package Tests;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

public class testParalel {

	@BeforeMethod
	public void beforemethod() {
		WebDriverManager.chromedriver().setup();
	}
	
	
	
	WebDriver driver;	
	public  void setupDriver(){
		driver = new ChromeDriver();
	}
	public  void teardown(){
driver.close();
	}


	@Test(description = "This test case one now",priority = 1)
	public void TestCaseOne() {
		setupDriver();
		
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/blog/extent-reports-in-selenium/");

		driver.navigate().to("https://www.google.com/?client=safari");
//teardown();
	}

	@Test(description = "This test case Two now",priority = 2)
	public void TestCaseTwo() {
		setupDriver();
//		WebDriver 	driver = new ChromeDriver();
		//

		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		driver.navigate().to("https://www.google.com/?client=safari");
		Assert.assertTrue(false);
	
	}

	@Test(description = "This test case Three now",priority = 3)
	public void TestCaseThree() {
//		WebDriver 	driver = new ChromeDriver();
		setupDriver();
		driver.manage().window().maximize();
		driver.get("https://www.youtube.com/");
		throw  new SkipException("Skipped 2 ");
		
	}


	@AfterMethod
	public synchronized void after(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			System.out.println(result.getName()+"passed **********");
			teardown();
		                }
		else if (result.getStatus() == ITestResult.FAILURE) {
			//			evidenceAndScreenShots= new EvidenceAndScreenShots();
			System.out.println(result.getName()+ "Failed **********");
			teardown();
		} else {
			//			evidenceAndScreenShots= new EvidenceAndScreenShots();
			System.out.println(result.getName()+"Skipped **********");
			teardown();
		}
	}



}
