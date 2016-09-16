package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import static net.serenitybdd.rest.RestRequests.expect;

public class annotations_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> createProduct_annotations()
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
			put("/annotations").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProductByQuery_query()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/product-schema2.json"))).
			spec(extends_expect).

		given().
			formParameter("name", oData.getParam("name")).
			spec(extends_given).

		when().
			get("/annotations/query").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProductByMatrix_matrix()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/product-schema.json"))).
			spec(extends_expect).

		given().
			param("id", oData.getParam("id")).
			spec(extends_given).

		when().
			get("/annotations/matrix").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProductByPath_id()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/product-schema.json"))).
			spec(extends_expect).

		given().
			pathParam("id", oData.getParam("id")).
			spec(extends_given).

		when().
			get("/annotations/path/{id}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProductByQueryDefault_query_default()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/product-schema.json"))).
			spec(extends_expect).

		given().
			param("name", oData.getParam("name")).
			spec(extends_given).

		when().
			get("/annotations/query-default").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> getProductByHeader_header()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/java/test/data/product-schema.json"))).
			spec(extends_expect).

		given().
			param("id", oData.getParam("id")).
			spec(extends_given).

		when().
			get("/annotations/header").andReturn();

		return oResponse;
	}
 
}