package test.restful.UserDefineActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import test.restful.TemplateClasses.Dictionaries;
import test.restful.TemplateClasses.Dictionary;

public class CommonLib {

	public static String Param(String param)
	{
		return "with" + param;
	}
	
	public static String hasPropertyValue(String param)
	{
		return param;
	}
	
	public static Dictionary Property(String key, Object value)
	{
		return new Dictionary(key, value, Dictionary.TypeParam.NONE);
	}
	
	public static Dictionary Param(String key, Object value)
	{
		return new Dictionary(key, value, Dictionary.TypeParam.PARAM);
	}
	
	public static JSONObject getJsonObjectFromStrings(Dictionaries dictionaries, String[] params)
	{
		JSONObject obj = new JSONObject();
		
		for (String param : params)
		{
			if (dictionaries.get(param) == null)
				obj.put(param, "");
			else
				obj.put(param, dictionaries.get(param));
		}
		return obj;
	}
	
	public static JSONObject getJsonObjectFromStrings(String separate, String[] params)
	{
		JSONObject obj = new JSONObject();
		String[] temp;
		for (String param : params)
		{
			temp = param.split(separate);
			obj.put(temp[0], temp[1]);
		}
		return obj;
	}
	
	public static JSONObject getJsonObjectFromDictionaries(Dictionary[] params)
	{
		JSONObject obj = new JSONObject();
		for (Dictionary param : params)
			obj.put(param.getKey(), param.getValue());
		
		return obj;
	}
	
	public static JSONObject convertStringToJson(String content)
	{
		return new JSONObject(content);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map ConvertParamsToMap(String[] sParams)
	{
		Map mParams = new HashMap();
		String[] temp;
		for (String sParam : sParams)
		{
			temp = sParam.split("=");
			mParams.put(temp[0], temp[1]);
		}
		return mParams;
	}
}
