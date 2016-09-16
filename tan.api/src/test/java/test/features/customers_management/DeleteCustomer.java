package test.features.customers_management;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.TemplateClasses.WSBDDRunner;
import test.restful.TemplateClasses.WithResource;
import test.restful.UserDefineActions.customers;
import test.restful.UserDefineActions.customers_assertion;

@RunWith(WSBDDRunner.class)
public class DeleteCustomer {
	
	
	String firstName = UUID.randomUUID().toString().split("-")[0];
	String lastName = UUID.randomUUID().toString().split("-")[0];
	String city = UUID.randomUUID().toString().split("-")[0];
	String street = UUID.randomUUID().toString().split("-")[0];
	
	@WithResource(Resource = customers.class, AssertionResource = customers_assertion.class)
	public BDDStatement<customers<customers_assertion>> obdd;
	
	@WithResource(Resource = customers.class, AssertionResource = customers_assertion.class)
	public BDDStatement<customers<customers_assertion>> obdd1;
	
	@Test
	@Issue("#API-9")
	@Title("Ability to delete a customer by id")
	public void deleteCustomer()
	{
		int id = (Integer) obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("firstname", firstName).
			Param("lastname", lastName).
			Param("city", city).
			Param("street", street).
			
		When().
		createCustomer_Customer().
		
		Then().getValueFromResponse("id");
		
		obdd1.
		Given().
			AuthBASIC("admin", "admin").
			Param("customerid", id).
		
		When().
			deleteCustomer_customerid().
		
		Then().
			checkCustomerNonExist();
	}
}
