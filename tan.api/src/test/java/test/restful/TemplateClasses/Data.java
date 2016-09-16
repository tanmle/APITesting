package test.restful.TemplateClasses;

import java.util.List;

import com.jayway.restassured.response.Headers;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

import test.restful.BaseActionsPri.data_Primary;
import test.restful.TemplateClasses.Authentication.AuthenType;
import test.restful.TemplateClasses.Dictionary.TypeParam;

@SuppressWarnings("rawtypes")
public class Data {

	private String _sGroup;
	private Class _oGroup;
	private Class _oAssert;

	private String _sBody;
	private Dictionaries _oAllParams;
	private Authentication _oAuthen;

	public data_Primary odataPrimary;
	public ValidatableResponse oValidatableResponse;
	public Response oResponse;

	public Data() {
		_oAllParams = new Dictionaries();
		_sBody = "";
		odataPrimary = new data_Primary();
	}

	public void initGroup(Class oGroup, Class oAssertType) {
		_oGroup = oGroup;

		String[] temp = oGroup.getName().split("\\.");
		_sGroup = temp[temp.length - 1];

		_oAssert = oAssertType;
	}

	public void AssignParam(String sBody, Dictionaries oParams, Authentication oAuthen) {
		_sBody = sBody;
		_oAllParams = oParams;
		_oAuthen = oAuthen;
	}

	public String getSGroup() {
		return _sGroup;
	}

	public Class getOGroup() {
		return _oGroup;
	}

	public Class getAssertType() {
		return _oAssert;
	}

	public String getBody() {
		
		return _sBody;
	}
	
	public String getBodyResponse(){
		return oResponse.body().asString();
	}

	public Dictionaries getAllParams() {
		return _oAllParams;
	}
	
	public Object getParam(String key) {
		return _oAllParams.getParam(key);
	}
	
	public List<Dictionary> getParams(){
		return _oAllParams.getParams();
	}
	
	public Headers getHeaders() {
		return _oAllParams.getHeaders();
	}
	
	public List<Dictionary> getCookies() {
		return _oAllParams.getCookies();
	}
	
	public Authentication getAuthen() {
		return _oAuthen;
	}

	public void setAuthen(String username, String password, AuthenType type) {
		_oAuthen = new Authentication(username, password, type);
	}

	public void setBody(String content) {
		if (content == null)
			_sBody = "";
		else
			_sBody = content;
	}

	public void putParam(String key, Object value) {
		_oAllParams.put(key, value, TypeParam.PARAM);
	}

	public void putHeader(String key, Object value) {
		_oAllParams.put(key, value, TypeParam.HEADER);
	}

	public void putCookie(String key, Object value) {
		_oAllParams.put(key, value, TypeParam.COOKIE);
	}

	public void putParam(Dictionary dictionary) {
		_oAllParams.put(dictionary);
	}

	public void putHeader(Dictionary dictionary) {
		_oAllParams.put(dictionary);
	}

	public void putCookie(Dictionary dictionary) {
		_oAllParams.put(dictionary);
	}
}