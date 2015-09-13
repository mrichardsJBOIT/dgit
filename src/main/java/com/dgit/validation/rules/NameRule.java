package com.dgit.validation.rules;

import com.dgit.model.Name;
import com.dgit.validation.Results;
import java.util.function.Predicate;
 
/**
* A concrete rule class that has a predicate condition - the rule,
* a description of the rule and an identifier
*/
public class NameRule implements ValidationRule {

	private Predicate<Name> condition;
	private String description;
	private int iD=0;
	
	/**
	 * Constructor -  calling class must be aware of Lambda Expressions
	 * as this is what is executed in the check methods
	*/
	public NameRule(Predicate<Name> aCondition, String aDescription, int anID) {
		super();
		this.condition = aCondition;
		this.description = aDescription;
		this.iD = anID;
	}

	
	private boolean condition(Name m) 
	{
		  return condition.test(m);		 
	}

	/**
	 * This method is where the data is checked against the rule (itself)
	 * If the data passes the rule it is added to the results object passed through
	*/
	@Override
	public void Check(Results results, Name m) {
		
		if (condition.test(m)){
			results.AddName(m, this);
		}		
	}

	/**
	 * Used to to identify the rule when creating a result set
	*/
	@Override
	public int getID() {	
		return this.iD;
	}

}
