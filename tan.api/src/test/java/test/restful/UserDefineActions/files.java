package test.restful.UserDefineActions;

import net.thucydides.core.annotations.Step;
import test.restful.BaseActionsPri.*;
import test.restful.TemplateClasses.ResponseClass;
import static net.serenitybdd.rest.RestRequests.*;
import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class files<T> extends files_Pri<T>
{ 
	@Step("")
	public ResponseClass<T> getContentOfNonExistFile()
	{
		oData.oResponse = 
		expect().
		given().
			pathParam("filename", oData.getParam("filename")).
			spec(extends_given).

			when().
			get("/files/content/{filename}").andReturn();

		return oResponse;
	}
	
	@Step("")
	public ResponseClass<T> getContentMissingFile()
	{
		oData.oResponse = 
		expect().
		given().
			spec(extends_given).

			when().
			get("/files/content").andReturn();

		return oResponse;
	}
	
	@Step("")
	public ResponseClass<T> downloadNonExistFile()
	{
		oData.oResponse = 
		expect().
		given().
			pathParam("filename", oData.getParam("filename")).
			spec(extends_given).

		when().
			get("/files/attachment/{filename}").andReturn();

		return oResponse;
	}
}