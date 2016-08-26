package base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class API {
	public static String url1 = "https://www.baidu.com";
	public static String url2 = "file:///F:/%E6%B5%8B%E8%AF%95%E5%AD%A6%E4%B9%A0/%E6%85%A7%E6%B5%8B/qq%E7%BE%A4/demo.html";
	public static String url3 = "http://www.huicewang.com/ecshop/user.php";
	public static String url4 = "http://www.huicewang.com/ecshop/";
	private static WebDriver driver  = DriverFactory.getDriver("chrome");
	String name, pwd ;
	public API(WebDriver driver){
		this.driver = driver ;
	}
	
	/**
	 * 
	 * @param time 休眠时间
	 */
	public void sleep(int time){
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Map<String, String> searchOrderInfo(String orderID){
		driver.get("http://www.huicewang.com/ecshop/user.php?act=order_list");
		this.sleep(2);
		List<WebElement> orders = driver.findElements(By.xpath("//div[@class='userCenterBox boxCenterList clearfix']/table//tr"));
		Map<String, String> results = new LinkedHashMap<String, String>();
		
		if(orders.size()>0){
			for(int i = 2 ;i<orders.size() ;i++){
				List<WebElement> orderInfos = driver.findElements(By.xpath("//div[@class='userCenterBox boxCenterList clearfix']/table//tr["+i+"]//td"));
				if(orderInfos.get(0).getText().equals(orderID)){
					results.put("订单号", orderInfos.get(0).getText());
					results.put("下单时间", orderInfos.get(1).getText());
					results.put("订单总金额", orderInfos.get(2).getText());
					results.put("订单状态", orderInfos.get(3).getText());
					results.put("操作", orderInfos.get(4).getText());
					return results;
				}
			}
		}else{
			System.out.println("无订单信息");
			return null ;
		}
		System.out.println("无订单信息" + orderID);
		return null ;
	}
	
	public void addToCookies(){
		Cookie cookies_username = new Cookie.Builder("ECS[username]", "debby").domain("www.huicewang.com").build();
		Cookie cookies_password = new Cookie.Builder("ECS[password]", "14d2dd7ef4176e2c739bd47c783b8276").domain("www.huicewang.com").build();
		Cookie cookies_id = new Cookie.Builder("ECS[user_id]", "10").domain("www.huicewang.com").build();
		driver.manage().addCookie(cookies_password);
		driver.manage().addCookie(cookies_username);
		driver.manage().addCookie(cookies_id);
		driver.get("http://www.huicewang.com/ecshop/");
		
	}
	
		public static void randomClick(String linktext){
			List<WebElement> list = driver.findElements(By.xpath("//div[@id='mainNav']/a"));
			for (WebElement link : list) {
				if(link.getText().equals(linktext)){
					link.click();
					break;
				}
			}
			//System.out.println(list);
		}
	
		public static void doRandomSelect(WebElement element){
			Select select = new Select(element);
			List<WebElement> list = select.getAllSelectedOptions();
			Random random = new Random();
			int index = random.nextInt(list.size());
			select.selectByIndex(index);
			System.out.println(index);
			System.out.println(list.size());
		//	list.get(index).click();
			
		}
	
	/**
	 *
	 * @param title
	 * @return
	 */
	public static boolean switchWindowByTitle(String title){
//		driver.get(url2);
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			driver.switchTo().window(handle);
//			System.out.println(handle);
			if(driver.getTitle().contains(title)){
				
				driver.switchTo().window(handle);
				driver.findElement(By.id("kw")).sendKeys("测试");;
//				System.out.println("pass");
				return true;
			}
			
//			System.out.println(handles.size());
		}
		System.out.println("没有匹配");
		return false ;
	}
	
	public static void scrollToBottom(String jsscript){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript(jsscript);
	}
	public void scrollToMiddle(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("scrollTo(0,400)");
	}
	
	public static void login(String name , String pwd){
		WebDriver driver = DriverFactory.getDriver("chrome");
		driver.findElement(By.name("username")).sendKeys(name);
		driver.findElement(By.name("password")).sendKeys(pwd);
		driver.findElement(By.name("submit")).click();
	}
		
	public static void getDate(){
		SimpleDateFormat simpledate  = new SimpleDateFormat("YYYYMMddhhmmssSSS");  //如果不设置格式，默认打印16-7-17 上午10:14
		System.out.println(simpledate.format(new Date()));
	}
	
	public void selectByValue(By by , String value){
		Select select = new Select(driver.findElement(by));
		select.selectByValue(value);
	}
			
}