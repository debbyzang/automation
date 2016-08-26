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
		locator.selectByValue("��ҳ", "��������", "3");
		locator.element("��ҳ", "���������").sendKeys("ŵ����");
		locator.element("��ҳ", "������ť").click();
		locator.sleep(2);
		List<WebElement> list = locator.elements("��ҳ", "��Ʒ�б�");
		check.equals(list.size()>0,true , "δ�ҵ���Ʒ");
//		check.equals(list.size(), 5,"ŵ���ǲ���5��");
		check.results("��ҳ������������");
	}
	
}