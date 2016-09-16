package test.restful.TemplateClasses;

import java.lang.reflect.Method;

import net.serenitybdd.core.steps.Instrumented;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import test.restful.BaseActionsPri.Manager_Resources;
import test.restful.TemplateClasses.Authentication.AuthenType;
import test.restful.TemplateClasses.Dictionary.TypeParam;

@SuppressWarnings("rawtypes") 
public class GivenClass<T extends BaseResource> {

	@Steps
	Manager_Resources<T> oMng;

	private Data _oData;
 
	public void InitGoup(Data oData) {
		_oData = oData;
	}

	public T When() {
		
		ShowReport();

		Method method = null;
		T tResource = null;

		try {
			if (_oData.getSGroup() != null)
				method = oMng.getClass().getMethod(_oData.getSGroup(), null);
			
			if (method != null) 
				tResource = (T) method.invoke(oMng, null);		
			else 
				tResource = (T) Instrumented.instanceOf(_oData.getOGroup()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tResource.AssignParams(_oData);
		return tResource;
	}

	// These methods below is used for display report
	@Step("Given")
	public void ShowReport() {
		//_oData.getAllParams().DistributeToGroups();
		if (_oData.getAuthen()!= null)
			Authentication(_oData.getAuthen().getUsername(), _oData.getAuthen().getPassword(), _oData.getAuthen().getType().name());
		
		for (Dictionary dic : _oData.getParams())
			Parameter(dic.getKey(), dic.getValue());

		for (Dictionary dic : _oData.getCookies())
			cookie(dic.getKey(), dic.getValue().toString());

		for (com.jayway.restassured.response.Header dic : _oData.getHeaders())
			header(dic.getName(), dic.getValue());
		
		if (_oData.getBody() != null && _oData.getBody() != "")
			Body(_oData.getBody());
	}

	@Step("Parameter: {0} is {1}")
	protected void Parameter(String key, Object value) {
	}

	public GivenClass<T> Param(String key, Object value) {
		_oData.putParam(key, value);
		return this;
	}

	@Step("Authentication with Username is {0} and Type is {2}")
	protected void Authentication(String username, String password, String type) {
	}
	
	public GivenClass<T> AuthBASIC(String username, String password){
		_oData.setAuthen(username, password, AuthenType.BASIC);
		return this;
	}
	
	public GivenClass<T> AuthDIGEST(String username, String password){
		_oData.setAuthen(username, password, AuthenType.DIGEST);
		return this;
	}

	@Step("Header: {0} is {1}")
	protected void header(String key, String value) {}
	public GivenClass<T> Header(String key, String value) {
		_oData.putHeader(key, value);
		return this;
	}

	@Step("Cookie: {0} is {1}")
	protected void cookie(String key, String value) {}
	public GivenClass<T> Cookie(String key, String value) {
		_oData.putCookie(key, value);
		return this;
	}
	
	@Step("Body content: {0}")
	protected void Body(String content) {}
	public GivenClass<T> BodyContent(String content) {
		_oData.setBody(content);
		return this;
	}
}
