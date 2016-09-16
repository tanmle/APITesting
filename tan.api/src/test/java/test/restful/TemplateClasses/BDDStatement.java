package test.restful.TemplateClasses;

import net.serenitybdd.core.steps.Instrumented;
import net.thucydides.core.annotations.Steps;
import test.restful.UserDefineActions.AssertThat;

@SuppressWarnings("rawtypes") 
public class BDDStatement<T extends BaseResource<? extends AssertThat>> {

	@Steps
	GivenClass<T> oGiven;
	
	private Data oData;
		
	public BDDStatement()
	{}

	public BDDStatement(Class oGroup, Class oAssertType)
	{
		oData = new Data();
		oData.initGroup(oGroup, oAssertType);
	}

	public void initGroup(Class oGroup, Class oAssertType)
	{
		oData = new Data();
		oData.initGroup(oGroup, oAssertType);
	}
	
	public static BDDStatement withResource(Class oResourceType, Class oAssertionResourceType) {
		return Instrumented.instanceOf(BDDStatement.class).withProperties(oResourceType, oAssertionResourceType);
	}

	public GivenClass<T> Given()
	{
		oGiven.InitGoup(oData);
		return oGiven;
	}

	public GivenClass<T> And(){
		return oGiven;
	}
}