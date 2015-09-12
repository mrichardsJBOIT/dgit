package com.dgit.validation.rules;

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
