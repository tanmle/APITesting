package test.restful.UserDefineActions;

import static com.jayway.restassured.path.json.JsonPath.from;
import static net.serenitybdd.rest.RestRequests.expect;

import junit.framework.Assert;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.Dictionary;

public class customers_assertion extends AssertThat<customers_assertion>
{ 
	
	@Step
	public Object getValueFromResponse(String key){
		Object value = null;
		if (oData.getBodyResponse() != null && oData.getBodyResponse().length() > 0)
		{
			value = from(oData.getBodyResponse()).get(key);
		}
		
		return value;
	}
	
	@Step
	public customers_assertion checkCustomerExist(String... properties)
	{
		Object id = oData.getParam("id");
		if (id == null && (oData.getBodyResponse() != null && oData.getBodyResponse().length() > 0))
		{
			id = from(oData.getBodyResponse()).get("id");
			oData.putParam("id", id);
		}
		
		oData.oResponse = 
				expect().
				statusCode(200).
				given().
				auth().preemptive().basic("admin", "admin").
				pathParam("id", id).
				when().
				get("http://192.168.188.121:8000/RESTful/rest/customers/{id}").andReturn();
		
		return checkProperties(properties);
	}
	
	@Step
	public customers_assertion checkCustomerNonExist()
	{
		expect().
		statusCode(204).
		
		given().
		auth().preemptive().basic("admin", "admin").
		pathParam("customerid", oData.getParam("customerid")).
		
		when().
		get("http://192.168.188.121:8000/RESTful/rest/customers/{customerid}").andReturn();
	
		return this;
	}
}