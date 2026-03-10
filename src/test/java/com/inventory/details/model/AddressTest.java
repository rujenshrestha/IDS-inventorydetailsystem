package com.inventory.details.model;

import org.junit.Test;
import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import com.inventory.detail.model.Address;
import com.openpojo.reflection.filters.FilterNonConcrete;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

public class AddressTest {

	@Test
	public void testAllGettersAndSetters() {
		assertPojoMethodsFor(Address.class).areWellImplemented();
	}
	
	@Test
	public void validateBeans() {
		Validator validator = ValidatorBuilder.create().with(new SetterTester()).with(new GetterTester()).build();
		//exclude enums, abstracts, interfaces
		validator.validateRecursively("com.inventory.details.model", new FilterNonConcrete());
	}
	
}
 