package test.features.files_management;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.io.File;
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
public class UploadFile {
	@WithResource(Resource = files.class, AssertionResource = files_assertion.class)
	public BDDStatement<files<files_assertion>> obdd;
	
	@Test
	@Issue("#API-16")
	@Title("Ability to upload any file")
	public void uploadAnyFile() throws FileNotFoundException, UnsupportedEncodingException
	{
		String fileName = UUID.randomUUID().toString().split("-")[0];
		PrintWriter writer = new PrintWriter("./src/test/java/test/data/" + fileName + ".txt");
		writer.println("The first line");
		writer.println("The second line");
		writer.close();
		
		obdd.
			Given().
				AuthBASIC("admin", "admin").
				Param("file", "./src/test/java/test/data/" + fileName + ".txt").
				
			When().
				uploadFile_upload().
			
			Then().
				hasContent("File upload successfully");
		
		new File("./src/test/java/test/data/" + fileName + ".txt").delete();
	}
}
