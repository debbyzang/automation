package base;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class WebSuite extends TestBase{

	public static List<String> resultLog = new ArrayList<String>();
	protected static WebDriver driver = null ; 
	protected CheckPoint check = null;
	protected static Locator locator ;
	
	@BeforeSuite
	@Parameters("type")
	public void setup(String type){
		driver = DriverFactory.getDriver(type);
		locator = new Locator(driver, "object.xml", 30);
	}
	
	@BeforeClass
	public void beforeClass(){
		check = new CheckPoint(this.getClass().getSimpleName());
	}
	
	@AfterTest
	public void tearup(){
		driver.close();
		driver.quit();
	}
}
