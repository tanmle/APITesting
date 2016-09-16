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

public class annotations<T> extends annotations_Pri<T>
{
	@Step("")
	public ResponseClass<T> getNonExistProductID()
	{
		oData.oResponse = 
		expect().
		given().
			pathParam("id", oData.getParam("id")).
			spec(extends_given).

		when().
			urlEncodingEnabled(false).
			get("/annotations/matrix;id={id}").andReturn();

		return oResponse;
	}
}