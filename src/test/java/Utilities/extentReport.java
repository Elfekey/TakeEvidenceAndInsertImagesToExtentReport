package Utilities;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class extentReport extends screenShots {

    //Extent Report setup
    /*The ExtentHtmlReporter is used for creating an HTML file, and it accepts a file path as a parameter.
    The file path represents the path in which our extent report would be generated.
    *#also to it's object we set the configuration of the report html page
    * */
//    ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/extentReport.html");
    public   ExtentSparkReporter htmlReporter;
	public static ExtentReports extent;
	public  static ExtentTest test;
	public  static  WebDriver extent_driver;
	//#######if i removed the static it'll crash!!!
	
    public void setUpExtent() {
      
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
    
    //open the driver of the report
    public void open_reportPage() {
    	extent_driver = new ChromeDriver();
    	  //refresh the content before anything
    	refreshReport();
    	extent_driver.navigate().to("file:"+System.getProperty("user.dir")+"/Reports/extentReport.html");
    	extent_driver.manage().window().maximize();
    	
    }
    
    //refresh the report method
    public synchronized void refreshReport() {
    	extent.flush();
    	extent_driver.navigate().refresh();
    }
    //create test method
    public synchronized void startTest(String tCName,String tCDescription) {
    	test = extent.createTest(tCName + "_" +tCDescription);
    }
    
	//we can take the below from the screenshots evidence class object
//  private String screenshotsFolderNameAndPath = "./screenshots/";//you have just to provide the location and foldername
//  private String fullDirectory = System.getProperty("user.dir") + "/" + screenshotsFolderNameAndPath + "/";//i have to add ###test name and "/"##
  public synchronized void InsertAllImagesToTheReport(String tCName, String tCDescription, String status) {
	  try {
		
      // sub-folders)   where we get the images
      File imagesSrcFilePath = new File(fullDirectory + tCName + "_" + tCDescription + "_" + status+"_"+lastTimeOfTestCase);
  
      //####check first if the screenshots folder exists####
		if (imagesSrcFilePath.exists() && imagesSrcFilePath.isDirectory()) {
		
     String imagesPath = fullDirectory+tCName+"_"+tCDescription+"_"+status+"_"+lastTimeOfTestCase+"/";
// fullDirectory+tCName+"_"+tCDescription+"_"+status+"_"+lastTimeOfTestCase    
     //fullDirectory+tCName+"_"+tCDescription+"_"+status+"_"+lastTimeOfTestCase
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
		
      System.out.println("Images Inserted to report");
		}//if end
		else {
			throw new Exception("The screenShots Folder Not Exist to add the images to report");
		}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		}

}
