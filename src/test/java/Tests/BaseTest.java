package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.WebDriverEventListener;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest   {
	protected    WebDriver driver;
	public  WebDriverWait wait;
	
	//event listener
	 WebDriverEventListener webDriverEventListener ;
	public static EventFiringWebDriver  eDriver;
	
	//the before method
	public void setupSuite() {
		WebDriverManager.chromedriver().setup();
	}
	public synchronized void SetUpDriver() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
//		wait = new WebDriverWait(driver, 20);//to start use it it must be here

	}
	//after  test method  
	public synchronized void TearDown() {
		if (eDriver != null) {
			eDriver.close();//close the current window !
		}
	}

	
	//event listener implementation 	
	public synchronized void setUplistener() {

	webDriverEventListener = new WebDriverEventListener();
	eDriver = new EventFiringWebDriver(this.driver);
	eDriver.register(webDriverEventListener);

	}

}
