package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static net.serenitybdd.rest.RestRequests.expect;

public class other_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> getIssue_id()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("id", oData.getParam("id")).
			spec(extends_given).

		when().
			get("/other/other-rest/{id}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProcessing_process()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/other/process").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> delayResponse_seconds()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("seconds", oData.getParam("seconds")).
			spec(extends_given).

		when().
			get("/other/delay/{seconds}").andReturn();

		return oResponse;
	}
 
}