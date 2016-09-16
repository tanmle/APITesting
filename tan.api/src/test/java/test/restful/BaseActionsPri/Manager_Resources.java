package test.restful.BaseActionsPri;

import net.thucydides.core.annotations.Steps;
import test.restful.TemplateClasses.BaseResource;
import test.restful.UserDefineActions.*;

public class Manager_Resources<T> extends BaseResource<T>
{ 

	@Steps
	files ofiles;
	public files files() { return ofiles; }

	@Steps
	users ousers;
	public users users() { return ousers; }

	@Steps
	caches ocaches;
	public caches caches() { return ocaches; }

	@Steps
	other oother;
	public other other() { return oother; }

	@Steps
	customers ocustomers;
	public customers customers() { return ocustomers; }

	@Steps
	annotations oannotations;
	public annotations annotations() { return oannotations; }
 
}