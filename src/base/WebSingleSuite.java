package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class WebSingleSuite extends TestBase{
	
	protected Locator locator ;
	protected WebDriver driver  ;
	protected CheckPoint check ;
	protected static API api;
	
	@BeforeClass
	public synchronized void beforeTest() {
		driver = DriverFactory.getDriver("chrome");
		check = new CheckPoint(this.getClass().getSimpleName());
		locator = new Locator(driver , "object.xml" , 30);
		api = new API(driver);
	}
	
	@AfterClass
	public void afterTest(){
		driver.close();
		driver.quit();
	}
	
}
