package test.restful.BaseActionsPri;

import java.io.File;
import net.thucydides.core.annotations.Step;
import test.restful.TemplateClasses.ResponseClass;
import test.restful.TemplateClasses.BaseResource;
import static net.serenitybdd.rest.RestRequests.expect;

public class files_Pri<T> extends BaseResource<T>
{ 

	@Step
	public ResponseClass<T> getListFile_files()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			spec(extends_given).

		when().
			get("/files").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> uploadFile_upload()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().multiPart("file", new File((String)oData.getParam("file"))).
			spec(extends_given).

		when().
			post("/files/upload").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> downloadContentFile_filename()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("filename", oData.getParam("filename")).
			spec(extends_given).

		when().
			get("/files/content/{filename}").andReturn();

		return oResponse;
	}

	@Step
	public ResponseClass<T> downloadFile_filename()
	{
		oData.oResponse = 
		expect().
			statusCode(200).
			spec(extends_expect).

		given().
			pathParam("filename", oData.getParam("filename")).
			spec(extends_given).

		when().
			get("/files/attachment/{filename}").andReturn();

		return oResponse;
	}
 
}