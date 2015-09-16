package com.dgit.countwords.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.results.Results;
import com.dgit.countwords.validation.rules.NameRule;

public class NameRuleTest {

	NameRule myRule;
	Results results;
	
	@Before
    public void setUp() {
		results = new Results();
    }
	
	@After
    public void tearDown() {
		myRule = null;		
    }
	
	@Test
	public void testConditionRuns() {
		Name mb = new Name("Maybees");		
		myRule = new NameRule(p -> p.getValue().equalsIgnoreCase("Maybees"), "Rule 3: Testing if we have a Maybees",3);		
		myRule.Check(results, mb);
		String maybe = "Maybees";
		String resultString =  results.ruleReport(3).trim();
		assertEquals(maybe, resultString);
	}
}
