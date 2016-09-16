package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static net.serenitybdd.rest.RestRequests.expect;

public class caches_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> getStudentLastmod_studentid()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("studentid", oData.getParam("studentid")).
			spec(extends_given).

		when().
			get("/caches/lastmod/{studentid}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getCustomerEtag_customerid()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("customerid", oData.getParam("customerid")).
			spec(extends_given).

		when().
			get("/caches/etag/{customerid}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> updateStudent_lastmod()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			contentType("application/json").
			content(oData.getBody()).
			spec(extends_given).

		when().
			post("/caches/lastmod").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getCustomers_expires()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/caches/expires").andReturn();

		return oResponse;
	}
 
}