/**
 * 
 */
package com.dgit.validation.rules;

import com.dgit.model.Name;
import com.dgit.validation.Results;

/**
 * @author MRICHARD
 *
 */
public interface ValidationRule {

	void Check(Results results, Name m);
	int getID();
}
