package test.restful.UserDefineActions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.Data;
import test.restful.TemplateClasses.Dictionary;
import javax.validation.ValidationException;
import static com.jayway.restassured.path.json.JsonPath.*;
import static org.hamcrest.Matchers.*;
import java.util.Iterator;

@SuppressWarnings("unchecked")
public class AssertThat<T> {

	protected Data oData;

	public void Initialization(Data oData)
	{
		this.oData = oData;
		this.oData.oValidatableResponse = this.oData.oResponse != null ? this.oData.oResponse.then() : null;
	}
	
	
	@Step("Assert Equals expected is {1} - actual is {0}")
	private String AssertEquals(Object oActual, Object oExpected) {
		String error = "";
		try {
			Assert.assertEquals(oExpected, oActual);
		} catch (AssertionError ex) {
			error += ex.getMessage() + "\r\n";
		}
		return error;
	}

	@Step
	public T Check_Info() {
		return (T) this;
	}

	@Step
	public T ValidateObject() {
		return (T) this;
	}

	@Step
	public T checkProperty(String sProName, Object oExpected) {
		Assert.assertEquals(oExpected, from(oData.oResponse.body().asString()).get(sProName));
		return (T) this;
	}

	@Step
	public T checkProperties(Dictionary... sProperties) {

		JSONObject jExpected = CommonLib.getJsonObjectFromDictionaries(sProperties);
		JSONObject jActual = new JSONObject(oData.oResponse.body().asString());

		String error = "\r\n";
		String key;
		Iterator<?> keys = jExpected.keys();

		while (keys.hasNext()) {
			key = (String) keys.next();
			
			if (!jExpected.has(key))
				error = "Property " + key + " is not exist.";
			else
				error += AssertEquals(jActual.get(key), jExpected.get(key));
		}

		if (error.length() > 2)
			throw new ValidationException(error);
		return (T) this;
	}

	@Step
	public T checkProperties(String... sProperties) {
		JSONObject jExpected = CommonLib.getJsonObjectFromStrings(oData.getAllParams(), sProperties);
		JSONObject jActual = new JSONObject(oData.getBodyResponse());

		String error = "\r\n";
		String key;
		Iterator<?> keys = jExpected.keys();

		while (keys.hasNext()) {
			key = (String) keys.next();
			
			if (!jActual.has(key))
				error = "Property \"" + key + "\" is not exist.";
			else
				error += AssertEquals(jActual.get(key), jExpected.get(key));
		}

		if (error.length() > 2)
			throw new ValidationException(error);
		return (T) this;
	}

	@Step
	public T checkObject(String separate, String... sProperties) {
		JSONObject jExpected = CommonLib.getJsonObjectFromStrings(separate, sProperties);
		JSONObject jActual = new JSONObject(oData.oResponse.body().asString());
		Assert.assertEquals(jExpected, jActual);
		return (T) this;
	}

	@Step
	public T hasProperty(String sProName) {

		if (oData.oResponse != null) {
		}

		return (T) this;
	}

	@Step
	public T hasContent(String sContent) {
		oData.oValidatableResponse.body(containsString(sContent));
		return (T) this;
	}
	
	@Step
	public T hasStatusCode(int statusCode){
		Assert.assertNotNull(oData.oValidatableResponse);
		
		oData.oValidatableResponse.statusCode(statusCode);
		return (T) this;
	}

	@Step
	public T hasProperty(JSONObject jo) {
		return (T) this;
	}	
	
	@Step
	public T isJSONValid() {
	    try {
	        new JSONObject(oData.oResponse.body().asString());
	    } catch (JSONException ex) {
	        try {
	            new JSONArray(oData.oResponse.body().asString());
	        } catch (JSONException ex1) {
	        	return (T) this;
	        }
	    }
	    return (T) this;
	}
	
	@Step
	public T checkBodyLength(int value)
	{
		Assert.assertEquals(value, oData.getBodyResponse().length());
		return (T) this;
	}
}