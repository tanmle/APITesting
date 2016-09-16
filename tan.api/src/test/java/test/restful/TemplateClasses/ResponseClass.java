package test.restful.TemplateClasses;

import java.lang.reflect.Method;
import com.jayway.restassured.response.Response;
import net.serenitybdd.core.steps.Instrumented;

public class ResponseClass<T> {

	private Data _oData;
	
	public void setData(Data oData)
	{
		_oData = oData;
	}
	
	@SuppressWarnings("unchecked")
	public T Then()
	{
		Object tAssert = Instrumented.instanceOf(_oData.getAssertType()).newInstance();
		
		Method method = null;
		
		try {
			method = tAssert.getClass().getMethod("Initialization", Data.class);
			method.invoke(tAssert, _oData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (T) tAssert;
	}
	
	public Response andReturn()
	{
		return _oData.oResponse;
	}		
}