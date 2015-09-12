package com.dgit.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.dgit.model.Name;
import com.dgit.validation.rules.ValidationRule;

public class Results {
	
	private List<Result> resultList = new ArrayList<Result>();
	
	public void AddName(Name name, ValidationRule rule) {		
		resultList.add(new Result(name, rule.getID()));
	}
	
	public boolean hasWords(){
		return resultList.size() > 0 ? true : false;
	}

	public String ruleReport(int ruleId ) {
		
		StringBuffer report = new StringBuffer("");
		List<Result> filteredResults = resultList.stream().filter(p -> p.ruleId == ruleId).collect(Collectors.<Result>toList());
		for (Result result : filteredResults) {
			report.append(result.name.getValue());	
			report.append(System.lineSeparator());	
		}		 			 		 
		
		return report.toString();
	}
	
	public List<String> getRuleNames(int ruleId){
		
		List<String> nameList = new ArrayList<String>();
		List<Result> filteredResults = resultList.stream().filter(p -> p.ruleId == ruleId).collect(Collectors.<Result>toList());
		for (Result result : filteredResults) {
			 nameList.add(result.name.getValue());			
		}		 			 		 
		
		return nameList;				
	}
	
	                
	private class Result {
		
		private Name name;
		private int ruleId;
		
		public Result(Name name, int ruleId) {
			super();
			this.name = name;
			this.ruleId = ruleId;
		}
	}
}
