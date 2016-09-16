package test.restful.BaseActionsPri;

import test.restful.TemplateClasses.Dictionaries;
import test.restful.TemplateClasses.Dictionary;
import test.restful.TemplateClasses.Dictionary.TypeParam;

public class data_Primary
{ 
	public Dictionaries oDataPri;
 
	public data_Primary()
	{

		oDataPri = new Dictionaries(new Dictionary("BaseURI", "http://192.168.188.121:8000/RESTful/rest/", TypeParam.NONE));
	}
 
}