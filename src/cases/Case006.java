package cases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import util.HttpHelper;
import base.WebSingleSuite;

public class Case006 extends WebSingleSuite {

	@Test
	public void linkCheck(){
		locator.linkTo("http://www.huicewang.com/ecshop/index.php");
		locator.sleep(2);
		locator.scrollToMiddle();
		List<WebElement> links = locator.elements("首页", "精品推荐-tab签");
		for(WebElement link : links){
			link.click();
			locator.sleep(2);
			List<String> imageLink = locator.elementsAttribute("首页", "精品推荐-图片", "src");
			check.equals(HttpHelper.URLisAvailable(imageLink), true);
			
		}
		check.results("精品区图片链接正常");
	}
}
