package com.dgit.countwords.tests;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.results.Results;
import com.dgit.countwords.validation.ValidationEngine;

public class ValidationEngineTest {

	ValidationEngine veTester ;
	List<Name> names;
	
	@Before
    public void setUp() {
		veTester =  new ValidationEngine();				
    }
	
	@After
    public void tearDown() {
		veTester = null;				
    }
	
	@Test
	public void testEngineOneMnameNumber() {		
	
		Name malfoy = new Name("Malfy");
		names = new ArrayList<Name>();
		names.add(malfoy);			
		Results results = veTester.Run(names);
		assertEquals(1,results.getNumberOfResultsForRule(1) );
	}
	
	@Test
	public void testEngineOneFiveChar() {		
	
		//Test we get the right name back
		Name alfy = new Name("AlfyBigWord");
		names = new ArrayList<Name>();
		names.add(alfy);		
		Results results = veTester.Run(names);
		assertEquals("AlfyBigWord",results.ruleReport(2).trim() );
	}
	
	@Test
	public void testEngineWithSmallName() {		
	
		//Testing we don't get any results for both rules with the word "sml"
		Name small = new Name("sml");
		names = new ArrayList<Name>();
		names.add(small);						
		Results results = veTester.Run(names);
		assertThat(new Integer(0).toString(),
				allOf(containsString(new Integer(results.getNumberOfResultsForRule(1)).toString()), containsString(new Integer(results.getNumberOfResultsForRule(2)).toString())));
	}
	
	@Test
	public void testEngineBlankData() {		
	
		//Testing that with a blank string we still get a result object
		Name none = new Name("");
		names = new ArrayList<Name>();
		names.add(none);		
		assertNotNull( veTester.Run(names));		
	}
	
	@Test
	public void testEngineBlankDataNoNames() {		
		
		//Testing we don't get any names in the results list with a blank string as input
		Name small = new Name("");
		names = new ArrayList<Name>();
		names.add(small);
		Results results = veTester.Run(names);
		assertThat("",allOf(containsString(results.ruleReport(1)), containsString(results.ruleReport(2))));	
	}
}
