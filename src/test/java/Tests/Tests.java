package Tests;



import Utilities.EvidenceAndScreenShots;

import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

public class Tests  extends BaseTest {
	EvidenceAndScreenShots evidenceAndScreenShots ;
//	public WebDriver dr;
	@BeforeSuite
	public void SetUpExtentReport() {
		setupSuite();

		evidenceAndScreenShots= new EvidenceAndScreenShots();
		evidenceAndScreenShots.setUpExtent();
	}

	@BeforeMethod
	public synchronized void beforeMethod(ITestResult result) {
		// -The ExtentTest class is used for generating the logs in the Extent Report.
		evidenceAndScreenShots.test = evidenceAndScreenShots.extent.createTest(result.getMethod().getMethodName() +"_" + result.getMethod().getDescription());
		setupSuite();
		setUpmethod();
//		evidenceAndScreenShots= new EvidenceAndScreenShots();
//		evidenceAndScreenShots.setUpExtent();
	}



	@Test(description = "This test case one now")
	public void TestCaseOne() {
//		setUpmethod(dr);

		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="This test case one now";
		evidenceAndScreenShots= new EvidenceAndScreenShots();

		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		evidenceAndScreenShots.test.log(Status.PASS, "first screen ok");
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/blog/extent-reports-in-selenium/");
		evidenceAndScreenShots.test.log(Status.PASS, "Navigated successfully");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		//				Assert.assertTrue(false);

		driver.navigate().to("https://www.google.com/?client=safari");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		evidenceAndScreenShots.test.log(Status.PASS, "Navigated Again successfully");

	}

	@Test(description = "This test case Two now")
	public void TestCaseTwo() {
		//
//		setUpmethod();

		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="This test case Two now";
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		evidenceAndScreenShots.test.log(Status.PASS, "Started");

		driver.navigate().to("https://www.google.com/?client=safari");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		evidenceAndScreenShots.test.log(Status.PASS, "second navigate done");
		Assert.assertTrue(false);

		driver.navigate().to("https://www.facebook.com");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		evidenceAndScreenShots.test.log(Status.PASS, "Navigated Finally");
		

	}

	@Test(description = "This test case Three now")
	public void TestCaseThree() {
//		setUpmethod();

		//getting the current test name
		String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
		// because the parameter for @ must be constant
		String testDescription ="This test case Three now";
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
		throw  new SkipException("Skipped 2 ");
//		
//		driver.navigate().to("https://www.google.com/?client=safari");
//		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
//		driver.navigate().to("https://www.facebook.com");
//		evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
//		throw  new SkipException("Skipped :D ");
	}
	
	
	@AfterMethod
	public synchronized void after(ITestResult result) {
		evidenceAndScreenShots= new EvidenceAndScreenShots();
		if (result.getStatus() == ITestResult.SUCCESS) {
			String status ="Passed";
			System.out.println(result.getName()+"passed **********");
			//rename the folder to new name with status
			evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
			//save screenshots to word evidence file
			evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
			//to add screenshot we need it's path and name
			//     test.addScreenCaptureFromPath("/Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/screenshots/TC001_Test Case One Say Hi_Passed/TC001_Test Case One Say Hi_21-07-2022 04-51-21.png");
			evidenceAndScreenShots.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
			//passing to the report
			evidenceAndScreenShots.test.log(Status.PASS, result.getName()+"_"+result.getMethod().getDescription());

		} else if (result.getStatus() == ITestResult.FAILURE) {
			//			evidenceAndScreenShots= new EvidenceAndScreenShots();
			String status = "Failed";
			System.out.println(result.getName()+ "Failed **********");
			//rename the folder to new name with status
			evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
			//save screenshots to word evidence file
			evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
			//to add screenshot we need it's path and name
			//     test.addScreenCaptureFromPath("/Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/screenshots/TC001_Test Case One Say Hi_Passed/TC001_Test Case One Say Hi_21-07-2022 04-51-21.png");
			evidenceAndScreenShots.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
			//passing to the report
			evidenceAndScreenShots.test.log(Status.FAIL, result.getName()+"_"+result.getMethod().getDescription());

		} else {
			//			evidenceAndScreenShots= new EvidenceAndScreenShots();
			String status = "Skipped";
			System.out.println(result.getName()+"Skipped **********");
			//rename the folder to new name with status
			evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
			//save screenshots to word evidence file
			evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
			//to add screenshot we need it's path and name
			//     test.addScreenCaptureFromPath("/Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/screenshots/TC001_Test Case One Say Hi_Passed/TC001_Test Case One Say Hi_21-07-2022 04-51-21.png");
			evidenceAndScreenShots.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
			//passing to the report
			evidenceAndScreenShots.test.log(Status.SKIP, result.getName()+"_"+result.getMethod().getDescription());

		}
		TearDown();
		evidenceAndScreenShots.extent.flush();
		evidenceAndScreenShots.driver_extent.navigate().refresh();

	}

	@AfterSuite
	public void afterSuite() {
		evidenceAndScreenShots.driver_extent.navigate().refresh();

	}
	/*##############
    In a nutshell,

    1-The ExtentHtmlReporter or "ExtentSparkReporter"  class is used for creating the HTML reports with it's configurations .
    2-The ExtentReports class is used for creating the tests.
    3-The ExtentTest class is used for generating the logs in the Extent Report.
	 */


}