/**
 * 
 */
package com.dgit.validation.rules;

import com.dgit.model.Name;
import com.dgit.validation.Results;

/**
 * A Validation Rule must have an identifier and know how to check itself against a some model data e.g. Name
 */
public interface ValidationRule {

	void Check(Results results, Name m);
	int getID();
}
