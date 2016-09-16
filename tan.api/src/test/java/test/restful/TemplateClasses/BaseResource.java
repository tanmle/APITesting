package test.restful.TemplateClasses;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;
import net.thucydides.core.annotations.Steps;

public class BaseResource<T>{
	protected static final long serialVersionUID = 1L;

	protected Data oData;
	public RequestSpecification extends_given;
	public ResponseSpecification extends_expect;
		
	@Steps
	protected ResponseClass<T> oResponse;
	
	protected void AssignParams(Data oData) {
		
		this.oData = oData; 
		
		initSpecification();
		
		oResponse.setData(oData);
	}
	
	private void initSpecification()
	{		
		RestAssured.baseURI = (String) oData.odataPrimary.oDataPri.get("BaseURI");
		extends_expect = new ResponseSpecBuilder().build();
		extends_given = new RequestSpecBuilder().build();
		
		if (oData.getAuthen() != null) {
			switch (oData.getAuthen().getType()) {
			case BASIC:
				extends_given.auth().preemptive().basic(oData.getAuthen().getUsername(), oData.getAuthen().getPassword());
				break;

			case DIGEST:
				extends_given.auth().digest(oData.getAuthen().getUsername(), oData.getAuthen().getPassword());
				break;
			}
		}
	}

	protected void addExtendsExpect(ResponseSpecification extends_expect) {	
		if (extends_expect != null)
			this.extends_expect.spec(extends_expect);

	}

	protected void addExtendsGiven(RequestSpecification extends_given) {
		if (extends_given != null) 		
			this.extends_given.spec(extends_given);
	}
}