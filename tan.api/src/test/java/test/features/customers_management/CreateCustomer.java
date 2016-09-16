package test.features.customers_management;

import org.junit.Test;
import org.junit.runner.RunWith;
import static test.restful.UserDefineActions.CommonLib.*;

import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.UserDefineActions.customers;
import test.restful.UserDefineActions.customers_assertion;

@RunWith(SerenityParameterizedRunner.class)
@SuppressWarnings("unchecked")
@UseTestDataFrom(value="./src/test/java/test/data/customer_new.csv")
public class CreateCustomer {
	
	String firstName;
	String lastName;
	String street;
	String city;
	
	BDDStatement<customers<customers_assertion>> obdd = 
			BDDStatement.withResource(customers.class, customers_assertion.class);
	
	@Test
	@Issue("#API-4")
	@Title("Ability to create a customer")
	public void createCustomer()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("firstname", firstName).
			Param("lastname", lastName).
			Param("city", city).
			Param("street", street).
			
		When().
		createCustomer_Customer().
		
		Then().
		checkCustomerExist(
				hasPropertyValue("id"),
				hasPropertyValue("firstname"),
				hasPropertyValue("lastname"),
				hasPropertyValue("street"),
				hasPropertyValue("city"));
	}
}
