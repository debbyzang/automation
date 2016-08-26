package cases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import base.WebSuite;
@Test
public class Case004 extends WebSuite{

	public void priceCheck(){
		
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.scrollToMiddle();
		
		locator.sleep(2);
		List<WebElement> itemBests = locator.elements("首页", "精品推荐-tab签");
		for(WebElement itemBest : itemBests){
			itemBest.click();
			locator.sleep(1);
			List<String> prices = locator.elementsText("首页", "精品推荐-价格");
			for (String price : prices) {
				check.notEquals(price, "null","价格异常");
			}
		}
		check.results("首页精品推荐区所有价格正常");
	}
}
