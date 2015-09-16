/**
 * 
 */
package com.dgit.countwords.validation.rules;

import com.dgit.countwords.model.Name;
import com.dgit.countwords.results.Results;


/**
 * A Validation Rule must have an identifier and know how to check itself against a some model data e.g. Name
 */
public interface ValidationRule {

	void Check(Results result, Name m);
	int getID();
}
