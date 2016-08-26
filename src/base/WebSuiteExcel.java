package base;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import util.ExcelParser;

public class WebSuiteExcel {

	protected static WebDriver driver ; 
	protected Locator locator;
	protected static ExcelParser ep;
	protected static String filePath = "TestData/data.xls";
	
	static{
//		System.out.println("111========111");
		try {
			ExcelParser ep = new ExcelParser(filePath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		System.out.println(ep.toString());
	}
	
	@BeforeSuite
	public void setup(){
		driver = DriverFactory.getDriver("chrome");
		locator = new Locator(driver, "object.xml", 30);
	}
	
	@BeforeClass
	public void classTest(){
		
	}
	
	@AfterSuite
	public void teardown(){
		driver.close();
		driver.quit();
	}
	
	public void init() throws Exception{
		if (ep == null) {
			ep = new ExcelParser(filePath);
		}
	}
	
	@DataProvider
	public Object[][] getDataExcel(Method method) throws Exception{
		this.init();
		File file = new File(filePath);
		Map<String, String> map = ep.getData(method.getName());
		Object[][] object = new Object[map.size()][];
		if(file.exists() && map.size()>0){
			
			for(int i=0;i<map.size();i++){
				Object[] temp = new Object[]{map};
				object[i] = temp;
			}
			
		}else{
			System.out.println("数据有误，请核对");
		}
		return object;
		
	}
}
