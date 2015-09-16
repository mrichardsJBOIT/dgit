package com.dgit.countwords.results;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.validation.rules.ValidationRule;

/**
* Wrapper class that holds the list of results and 
* provides access to those results groups and meta data 
*/ 
public class Results {
	
	private List<Result> resultList = new ArrayList<Result>();
	
	public void AddName(Name name, ValidationRule rule) {		
		resultList.add(new Result(name, rule.getID()));
	}
	
	public boolean hasWords(){
		return resultList.size() > 0 ? true : false;
	}
	
	/**
	* Generic method that returns the number of results for the 
	* rule identify that is passed in
	* @return int
	*/ 
	public int getNumberOfResultsForRule(int ruleId){
		List<Result> filteredResults = resultList.stream().filter(p -> p.ruleId == ruleId).collect(Collectors.<Result>toList());
		return filteredResults.size();
	}

	/**
	* Generic method that returns a flat string list of names
	* that satisfy the rule id that is passed in
	* @return String
	*/
	public String ruleReport(int ruleId ) {
		
		StringBuffer report = new StringBuffer("");
		List<Result> filteredResults = resultList.stream().filter(p -> p.ruleId == ruleId).collect(Collectors.<Result>toList());
		for (Result result : filteredResults) {
			report.append(result.name.getValue()+System.lineSeparator());	//Not sure about the line Separator - it should probably be the delimter from the WordCounter class
			report.append(System.lineSeparator());	
		}		 			 		 
		
		return report.toString();
	}
	
	/**
	* Generic method that returns a list of strings which are the names
	* that satisfy the rule identified by the integer passed in
	* @return List<String>
	*/
	public List<String> getRuleReportList(int ruleId){
		
		List<String> nameList = new ArrayList<String>();
		List<Result> filteredResults = resultList.stream().filter(p -> p.ruleId == ruleId).collect(Collectors.<Result>toList());
		for (Result result : filteredResults) {
			 nameList.add(result.name.getValue());			
		}		 			 		 
		
		return nameList;				
	}
	
	/**
	* Internal wrapper class used to enable filtering of
	* result set by rule id 
	*/               
	public class Result {
		
		private Name name;
		private int ruleId;
		
		public Result(Name name, int ruleId) {
			super();
			this.name = name;
			this.ruleId = ruleId;
		}
	}
}
