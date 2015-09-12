package com.dgit.validation.rules;

import java.util.ArrayList;
import java.util.List;

public class RuleFactory {
	
	public static final int NUMBER_OF_NAME_RULES = 2;  
	
	private static RuleFactory instance = null;
	public static final int NAMES_STARTING_M = 1;
	public static final int NAMES_LONGER_THAN_5_CHARS = 2;
	
	private RuleFactory(){
		
	}
	
	public static RuleFactory getInstance(){
		
		 if(instance == null) {
	         instance = new RuleFactory();
	      }
	      return instance;		
	}
	

	public ValidationRule getNameRule(int ruleId) throws NoRuleException {
		
		ValidationRule rule;
		switch (ruleId) {
			case NAMES_STARTING_M: rule = new NameRule(p -> p.getValue().toUpperCase().startsWith("M"), "Rule 1: Name Starts With M",NAMES_STARTING_M);
			
			case NAMES_LONGER_THAN_5_CHARS: rule = new NameRule(p -> p.getValue().length() > 5, "Rule 2: Name Longer Than 5  Characters ",NAMES_LONGER_THAN_5_CHARS);
			
			default : rule = null;		
		}		
		if (null == rule){					
			throw new NoRuleException(ruleId);
		}
		return rule;
	}
	
	public  List<ValidationRule> getAllNameRules(){
		
		List<ValidationRule> rulesList = new ArrayList<ValidationRule>();
		
		try {					
			for (int i =0; i<=NUMBER_OF_NAME_RULES; i++){
				rulesList.add(getNameRule(i));
			}		
		}
		catch(NoRuleException ne){
			//We need to implement loggoing for this.
			//using constants NUMBER_OF_NAME_RULES should stop this exception occuring in this method. 
		}
		return rulesList;
	}
}
