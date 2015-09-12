package com.dgit.validation;

import java.util.ArrayList;
import java.util.List;

import com.dgit.model.Name;
import com.dgit.validation.rules.ValidationRule;

public class ValidationEngine {
	
	private List<ValidationRule> rules = new ArrayList<ValidationRule>();
	
	public Results Run(Name n){
		Results results = new Results();
		
		for(ValidationRule r:rules){
			if (null != r){
				r.Check(results, n);	
			}			
		}
		return results;
	}

	public Results Run(List<Name> names){
		Results results = new Results();
		
		for(Name n:names){
			for(ValidationRule r:rules){	
				if (null != r){
					r.Check(results, n);
				}
			}
		}
		
		return results;
	}
	
	public void AddRule(ValidationRule rule){
		rules.add(rule);
	}
}
