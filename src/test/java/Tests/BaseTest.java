package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest   {
	protected static WebDriver driver;
	public  WebDriverWait wait;
	//the before method
	public void setupSuite() {
		WebDriverManager.chromedriver().setup();
	}
	public void setUpmethod() {
		//		System.setProperty("webdriver.chrome.driver", "D:\\Study\\Testing\\programs\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 20);//to start use it it must be here

	}
	//after  test method  
	public void TearDown() {
		if (driver != null) {
			driver.close();//close the current window !
		}
	}



}
