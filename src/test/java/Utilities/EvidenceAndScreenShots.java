package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import Tests.BaseTest;
import org.testng.annotations.Test;

public class EvidenceAndScreenShots  extends BaseTest {

	//We must extend base test to use the driver !!!
	//to set screenshots folder name and it's path for example D:/screenshots/.
	//or we can type "." then folder name to create it in the project directory
	//the below will create the folder in the project directory
	private String screenshotsFolderNameAndPath = "./screenshots/";//you have just to provide the location and foldername
	private String fullDirectory = System.getProperty("user.dir") + "/" + screenshotsFolderNameAndPath + "/";//i have to add ###test name and "/"##
	private static String currentDateAndTime;
	/**
	 * Takes screenshot of whole page and uses the current date/time as the file name
	 * LAST SHAPE
	 * Take screenshot of whole page and uses the current date/time as the file name
	 * also provide screenshots for each test case in seperate folders with test case name
	 * ## to use it in normal @test method  As Below :
	 evidenceAndScreenShots.takeFullScreenshot(testName,testDescription);
	 */
	public void takeFullScreenshot(String testCaseNameOnlyToCreateFolderForIt,String testcaseDescripion) {
		//taking the screenshot
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File file = screenshot.getScreenshotAs(OutputType.FILE);
		try {
			// add the folder path and screenshot name
			// i added the screen shot status with the path to make folder fo each run of each test case run
			//the first part untill "/" is for the folder path after that is the screenshot name
			FileUtils.copyFile(file, new File(screenshotsFolderNameAndPath + testCaseNameOnlyToCreateFolderForIt + "_"+testcaseDescripion+ "/" + testCaseNameOnlyToCreateFolderForIt + "_" + testcaseDescripion + "_" + GetCurrenDateAndTime() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/*---------------------------------------saving the images to word file--------------------------
     * Last shape
     *    to take all screenshots from a folder to word file
     *    to use it As Below :
     EvidenceAndScreenShots evidenceAndScreenShots= new EvidenceAndScreenShots();
	evidenceAndScreenShots.saveAllScreenShotsIntoWordDocument(result.getName(), result.getMethod().getDescription(),status);
     * */
	//i added the status to take images from every folder with it's status
	public void saveAllScreenShotsIntoWordDocument(String itestListenerDOTgetName, String testcaseDescription, String status) {
		try {
			// Create the docx object
			// Step 1: Creating a blank document
			XWPFDocument document = new XWPFDocument();
			// Step 2: Creating a Paragraph using
			// createParagraph() method
			XWPFParagraph paragraph
					= document.createParagraph();
			//for inputs with wordDocument
			XWPFRun run = paragraph.createRun();
			// Step 3: Creating a File output stream of word
			// document at the required location
			//the below location must be declared before ,,,i did it when i take the screenshots
			FileOutputStream fos = new FileOutputStream(
					new File(screenshotsFolderNameAndPath + itestListenerDOTgetName + "_" + testcaseDescription + "_" + status + "_" + GetCurrenDateAndTime() + ".docx"));//right place with right test name
			// Step 4 : Get the source folder and list of files (includes images and
			// sub-folders)   where we get the images
			File imagesSrcFilePath = new File(fullDirectory +itestListenerDOTgetName+"_"+testcaseDescription+"_"+status);

			//array of files to get the list of items inside the src folder path
			File[] list = imagesSrcFilePath.listFiles();

			//Step 5 : printing the number of found items
			System.out.println("Source folder item list " + list.length);

			// Step 6 : Iterate through the files in the source folder
			for (int images = 0; images < list.length; images++) {
				if (list[images].isFile()) {
					System.out.println("Found File name - " + list[images].getName());

					// Step 7 : Create fis"file input stream " for images
					FileInputStream fis = new FileInputStream(list[images].getPath());

					// adding the image type & images width and height
					int imageType = XWPFDocument.PICTURE_TYPE_PNG;
					int width = 500;
					int height = 550;

					// step 8 : adding the found images using the fis
					run.addPicture(fis, imageType,
							list[images].getPath(), Units.toEMU(width), Units.toEMU(height));
					fis.close();
				}
			}
			//Last step adding every things we opened
			document.write(fos);
			fos.close();
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


//Creating a method to get current date and time to use it in naming
	private String GetCurrenDateAndTime() {
		//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH-mm-ss-SSS");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
		LocalDateTime dateTime = LocalDateTime.now();
		currentDateAndTime = dateTime.format(formatter);//test
		return currentDateAndTime;
	}



//changeing the fodler name to the folder with status name
	public void renameScreenShotsFolder(String itestListenerDOTgetName, String testcaseDescription, String status){
		//getting the old folder directory and it's name
		File oldName = new File(fullDirectory+itestListenerDOTgetName+"_"+testcaseDescription);
		//creating new folder name and directory
		File newName = new File(fullDirectory+itestListenerDOTgetName+"_"+testcaseDescription+"_"+status);
        //if the old name is exist then rename it to the new name we created
		if (oldName.renameTo(newName)) {
			//after renaming success print this
			System.out.println("folder renamed successfully");
		} else {
			//printing this if the renaming failed
			System.out.println("Failed to rename folder");
		}



	}

	/*The below is how to use it in normal  test class

-----------test method
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
	}

	-------------after test method where we do most of things
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

	* */

}
