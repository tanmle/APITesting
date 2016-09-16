package test.restful.TemplateClasses;

public class Dictionary {
	
	public enum TypeParam{
		PARAM,
		HEADER,
		COOKIE,
		NONE
	}

	private String key;
	private Object value;
	private TypeParam type;
	
	public Dictionary(String key, Object value, TypeParam type)
	{
		this.key = key;
		this.value = value;
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
	public TypeParam getType()
	{
		return type;
	}
}
