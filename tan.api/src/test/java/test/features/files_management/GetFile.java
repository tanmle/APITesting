package test.features.files_management;

import org.junit.Test;
import org.junit.runner.RunWith;
import net.thucydides.core.annotations.Issue;
import net.thucydides.core.annotations.Title;
import test.restful.TemplateClasses.BDDStatement;
import test.restful.TemplateClasses.WSBDDRunner;
import test.restful.TemplateClasses.WithResource;
import test.restful.UserDefineActions.files;
import test.restful.UserDefineActions.files_assertion;

@RunWith(WSBDDRunner.class)

public class GetFile {
	@WithResource(Resource = files.class, AssertionResource = files_assertion.class)
	public BDDStatement<files<files_assertion>> obdd;
	
	@Test
	@Issue("#API-17")
	@Title("Ability to get all files")
	public void getAllFile()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			
		When().
			getListFile_files().
		
		Then().
			isJSONValid().
			hasStatusCode(200);
	}
	
	@Test
	@Issue("#API-18")
	@Title("Ability to get content of exist file")
	public void getContentOfExistFile()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("filename", "a79bc7a2.txt").
			
		When().
			downloadContentFile_filename().
		
		Then().
			hasContent("The first line");
	}
	
	@Test
	@Issue("#API-19")
	@Title("Can not get content of non-exist file")
	public void getContentOfNonExistFile()
	{
		String fileName = "nonexistfile.txt";
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("filename", fileName).
			
		When().
			getContentOfNonExistFile().
		
		Then().
			hasContent("File " + fileName + " not found!").
			hasStatusCode(211);
	}
	
	@Test
	@Issue("#API-20")
	@Title("Can not get content of file when missing file name")
	public void getContentWhenMissingFile()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			
		When().
			getContentMissingFile().
		
		Then().
			hasStatusCode(404);
	}
}
