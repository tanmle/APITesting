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
public class DownloadFile {
	@WithResource(Resource = files.class, AssertionResource = files_assertion.class)
	public BDDStatement<files<files_assertion>> obdd;
	
	@Test
	@Issue("#API-21")
	@Title("Ability to download a exist file")
	public void donwloadFile()
	{
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("filename", "a79bc7a2.txt").
			
		When().
			downloadFile_filename().
		
		Then().
			hasStatusCode(200).
			hasContent("The first line");
	}
	
	@Test
	@Issue("#API-22")
	@Title("Can not download a non-exist file")
	public void downloadNonExistFile()
	{
		String fileName = "nonexistfile.txt";
		
		obdd.
		Given().
			AuthBASIC("admin", "admin").
			Param("filename", fileName).
			
		When().
			downloadNonExistFile().
		
		Then().
			hasContent("File " + fileName + " not found!").
			hasStatusCode(211);
	}
}
