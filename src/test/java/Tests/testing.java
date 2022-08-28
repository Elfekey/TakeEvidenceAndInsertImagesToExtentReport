package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import Utilities.screenShots;
import io.github.bonigarcia.wdm.WebDriverManager;

public class testing {

	@Test
	public void testCase() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to("https://www.lambdatest.com/blog/extent-reports-in-selenium/");
		screenShots sc= new screenShots();
		sc.takeFullScreenshot("tc1", null);
		driver.navigate().to("https://www.google.com/?client=safari");
		sc.takeFullScreenshot("tc1", null);
		driver.close();
	}
}
