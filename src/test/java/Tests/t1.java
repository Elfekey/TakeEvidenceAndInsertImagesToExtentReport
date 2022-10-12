package Tests;

import org.testng.annotations.Test;
import Utilities.WordDocumentEvidence;
import Utilities.extentReport;
import Utilities.retryFailedTCs;
import Utilities.screenShots;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import com.aventstack.extentreports.Status;


//Listeners imports
//@Listeners(ScreenShotsListener.class)


public class t1  extends BaseTest   {
	/*we'll need the below dependency
    <!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->
   <dependency>
     <groupId>com.aventstack</groupId>
     <artifactId>extentreports</artifactId>
     <version>5.0.9</version>
   </dependency>
	 * */
	//global varibles
	
	//classes objects that we'll use




//	@BeforeSuite
//	public synchronized void beforeSuite() {
//		setupSuite();
//		report = new extentReport();
//		report.setUpExtent();
//		report.open_reportPage();
//
//	}
//
//	@BeforeMethod
//	public void beforeM(ITestResult result) {
//	beforeMethod(result);
//	}
//	
//	public synchronized void beforeMethod(ITestResult result) {
////		setUpmethod();
////		setUplistener();
//
//		//creating the test for the report
//		report.startTest(result.getMethod().getMethodName(),result.getMethod().getDescription());
//
//		//getting tc name and description
//		tCName =result.getMethod().getMethodName();
//		tCDescription=result.getMethod().getDescription();
//	
//	}

	@Test(description = "This is test case one now")
	public void TestCaseOne() {
//		beforeMethod();
//		setUplistener();
//		report.startTest(tCName,tCDescription);

			//		screenShotOb= new screenShots();
			//		screenShotOb.takeFullScreenshot(testName,testDescription);
			//		report.test.log(Status.PASS, "First Screen Ok")
			eDriver.navigate().to("https://www.lambdatest.com/blog/extent-reports-in-selenium/");
			//		screenShotOb.takeFullScreenshot(testName,testDescription);
			//		report.test.log(Status.PASS, "Second Screen Ok");
			eDriver.navigate().to("https://www.google.com/?client=safari");
			//		screenShotOb.takeFullScreenshot(testName,testDescription);
			//		report.test.log(Status.PASS, "Third Screen Ok");
	

	}

	@Test(description = "This is test case Two from class t1 now",retryAnalyzer = retryFailedTCs.class)
	public void TestCaseTwo() {
//		beforeMethod();
//		setUplistener();

			//		screenShotOb= new screenShots();
			//		screenShotOb.takeFullScreenshot(testName,testDescription);
			//		report.test.log(Status.PASS, "First Screen Ok");

			driver.navigate().to("https://www.google.com/");
			screenShotsOb.takeFullScreenshot(tCName,tCDescription,driver);
			driver.navigate().to("https://stackoverflow.com/questions/6912169/eclipse-enable-autocomplete-content-assist");
			screenShotsOb.takeFullScreenshot(tCName,tCDescription,driver);
			throw new SkipException("Skipppppp");
	
//Assert.assertTrue(false);

	}

	@AfterMethod
	public void afterM(ITestResult result) {
		afterMethod( result);
	}
//	public synchronized void afterMethod(ITestResult result) {
		
//		try {
//			wordDocumentEvidence = new WordDocumentEvidence();
//			if (result.getStatus() == ITestResult.SUCCESS) {
//
//				screenShotOb= new screenShots();
//				String status = "Passed";
//				System.out.println(result.getName()+"passed **********");
//				//rename the folder to new name with status
//				screenShotOb.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//				//save screenshots to word evidence file
//				wordDocumentEvidence.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
//				//passing to the report
//				report.test.log(Status.PASS, result.getMethod().getMethodName()+"  :  "+status);
//				//to add screenshot we need it's path and name
//				report.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
//				report.refreshReport();
//				TearDown();
//			} else if (result.getStatus() == ITestResult.FAILURE) {
//				screenShotOb= new screenShots();
//				String status = "Failed";
//				System.out.println(result.getName()+"Failed **********");
//				//rename the folder to new name with status
//				screenShotOb.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//				//save screenshots to word evidence file
//				Thread.sleep(3000);
//				wordDocumentEvidence.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
//				//passing to the report
//				report.test.log(Status.FAIL,result.getMethod().getMethodName()+"  :  "+status);
//				//to add screenshot we need it's path and name
//				//           evidenceAndScreenShots.   test.addScreenCaptureFromPath("/Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/screenshots/TC001_Test Case One Say Hi_Passed/TC001_Test Case One Say Hi_21-07-2022 04-51-21.png");
//				report.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
//				report.refreshReport();
//				TearDown();
//
//			} else {
//				screenShotOb= new screenShots();
//				String status = "Skiped";
//				System.out.println(result.getName()+"Skiped **********");
//				//rename the folder to new name with status
//				screenShotOb.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//				//save screenshots to word evidence file
//				wordDocumentEvidence .saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
//
//				//passing to the report
//				report.test.log(Status.SKIP, result.getMethod().getMethodName()+"  :  "+status);
//				//to add screenshot we need it's path and name
//				report.InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
//				report.refreshReport();
//				TearDown();
//
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

//	}



//
//	@AfterSuite
//	public void afterSuite() {
//		report.refreshReport();
//
//	}

	/*##############
   In a nutshell,

   1-The ExtentHtmlReporter or "ExtentSparkReporter"  class is used for creating the HTML reports with it's configurations .
   2-The ExtentReports class is used for creating the tests.
   3-The ExtentTest class is used for generating the logs in the Extent Report.
	 */




}
