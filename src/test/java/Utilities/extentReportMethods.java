package Utilities;

import Tests.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;
import java.io.File;
public class extentReportMethods  extends BaseTest {
    /*we'll need the below dependency
     <!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->
    <dependency>
      <groupId>com.aventstack</groupId>
      <artifactId>extentreports</artifactId>
      <version>5.0.9</version>
    </dependency>
    * */
   private ExtentSparkReporter htmlReporter;
    private ExtentReports extent;
    private ExtentTest test;

    @BeforeTest
    public void SetUpExtentReport() {
        setupSuite();
    /*The ExtentHtmlReporter is used for creating an HTML file, and it accepts a file path as a parameter.
    The file path represents the path in which our extent report would be generated.
    *#also to it's object we set the configuration of the report html page
    * */
//    ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentReport.html");
//    ExtentReports
//   The ExtentReports class is used for creating the tests.
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        htmlReporter.config().setDocumentTitle("Automation Report");
        htmlReporter.config().setReportName("report Name");
//        htmlReporter.config().setTimeStampFormat("EEEE, dd  MMMM , yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setTimeStampFormat("EEEE, dd  MMMM , yyyy, hh:mm a ");
        htmlReporter.config().setTheme(Theme.STANDARD);//Theme.DARK   or  Theme.STANDARD
    }

    @BeforeMethod
    public synchronized void beforeMethod(ITestResult result) {
        // -The ExtentTest class is used for generating the logs in the Extent Report.
        test = extent.createTest(result.getMethod().getMethodName() + "_" + result.getMethod().getDescription());
        setUpmethod();
    }

    ///BIG TRY
    EvidenceAndScreenShots evidenceAndScreenShots ;

    @Test(description = "This test case one now")
    public void TestCaseOne() {
        //getting the current test name
        String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
        // because the parameter for @ must be constant
        String testDescription ="This test case one now";
        evidenceAndScreenShots= new EvidenceAndScreenShots();
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

        driver.manage().window().maximize();
        driver.get("https://www.lambdatest.com/blog/extent-reports-in-selenium/");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        driver.navigate().to("https://www.google.com/?client=safari");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

    }

    @Test(description = "This test case Two now")
    public void TestCaseTwo() {
        //
        //getting the current test name
        String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
        // because the parameter for @ must be constant
        String testDescription ="This test case Two now";
        evidenceAndScreenShots= new EvidenceAndScreenShots();
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        driver.navigate().to("https://www.google.com/?client=safari");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        driver.navigate().to("https://www.facebook.com");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        Assert.assertTrue(false);
    }

    @Test(description = "This test case Three now")
    public void TestCaseThree() {
        //
        //getting the current test name
        String testName =new Object(){}.getClass().getEnclosingMethod().getName() ;
        // because the parameter for @ must be constant
        String testDescription ="This test case Three now";
        evidenceAndScreenShots= new EvidenceAndScreenShots();
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);

        driver.manage().window().maximize();
        driver.get("https://www.youtube.com/");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        driver.navigate().to("https://www.google.com/?client=safari");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        driver.navigate().to("https://www.facebook.com");
        evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
        throw  new SkipException("Skipped :D ");
    }
    @AfterMethod
    public synchronized void after(ITestResult result) {
        if (result.getStatus() == ITestResult.SUCCESS) {
            evidenceAndScreenShots= new EvidenceAndScreenShots();
            String status = "Passed";
            System.out.println(result.getName()+"passed **********");
//rename the folder to new name with status
            evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
            evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

            //passing to the report
            test.log(Status.PASS, result.getTestName());
            //to add screenshot we need it's path and name
//     test.addScreenCaptureFromPath("/Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/screenshots/TC001_Test Case One Say Hi_Passed/TC001_Test Case One Say Hi_21-07-2022 04-51-21.png");
        InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
            extent.flush();
        } else if (result.getStatus() == ITestResult.FAILURE) {
            evidenceAndScreenShots= new EvidenceAndScreenShots();
            String status = "Failed";
            System.out.println(result.getName()+ "Failed **********");
//rename the folder to new name with status
            evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
            evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);

            test.log(Status.FAIL, result.getThrowable());
            //to add screenshot we need it's path and name
            InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
          extent.flush();
        } else {
            evidenceAndScreenShots= new EvidenceAndScreenShots();
            String status = "Skipped";
            System.out.println(result.getName()+"Skipped **********");
//rename the folder to new name with status
            evidenceAndScreenShots.renameScreenShotsFolder(result.getName(), result.getMethod().getDescription(),status);
//save screenshots to word evidence file
            evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);


            test.log(Status.SKIP, result.getTestName());
            //to add screenshot we need it's path and name
            InsertAllImagesToTheReport(result.getName(),result.getMethod().getDescription(),status);
          extent.flush();
        }
        driver.close();
    }
    @AfterTest
    public void afterTest() {
//    we can use the flush()method, which is used for removing any previous data and creating a new Extent Report in Selenium.
//        extent.flush();
//        driver.quit();
    }

    @AfterSuite
    public void afterSuite() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("file:///Users/h-elfekey/Desktop/TakeEvidenceScreenShotsToWordDoc-main2/Reports/extentReport.html");
    }

/*##############
    In a nutshell,

    1-The ExtentHtmlReporter or "ExtentSparkReporter"  class is used for creating the HTML reports with it's configurations .
    2-The ExtentReports class is used for creating the tests.
    3-The ExtentTest class is used for generating the logs in the Extent Report.
*/

//we can take the below from the screenshots evidence class object
    private String screenshotsFolderNameAndPath = "./screenshots/";//you have just to provide the location and foldername
    private String fullDirectory = System.getProperty("user.dir") + "/" + screenshotsFolderNameAndPath + "/";//i have to add ###test name and "/"##
    public synchronized void InsertAllImagesToTheReport(String itestListenerDOTgetName, String testcaseDescription, String status) {
        // sub-folders)   where we get the images
        File imagesSrcFilePath = new File(fullDirectory + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status);
       String imagesPath = fullDirectory + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status+"/";
        //array of files to get the list of items inside the src folder path
        File[] list = imagesSrcFilePath.listFiles();

        //Step 5 : printing the number of found items
        System.out.println("Source folder item list " + list.length);

        // Step 6 : Iterate through the files in the source folder
        for (int images = 0; images < list.length; images++) {
            if (list[images].isFile()) {
                System.out.println("Found File name - " + list[images].getName());

                test.addScreenCaptureFromPath(imagesPath+list[images].getName());

            }

        }
    }
}