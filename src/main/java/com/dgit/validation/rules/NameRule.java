package com.dgit.validation.rules;

import com.dgit.model.Name;
import com.dgit.validation.Results;
import java.util.function.Predicate;
 

public class NameRule implements ValidationRule {

	private Predicate<Name> condition;
	private String description;
	private int iD=0;
	
	
	public NameRule(Predicate<Name> aCondition, String aDescription, int anID) {
		super();
		this.condition = aCondition;
		this.description = aDescription;
		this.iD = anID;
	}

	
	private boolean condition(Name m) //, Predicate<Name> condition)
	{
		  return condition.test(m);
		 
	}
	@Override
	public void Check(Results results, Name m) {
		
		if (condition.test(m)){
			results.AddName(m, this);
		}		
	}


	@Override
	public int getID() {	
		return this.iD;
	}

}
