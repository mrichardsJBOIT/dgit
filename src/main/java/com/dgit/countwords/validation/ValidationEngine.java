package com.dgit.countwords.validation;

import java.util.ArrayList;
import java.util.List;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.results.Results;
import com.dgit.countwords.validation.rules.RuleFactory;
import com.dgit.countwords.validation.rules.ValidationRule;

/**
* The validation engine contains a list of rules 
* that it then runs over a list of, or over an individual name.
* The results are returned in a wrapper class.
*/ 
public class ValidationEngine {
	
	private List<ValidationRule> rules = new ArrayList<ValidationRule>();
	
	/**
	* This method executes or runs the internal list of rules 
	* over the name that is passed in 
	* @return Results - a wrapper class 
	*/
	public Results Run(Name n){
		Results results = new Results();		
		for(ValidationRule r:rules){
			if (null != r){
				r.Check(results, n);	
			}			
		}
		return results;
	}

	public ValidationEngine() {
		super();
		
		//uses a factory class to retrieve a list of rules
		//and populate the validation engine with that list
		 	 
		for (ValidationRule rule : RuleFactory.getInstance().getAllNameRules()) {
			AddRule(rule);
		}	
	}

	/**
	* This method executes or runs the internal list of rules 
	* over the list of names that are passed in 
	* @return Results - a wrapper class 
	*/
	public Results Run(List<Name> names){
		Results results = new Results();
		for(Name n:names){
			for(ValidationRule r:rules){	
				r.Check(results, n);		
			}
		}		
		return results;
	}
	
	/**
	* Internal method to add a Rule to the internal list of rules
	*/
	public void AddRule(ValidationRule rule){
		rules.add(rule);
	}
}