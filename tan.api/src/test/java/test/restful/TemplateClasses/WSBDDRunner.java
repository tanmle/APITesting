package test.restful.TemplateClasses;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.runners.model.FrameworkField;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import net.serenitybdd.junit.runners.SerenityRunner;

@SuppressWarnings("unused")
public class WSBDDRunner extends SerenityRunner {

	public WSBDDRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	protected Object createTest() throws Exception {
		// Get Instance of Test Class
		Object obj = super.createTest();

		try {
			// Get Init Method in BDDStatement
			List<FrameworkField> list_field = getTestClass().getAnnotatedFields(WithResource.class);
			if (list_field.size() == 0) {
				Method method = obj.getClass().getMethod("initGroup", Class.class, Class.class);

				// Get Annotation in Test Class
				Annotation annotation = getTestClass().getAnnotation(WithResource.class);
				WithResource wg = (WithResource) annotation;

				// Invoke method
				method.invoke(obj, wg.Resource(), wg.AssertionResource());
			}
			else {
	
				// Get field's annotation and set value for that field
				for (FrameworkField each : list_field) {
					Field field = each.getField();
					Annotation annotation = field.getAnnotation(WithResource.class);
					WithResource wg = (WithResource) annotation;
					
					field.set(obj, BDDStatement.withResource(wg.Resource(), wg.AssertionResource()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}
