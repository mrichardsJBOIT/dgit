package com.dgit.countwords.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.results.Results;
import com.dgit.countwords.validation.ValidationEngine;
import com.dgit.countwords.validation.rules.RuleFactory;

/**
* Entry point into the count word application
* Exposes the business logic and encapsulates controls and flow. 
* Uses a validation engine to parse the input data 
* into a list of strings to process.
*/ 
public class WordCounter {
	
	private ValidationEngine engine;
	public static String delimiter = "\\s"; //This could be externalised so calling class can decide what delimiter is used
	private List<Name> nameListToValidate;
	private Results validationResults;
	
	public WordCounter(){
		engine = new ValidationEngine();	
	}

	/**
	* Returns a complete string that is a list of names that are longer five characters long 
	* @return String.
	*/ 
	public String getNamesLongerThan5Chars(){
		return validationResults.ruleReport(RuleFactory.NAMES_LONGER_THAN_5_CHARS);
	}
	
	/**
	* Returns a List of String containing names that are longer five characters long 
	* @return String.
	*/ 
	public List<String> getNamesLongerThan5CharsList(){
		return validationResults.getRuleReportList(RuleFactory.NAMES_LONGER_THAN_5_CHARS);	
	}
	
	/**
	* Returns number of names that start with 'M' or 'm'
	* @return int
	*/
	public int getNumberOfWordStartingWithM() {		
		return validationResults.getNumberOfResultsForRule(RuleFactory.NAMES_STARTING_M);	
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
	* The rules are applied on that data passed in this method
	* A Result object is passed back which can be interrogated 
	* by the calling class once this method completes 
	*/
	public void processList(String inputStreamString){	
			engine = new ValidationEngine();				
			createNameList(inputStreamString);			
			validationResults = engine.Run(nameListToValidate);					
	}
}
