package com.dgit.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.dgit.model.Name;
import com.dgit.validation.Results;
import com.dgit.validation.ValidationEngine;
import com.dgit.validation.rules.RuleFactory;
import com.dgit.validation.rules.ValidationRule;

/**
* Entry point into the count word application
* Exposes the business logic and encapsulates controls and flow. 
* Uses a validation engine to parse the input data 
* into a list of strings to process.
*/ 
public class WordCounter {
	
	private ValidationEngine engine;
	public static String delimiter = "\\s";
	private List<Name> nameListToValidate;
	private Results validationResults;
	
	public WordCounter(){
		engine = new ValidationEngine();	
	}
	
	/**
	* This method uses a factory class to retrieve a list of rules
	* and populate the validation engine with that list 
	*/ 
	private void createRules(){
		
		for (ValidationRule rule : RuleFactory.getInstance().getAllNameRules()) {
			engine.AddRule(rule);
		}		
	}
	
	/**
	* Returns a string of names that are longer begin with M or m 
	* @return String.
	*/
	public String getNamesStartingWithMm(){
		return getRuleResultsList(RuleFactory.NAMES_STARTING_M);
	}
	
	/**
	* Returns a string of names that are longer five characters long 
	* @return String.
	*/ 
	public String getNamesLongerThan5Chars(){
		return getRuleResultsList(RuleFactory.NAMES_LONGER_THAN_5_CHARS);
	}
	
	/**
	* Returns the number of names that are longer five characters long 
	* @return int.
	*/
	public int getNumberOfWordsLongerThan5Chars(){
		if (null != validationResults){
			return validationResults.getNumberOfResultsForRule(RuleFactory.NAMES_LONGER_THAN_5_CHARS);	
		}
		return 0;
	}
	
	/**
	* Returns number of names that start with 'M' or 'm'
	* @return int
	*/
	public int getNumberOfWordStartingWithM() {
		
		if (null != validationResults){
			return validationResults.getNumberOfResultsForRule(RuleFactory.NAMES_STARTING_M);	
		}
		
		return 0;
	}
	
	/**
	* Internal generic method that returns a string representation 
	* of the results list for the rule identifier passed in.
	* @return String.
	*/
	private String getRuleResultsList(int ruleId){
		
		if (null != validationResults){
			return validationResults.ruleReport(ruleId);	
		}
		else {
			return "VALIDATION ISSUE - RUN VALIDATION";
		}				
	}
	
	/**
	* Internal method that splits the raw input data string into 
	* a list of names to be validated 
	*/
	private void createNameList(String inputStreamString){		
	
        Pattern pattern = Pattern.compile(delimiter);
        String[] testees = pattern.split(inputStreamString);
        
        nameListToValidate = new ArrayList<Name>();
        for (String testee : testees) {
        	nameListToValidate.add(new Name(testee));
        }        
	}
	
	/**
	* This method is passed the data to be processed.  
	* The rules are applied on that data in this method
	* and the results can be interrogated  by the calling class once this method completes 
	*/
	public void processList(String inputStreamString){
		
		if (null != inputStreamString){
			
			engine = new ValidationEngine();	
			createRules();					
			createNameList(inputStreamString);			
			validationResults = engine.Run(nameListToValidate);			
		}		
	}
}
