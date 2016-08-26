package cases;

import java.util.Map;
import org.openqa.selenium.By;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.WebSingleSuite;

public class Case003 extends WebSingleSuite{

	String name, pwd ;
	
	@Test
	@Parameters({"name" , "pwd"})
	public void  order(){
		
		//������Ʒ
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.sleep(2);
//		api.addToCookies();
		locator.element("��¼ҳ", "�û���").sendKeys("debby");
		locator.element("��¼ҳ", "����").sendKeys("123123");
		locator.element("��¼ҳ", "������¼").click();
		locator.linkTo("http://www.huicewang.com/ecshop/");
		locator.sleep(2);
		locator.selectByValue("��ҳ", "��������", "8");
		locator.sleep(2);
		locator.element("��ҳ", "���������").sendKeys("����");
		locator.element("��ҳ", "������ť").click();
		
		//ȥ����
		driver.findElement(By.xpath("//a[@href='javascript:addToCart(7)']")).click();
		driver.get("http://www.huicewang.com/ecshop/flow.php?step=cart");
		driver.findElement(By.xpath("//img[@alt='checkout']")).click();
		driver.get("http://www.huicewang.com/ecshop/flow.php?step=checkout");
		locator.sleep(2);
		System.out.println("׼���ύ...");
		driver.findElement(By.xpath("//input[@type='image']")).click();
		locator.sleep(2);
		String orderid_submit = driver.findElement(By.xpath("//h6/font")).getText().trim();
		
		Map<String, String> info = locator.searchOrderInfo(orderid_submit);
		check.assertNotNull(info, "�����Ų�����");
//		Pattern num = Pattern.compile("20[1-9][0-9][0-1][0-9]{8}");
//		Matcher matcher = num.matcher(orderid_submit);          
//		if(matcher != null){
//			check.results("�µ��ɹ�");
//		}
	}
	
//	public boolean searchOrderInfo(){
//		List<WebElement> orders = driver.findElements(By.xpath("//div[@class='userCenterBox boxCenterList clearfix']/table//tr"));
//		for (WebElement s : orders) {
//			System.out.println(s.getText());
//		}
//		Map<String, String> map = new HashMap<>();
//		
//		
//		for(int i = 1 ; i<11 ; i++){
//			String[] array = orders.get(i).getText().split("\\s");
//			for(int j = 0 ; j<array.length ; j++){
//				map.put(array[j], array[1] + array[2] + array[3] + array[4]);
//			}
//		}
//		
//		return true ;
//	}
}
