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
		List<WebElement> itemBests = locator.elements("��ҳ", "��Ʒ�Ƽ�-tabǩ");
		for(WebElement itemBest : itemBests){
			itemBest.click();
			locator.sleep(1);
			List<String> prices = locator.elementsText("��ҳ", "��Ʒ�Ƽ�-�۸�");
			for (String price : prices) {
				check.notEquals(price, "null","�۸��쳣");
			}
		}
		check.results("��ҳ��Ʒ�Ƽ������м۸�����");
	}
}
