package test.features.customers_management;

import org.junit.Test;
import org.junit.runner.RunWith;
import static test.restful.UserDefineActions.CommonLib.*;

import java.util.UUID;

import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.TemplateClasses.WSBDDRunner;
import test.restful.TemplateClasses.WithResource;
import test.restful.UserDefineActions.customers;
import test.restful.UserDefineActions.customers_assertion;

@RunWith(WSBDDRunner.class)
@SuppressWarnings("unchecked")
public class UpdateCustomer {
	
	BDDStatement<customers<customers_assertion>> obdd = 
			BDDStatement.withResource(customers.class, customers_assertion.class);
	
	@WithResource(Resource = customers.class, AssertionResource = customers_assertion.class)
	public BDDStatement<customers<customers_assertion>> obdd1;
	
	String updateName = UUID.randomUUID().toString();
	
	@Test
	@Issue("#API-8")
	@Title("Ability to update the customer Address")
	public void updateCustomer()
	{
		obdd.Given().
			Param("street", updateName).
			Param("id", 11).
			AuthBASIC("admin", "admin").
		
		When().
			updateCustomer_userdefine().
			
		Then().
			checkCustomerExist(
					hasPropertyValue("id"),
					hasPropertyValue("street"));
		
		obdd1.Given().
			Param("customerid", 11).
			AuthBASIC("admin", "admin").
		
		When().
			getcustomer_customerid().
			
		Then().
			checkProperties(
				Property("id", 11),
				Property("street", updateName));
	}
}