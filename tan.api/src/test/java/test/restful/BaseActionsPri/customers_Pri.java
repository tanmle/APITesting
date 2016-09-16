package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static net.serenitybdd.rest.RestRequests.expect;

public class customers_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> updateCustomer_customers()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			contentType("application/xml").
			content(oData.getBody()).
			contentType("application/json").
			content(oData.getBody()).
			spec(extends_given).

		when().
			post("/customers").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getCustomers_customers()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/customer-schema.json"))).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/customers").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getHeader_customers()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			head("/customers").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> createCustomer_customers()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			formParameter("firstname", oData.getParam("firstname")).
			formParameter("lastname", oData.getParam("lastname")).
			formParameter("street", oData.getParam("street")).
			formParameter("city", oData.getParam("city")).
			spec(extends_given).

		when().
			put("/customers").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getSupportedOperations_customers()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			options("/customers").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> deleteCustomer_customerid()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("customerid", oData.getParam("customerid")).
			spec(extends_given).

		when().
			delete("/customers/{customerid}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getcustomer_customerid()
	{
		oData.oResponse = 
		expect().
			statusCode(204).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/customer-schema2.json"))).
			spec(extends_expect).

		given().
			pathParam("customerid", oData.getParam("customerid")).
			spec(extends_given).

		when().
			get("/customers/{customerid}").andReturn();

		return oResponse;
	}
}