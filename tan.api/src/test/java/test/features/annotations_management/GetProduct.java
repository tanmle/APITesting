package test.features.annotations_management;

import static test.restful.UserDefineActions.CommonLib.Property;

import org.junit.Test;
import org.junit.runner.RunWith;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.TemplateClasses.WSBDDRunner;
import test.restful.TemplateClasses.WithResource;
import test.restful.UserDefineActions.annotations;
import test.restful.UserDefineActions.annotations_assertion;

@RunWith(WSBDDRunner.class)
public class GetProduct {
	
	@WithResource(Resource = annotations.class, AssertionResource = annotations_assertion.class)
	public BDDStatement<annotations<annotations_assertion>> obdd;
	
	@Test
	@Issue("#API-11")
	@Title("Ability to get product by id and use PathParam")
	public void getProductByID()
	{
		obdd.
			Given().
				AuthBASIC("admin", "admin").
				Param("id", 1).
				
			When().
				getProductByPath_id().
				
			Then().
				checkProperties(
					Property("id", 1),
					Property("name", "Chair Shoe"),
					Property("price", 25));
	}
	
	@Test
	@Issue("#API-12")
	@Title("Ability to get product by name and use param")
	public void getProductByName()
	{
		String name = "Chair Shoe";
		obdd.
			Given().
				AuthBASIC("admin", "admin").
				Param("name", name).
				
			When().
				getProductByQuery_query().
				
			Then().
				isJSONValid();
	}
	
	@Test
	@Issue("#API-13")
	@Title("Ability to get product by id, using QueryParam and Default Value")
	public void getProductByIDByDefault()
	{
		obdd.
			Given().
				AuthBASIC("admin", "admin").
				
			When().
				getProductByQuery_query().
				
			Then().
				isJSONValid();
	}
	
	@Test
	@Issue("#API-14")
	@Title("Cannot get product by non-exist id, using  PathParam")
	public void getNonExistProductByID()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("id", 9999).
		
		When().
			getNonExistProductID().
			
		Then().
			hasStatusCode(204);
	}
	
	@Test
	@Issue("#API-15")
	@Title("Cannot get product by string id, using PathParam")
	public void getNonExistProductByString()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("id", "anystring").
		
		When().
			getNonExistProductID().
			
		Then().
			hasStatusCode(404);
	}
}
