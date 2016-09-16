package test.restful.UserDefineActions;

import net.thucydides.core.annotations.Step;
import test.restful.BaseActionsPri.*;
import test.restful.TemplateClasses.ResponseClass;
import static net.serenitybdd.rest.RestRequests.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;

import javax.validation.ValidationException;

import test.restful.TemplateClasses.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class customers<T> extends customers_Pri<T>
{ 
	@Step("")
	public ResponseClass<T> createCustomer_Customer()
	{
		ResponseSpecification extendsExpect = expect().statusCode(201);
		addExtendsExpect(extendsExpect);
		return super.createCustomer_customers();
	}
	
	@Step("")
	public ResponseClass<T> getNonExistCustomer()
	{
		oData.oResponse = 
		expect().
		given().
			pathParam("customerid", oData.getParam("customerid")).
			spec(extends_given).

		when().
			get("/customers/{customerid}").andReturn();

		return oResponse;
	}
	
	@Step
	public ResponseClass<T> updateCustomer_userdefine()
	{
		ResponseSpecification extends_expect = 	expect().body(containsString("SUCCESS_RESULT"));
		
		RequestSpecification extends_given = new RequestSpecBuilder().build()
				.headers(oData.getHeaders());
		
		Dictionary[] params = new Dictionary[oData.getParams().size()];
		String content = CommonLib.getJsonObjectFromDictionaries(oData.getParams().toArray(params)).toString();
		extends_given.content(content);
		
		addExtendsGiven(extends_given);
		addExtendsExpect(extends_expect);
		return super.updateCustomer_customers();
	}

	@Step
	public ResponseClass<T> updateCustomer_customers()
	{
		RequestSpecification extends_given = new RequestSpecBuilder().build().content(oData.getBody());
		addExtendsGiven(extends_given);
		return super.updateCustomer_customers();
	}
	
	@Step
	public ResponseClass<T> deleteCustomer_customerid()
	{
		ResponseSpecification extends_expect = 	expect().body(containsString("SUCCESS_RESULT"));
		addExtendsExpect(extends_expect);
		return super.deleteCustomer_customerid();
	}
}