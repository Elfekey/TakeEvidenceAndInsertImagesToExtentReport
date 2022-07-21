package Utilities;


import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//Importing Input output package for basic file handling
//Importing Apache POI package

public class ScreenShotsListener  extends EvidenceAndScreenShots {//implements ITestListener {
	//to make it clean as possible i defined the screenshot method in the parent class
	//synchronized is used to parallel 
//	@Override
//	public synchronized void onTestSuccess(ITestResult result) {
//		String status = "Passed";
//		takeFullScreenshot(result.getName(),result.getName(),result.getMethod().getDescription(), status);//status PAssed/Failed/Skipped Ect...
//		saveAllScreenShotsIntoWordDocument(result.getName(),result.getMethod().getDescription(),status);  //status passed/failed/skiped ect....
//
//	}
	//synchronized is used to parallel 
//	@Override
//	public synchronized void onTestFailure(ITestResult result) {
//		String status = "Failed";
//		takeFullScreenshot(result.getName(),result.getName(),result.getMethod().getDescription(), status);//status PAssed/Failed/Skipped Ect...
//		saveAllScreenShotsIntoWordDocument(result.getName(),result.getMethod().getDescription(),status);  //status passed/failed/skiped ect....
//
//	}

//	@Override
//	public void onTestSkipped(ITestResult result) {
//		String status = "Skipped";
//		takeFullScreenshot(result.getName(),result.getName(),result.getMethod().getDescription(), status);//status PAssed/Failed/Skipped Ect...
//		saveAllScreenShotsIntoWordDocument(result.getName(),result.getMethod().getDescription(),status);  //status passed/failed/skiped ect....
//
//
//	}
}




