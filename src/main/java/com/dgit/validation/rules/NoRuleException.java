package com.dgit.validation.rules;

/**
 * Thrown if rule is attempted be created with wrong or no id
*/
public class NoRuleException extends Exception {
	
	int ruleId;
	NoRuleException(int attemptedRuleId) {
		ruleId=attemptedRuleId;
    }
    @Override
	public String toString(){ 
       return ("The rule does not exist = "+ruleId) ;
    }
    
    

}
