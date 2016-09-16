package test.features.customers_management;

import org.junit.Test;
import org.junit.runner.RunWith;
import static test.restful.UserDefineActions.CommonLib.*;

import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.TemplateClasses.WSBDDRunner;
import test.restful.TemplateClasses.WithResource;
import test.restful.UserDefineActions.customers;
import test.restful.UserDefineActions.customers_assertion;

@RunWith(WSBDDRunner.class)
@SuppressWarnings("unchecked")
public class GetCustomer {
	
	BDDStatement<customers<customers_assertion>> obdd = 
			BDDStatement.withResource(customers.class, customers_assertion.class);
	
	@WithResource(Resource = customers.class, AssertionResource = customers_assertion.class)
	public BDDStatement<customers<customers_assertion>> obdd1;
	
	@Test
	@Issue("#API-5")
	@Title("Ability to get a exist customer by id")
	public void getCustomerByID()
	{	
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("customerid", 1).

		When().
			getcustomer_customerid().

		Then().
			checkProperties(
				Property("id", 1),
				Property("firstname", "Susanne"),
				Property("lastname", "King"),
				Property("street", "366 - 20th Ave."),
				Property("city", "Olten"));
	}
	
	@Test
	@Issue("#API-6")
	@Title("Ability to get all customers")
	public void getAllCustomer()
	{
		obdd1.
		Given().
			AuthBASIC("admin", "admin").
		
		When().
			getCustomers_customers().
		
		Then().
			isJSONValid();
	}

	@Test
	@Issue("#API-7")
	@Title("Cannot get a non-exist customer by id")
	public void getNonExistCustomer()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("customerid", 999).
			
		When().
			getNonExistCustomer().
			
		Then().
			hasStatusCode(204);
	}
	
	@Test
	@Issue("#API-10")
	@Title("Ability to get header of customer")
	public void getCustomerHeader()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("customerid", 1).
			
		When().
			getHeader_customers().
		
		Then().
			checkBodyLength(0);
	}
}
