package com.dgit.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.dgit.model.Name;
import com.dgit.validation.Results;
import com.dgit.validation.ValidationEngine;
import com.dgit.validation.rules.RuleFactory;
import com.dgit.validation.rules.ValidationRule;

public class NamesValidationController {
	
	private ValidationEngine engine;
	private String delimiter = "\\s";
	private List<Name> nameListToValidate;
	private Results validationResults;
	
	public NamesValidationController(){
		engine = new ValidationEngine();	
	}
	
	private void createRules(){
		
		for (ValidationRule rule : RuleFactory.getInstance().getAllNameRules()) {
			engine.AddRule(rule);
		}		
	}
	
	public String getNamesStartingWithMm(){
		return getRuleResultsList(RuleFactory.NAMES_STARTING_M);
	}
	
	public String getNamesLongerThan5Chars(){
		return getRuleResultsList(RuleFactory.NAMES_LONGER_THAN_5_CHARS);
	}
	
	public int getNumberOfWordsLongerThan5Chars(){
		return 0;
	}
	
	public int getNumberOfWordStartingWithM() {
		return 0;
	}
	
	private String getRuleResultsList(int ruleId){
		
		if (null != validationResults){
			return validationResults.ruleReport(ruleId);	
		}
		else {
			return "VALIDATION ISSUE - RUN VALIDATION";
		}				
	}
	
	private void createNameList(String inputStreamString){		
	
        Pattern pattern = Pattern.compile(delimiter);
        String[] testees = pattern.split(inputStreamString);
        
        nameListToValidate = new ArrayList<Name>();
        for (String testee : testees) {
        	nameListToValidate.add(new Name(testee));
        }        
	}
	
	public void doValidation(String inputStreamString){
		
		if (null != inputStreamString){
			
			engine = new ValidationEngine();	
			createRules();					
			createNameList(inputStreamString);			
			validationResults = engine.Run(nameListToValidate);			
		}		
	}


}
