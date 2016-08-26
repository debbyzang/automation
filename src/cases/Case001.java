package cases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.Log;
import base.WebSuite;

@Test  //(retryAnalyzer=listener.TestRetryAnalyzer.class)
public class Case001 extends WebSuite {
	
	
	public void findItem()  {
		
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.sleep(2);
		locator.selectByValue("首页", "搜索分类", "3");
		locator.element("首页", "搜索输入框").sendKeys("诺基亚");
		locator.element("首页", "搜索按钮").click();
		locator.sleep(2);
		List<WebElement> list = locator.elements("首页", "商品列表");
		check.equals(list.size()>0,true , "未找到商品");
//		check.equals(list.size(), 5,"诺基亚不是5个");
		check.results("首页搜索功能正常");
	}
	
}