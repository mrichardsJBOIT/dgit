package com.dgit.countwords.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.dgit.countwords.controllers.WordCounter;

public class CounterTest  {
	WordCounter counter;
	int noOfMwords;
	List<String> fiveCharWords;
	
	@Before
    public void setUp() {
		counter = new WordCounter();		
		fiveCharWords = new ArrayList<String>();
    }
	
	@After
    public void tearDown() {
		counter = null;		
		fiveCharWords = null;
    }
	
	@Test
	public void testNumberBothMwords() {		
		noOfMwords = 2;
		counter.processList("Maybe marks");		
		assertEquals(noOfMwords,  counter.getNumberOfWordStartingWithM());
	}
	
	@Test
	public void testListOf5Mwords() {
				
		fiveCharWords.add("polymorphism");
		fiveCharWords.add("brilliant");
		counter.processList("polymorphism is brilliant");		
		assertEquals(fiveCharWords,  new ArrayList<String>(counter.getNamesLongerThan5CharsList()));
	}
	
	@Test
	public void testHandlesNonAlphaText() {
				
		fiveCharWords.add("\\n#12=M");		
		counter.processList("\\n#12=M");		
		assertEquals(fiveCharWords,  new ArrayList<String>(counter.getNamesLongerThan5CharsList()));
	}
	
	@Test
	public void testLoremIpsum() {
		
		fiveCharWords.add("consectetur"); 
		fiveCharWords.add("adipiscing");
		fiveCharWords.add("eiusmod"); 
		fiveCharWords.add("tempor");
		fiveCharWords.add("incididunt");
		fiveCharWords.add("labore"); 
		fiveCharWords.add("dolore"); 
		fiveCharWords.add("aliqua"); 		
		fiveCharWords.add("veniam");
		fiveCharWords.add("nostrud");
		fiveCharWords.add("exercitation");
		fiveCharWords.add("ullamco");
		fiveCharWords.add("laboris");
		fiveCharWords.add("aliquip");
		fiveCharWords.add("commodo");
		fiveCharWords.add("consequat");
		fiveCharWords.add("reprehenderit");
		fiveCharWords.add("voluptate"); 
		fiveCharWords.add("cillum");
		fiveCharWords.add("dolore");
		fiveCharWords.add("fugiat");
		fiveCharWords.add("pariatur");
		fiveCharWords.add("Excepteur");
		fiveCharWords.add("occaecat");
		fiveCharWords.add("cupidatat");
		fiveCharWords.add("proident");
		fiveCharWords.add("officia");
		fiveCharWords.add("deserunt");
		fiveCharWords.add("mollit");
		fiveCharWords.add("laborum");
		
		String lorem = 	"Lorem ipsum dolor sit amet consectetur adipiscing elit sed do eiusmod tempor incididunt ut labore et "+
						"dolore magna aliqua Ut enim ad minim veniam quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo "+
						"consequat Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur "+
						"Excepteur sint occaecat cupidatat non proident sunt in culpa qui officia deserunt mollit anim id est laborum ";
		
		counter.processList(lorem);		
		assertEquals(fiveCharWords,  new ArrayList<String>(counter.getNamesLongerThan5CharsList()));
	}
}
