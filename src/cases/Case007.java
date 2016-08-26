package cases;

import java.util.Map;

import org.testng.annotations.Test;

import base.Locator;
import base.WebSuiteExcel;
import util.ExcelParser;

//(dataProvider="getData")
public class Case007 extends WebSuiteExcel {

//	ExcelParser ep = new ExcelParser("TestData/data.xls");
//	private ExcelParser ep;
//	private Locator locator;
	
	@Test(dataProvider="getDataExcel")
	public void case007(Map<String, String> map){
		
		locator.linkTo("http://www.huicewang.com/ecshop/user.php");
		System.out.println("121222222");
	}
}
