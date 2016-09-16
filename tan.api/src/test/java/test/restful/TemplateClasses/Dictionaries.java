package test.restful.TemplateClasses;

import java.util.ArrayList;
import java.util.List;

import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Headers;

import test.restful.TemplateClasses.Dictionary.TypeParam;

public class Dictionaries {
	private List<Dictionary> dictionaries;
	private List<Dictionary> _listParams;
	private List<Dictionary> _listHeaders;
	private List<Dictionary> _listCookies;

	public Dictionaries() {
		dictionaries = new ArrayList<Dictionary>();
		_listParams = new ArrayList<Dictionary>();
		_listHeaders = new ArrayList<Dictionary>();
		_listCookies = new ArrayList<Dictionary>();
	}

	public Dictionaries(Dictionary... dicts) {

		dictionaries = new ArrayList<Dictionary>();
		for (Dictionary dict : dicts)
			dictionaries.add(dict);
	}

	public void put(String key, Object value, TypeParam type) {
		put(new Dictionary(key, value, type));
	}

	public void put(Dictionary dictionary) {
		dictionaries.add(dictionary);
		DistributeToGroup(dictionary);
	}

	public Dictionary[] toArray() {
		return dictionaries.toArray(new Dictionary[dictionaries.size()]);
	}

	public void DistributeToGroup(Dictionary dictionary) {

		switch (dictionary.getType()) {
		case PARAM:
			_listParams.add(dictionary);
			break;
		case COOKIE:
			_listCookies.add(dictionary);
			break;
		case HEADER:
			_listHeaders.add(dictionary);
			break;
		default:
			break;
		}
	}

	public Object get(List<Dictionary> list, String key) {
		for (int i = 0; i < list.size(); i++)
			if (list.get(i).getKey().equals(key))
				return list.get(i).getValue();
		return null;
	}

	public Object get(String key) {
		for (int i = 0; i < dictionaries.size(); i++)
			if (dictionaries.get(i).getKey().equals(key))
				return dictionaries.get(i).getValue();
		return null;
	}
	
	public Object getParam(String key) {
		return get(_listParams, key);
	}
	
	public List<Dictionary> getParams() {	
		return _listParams;
	}

	public List<Dictionary> getCookies() {
		return _listCookies;
	}

	public Headers getHeaders() {
		List<Header> headers = new ArrayList<Header>();
		for (Dictionary header : _listHeaders)
			headers.add(new Header(header.getKey(), (String) header.getValue()));
		return new Headers(headers);
	}
}
