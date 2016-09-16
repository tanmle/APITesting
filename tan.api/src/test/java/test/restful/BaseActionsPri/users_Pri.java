package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static net.serenitybdd.rest.RestRequests.expect;

public class users_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> createNewUser_users()
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
			post("/users").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> createUser_users()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			formParameter("id", oData.getParam("id")).
			formParameter("name", oData.getParam("name")).
			formParameter("username", oData.getParam("username")).
			formParameter("password", oData.getParam("password")).
			formParameter("role", oData.getParam("role")).
			spec(extends_given).

		when().
			put("/users").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> updateUser_users()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			formParameter("id", oData.getParam("id")).
			formParameter("name", oData.getParam("name")).
			formParameter("username", oData.getParam("username")).
			formParameter("password", oData.getParam("password")).
			formParameter("role", oData.getParam("role")).
			spec(extends_given).

		when().
			post("/users").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getUsers_users()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/user-schema.json"))).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/users").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getSupportedOperations_users()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			options("/users").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getListUsers_list()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/user-schema.json"))).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/users/secure/list").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> deleteUser_userid()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("userid", oData.getParam("userid")).
			spec(extends_given).

		when().
			delete("/users/{userid}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getUser_userid()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/user-schema.json"))).
			spec(extends_expect).

		given().
			pathParam("userid", oData.getParam("userid")).
			spec(extends_given).

		when().
			get("/users/{userid}").andReturn();

		return oResponse;
	}
 
}