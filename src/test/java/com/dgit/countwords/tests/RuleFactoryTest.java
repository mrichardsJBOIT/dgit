package com.dgit.countwords.tests;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import com.dgit.countwords.validation.rules.RuleFactory;
import com.dgit.countwords.validation.rules.ValidationRule;

public class RuleFactoryTest {

	
	@Test
	public void testTwoRulesCreated() {
		List<ValidationRule> rules =  RuleFactory.getInstance().getAllNameRules();
		assertEquals(2, rules.size());
	}

}
