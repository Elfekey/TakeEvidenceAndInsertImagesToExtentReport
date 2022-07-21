package Tests;

import Utilities.EvidenceAndScreenShots;
import org.testng.Assert;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

//packages imports
import Pages.GooglePage;
import Utilities.ScreenShotsListener;

//Listeners imports
//@Listeners(ScreenShotsListener.class)

public class t1  extends BaseTest   {
	GooglePage oGooglePage;
 EvidenceAndScreenShots evidenceAndScreenShots ;

	@BeforeSuite
	public void beforeSuite() {
		setupSuite();
	}
	@BeforeMethod
	public void beforemethods() {
		setUpmethod();
	}


//will pass
	@Test(priority = 1,description ="Test Case One Say Hi" )
	public void TC001() throws InterruptedException {
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="Test Case One Say Hi";

		oGooglePage= new GooglePage(driver);
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		driver.get("https://www.google.com");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		driver.manage().window().maximize();
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		oGooglePage.TestCase("Hi");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
//		Assert.assertTrue(false);



		//trying rename folder
	}
//will fail
	@Test(priority = 2,description = "Test CAse Two Saying Hello2")
	public void TC002() throws InterruptedException {
		//Mandatory for screenshots
//----------------------------------------------------------------------
		//object from used methods class
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="Test CAse Two Saying Hello2";
//----------------------------------------------------------------------
		driver.manage().window().maximize();
		evidenceAndScreenShots.takeFullScreenshot(testName, testDescription);
		driver.get("https://stackoverflow.com/questions/46357858/java-cannot-copy-the-images-from-one-folder-to-a-word-document-located-in-diff");

		Assert.assertTrue(false);
	}
	//will skip
	@Test(priority = 3,description = "Test case 3 False")
public void TestCaseNo3() {
		//Mandatory for screenshots
//----------------------------------------------------------------------
		//object from used methods class
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="Test case 3 False";
//----------------------------------------------------------------------
		driver.manage().window().maximize();
		evidenceAndScreenShots.takeFullScreenshot(testName, testDescription);
		driver.get("https://docs.oracle.com/en/java/javase/12/docs/api/java.base/java/nio/file");
		evidenceAndScreenShots.takeFullScreenshot(testName, testDescription);
//		throw new SkipException("Skipeed");
}

	@AfterMethod
	public void close(ITestResult result) throws InterruptedException {
		try
		{
			if(result.getStatus() == ITestResult.SUCCESS)
			{
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Passed";
				System.out.println(result.getName()+"passed **********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
			}

			else if(result.getStatus() == ITestResult.FAILURE)
			{
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Failed";

				System.out.println("Failed ***********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

			}

			else if(result.getStatus() == ITestResult.SKIP ){
				evidenceAndScreenShots= new EvidenceAndScreenShots();
				String status = "Skipped";
				System.out.println("Skipped***********");
//rename the folder to new name with status
				evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
				evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		TearDown();
	}


}
