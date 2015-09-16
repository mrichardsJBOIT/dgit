package com.dgit.countwords.validation.rules;

import java.util.ArrayList;
import java.util.List;

/**
 * Factory class that abstracts the rule creation to a single class.
*/
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
	
	/**
	* Internal method where the rules are defined
	* Rules are created using Lambda Expressions, they are given an incremental number and description
	* Further rules can be added be extending the case statements
	* A Rule object is relatively straight forward in 
	* keeping with the data model - Name object which the rule is checked against.
	*/
	private ValidationRule getNameRule(int ruleId) throws NoRuleException {
		
		ValidationRule rule;
		switch (ruleId) {
			case NAMES_STARTING_M: 
				rule = new NameRule(p -> p.getValue().toUpperCase().startsWith("M"), "Rule 1: Name Starts With M",NAMES_STARTING_M);
				break;				
			case NAMES_LONGER_THAN_5_CHARS: 
				rule = new NameRule(p -> p.getValue().length() > 5, "Rule 2: Name Longer Than 5  Characters ",NAMES_LONGER_THAN_5_CHARS);
				break;
			default : throw new NoRuleException(ruleId);		
		}		
		
		return rule;
	}
	
	/**
	* Accessor method that creates the rules and stores into a list that is returned to the calling class
	* @return List<ValidationRule>
	*/
	public List<ValidationRule> getAllNameRules(){
		
		List<ValidationRule> rulesList = new ArrayList<ValidationRule>();						
			for (int i =1; i<=NUMBER_OF_NAME_RULES; i++){
				try {	
					rulesList.add(getNameRule(i));
				}
				catch(NoRuleException ne){
					//We need to implement logging for this.
					//using constants NUMBER_OF_NAME_RULES should stop this exception occurring in this method. 
				}
			}				
		return rulesList;
	}
}