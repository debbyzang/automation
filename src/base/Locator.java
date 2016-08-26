package base;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.xpath.XPath;

import org.dom4j.DocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.mustache.Value;

import com.practise.ExpectedConditions;

import util.XMLParser;

public class Locator {
	
	private WebDriver driver ;
	private boolean existFlag ;
	private XMLParser xp ;
	private int timeOutInSeconds;
	private String filePath ;

	public Locator(WebDriver driver , String filePath , int timeOutInSeconds) {
		this.driver = driver ;
		this.filePath = filePath ;
		this.timeOutInSeconds = timeOutInSeconds;
		xp = new XMLParser(this.filePath);
	}
	
	/**
	 * @param orderID
	 * @return
	 */
	public Map<String, String> searchOrderInfo(String orderID){
		driver.get("http://www.huicewang.com/ecshop/user.php?act=order_list");
		this.sleep(2);
		List<WebElement> orders = driver.findElements(By.xpath("//div[@class='userCenterBox boxCenterList clearfix']/table//tr"));
		Map<String, String> results = new LinkedHashMap<String, String>();
		
		if(orders.size()>0){
			for(int i = 2 ;i<orders.size() ;i++){
				List<WebElement> orderInfos = driver.findElements(By.xpath("//div[@class='userCenterBox boxCenterList clearfix']/table//tr["+i+"]//td"));
				if(orderInfos.get(0).getText().equals(orderID)){
					results.put("������", orderInfos.get(0).getText());
					results.put("�µ�ʱ��", orderInfos.get(1).getText());
					results.put("�����ܽ��", orderInfos.get(2).getText());
					results.put("����״̬", orderInfos.get(3).getText());
					results.put("����", orderInfos.get(4).getText());
					return results;
				}
			}
		}else{
			System.out.println("�޶�����Ϣ");
			return null ;
		}
		System.out.println("�޶�����Ϣ" + orderID);
		return null ;
	}
	
	public boolean linkTo(String Url) {
		try {
			driver.get(Url);
		} catch (Exception e) {
			System.out.println("ҳ�����ʧ��" + Url);
			return false ;
		}
		return true ;
	}
	
	public void addJS(String jsCode){
		JavascriptExecutor jsExcuExecutor = (JavascriptExecutor) driver;
		jsExcuExecutor.executeScript(jsCode);
	}
	
	public void scrollToMiddle(){
		addJS("scrollTo(0,400)");
	}
	
	public void scrollToButtom(){
		addJS("scrollTo(0,1200)");
	}
	
	public void sleep(int time){
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public boolean waitElementToBeDisplayed(final WebElement element, String pageKey , String objKey){
		boolean wait = false ;
		if(element == null)
			return wait ;
		try {
			wait = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<Boolean>() {
				public Boolean apply(WebDriver d){
					return element.isDisplayed();
				}
			});
		} catch (Exception e) {
			System.out.println(pageKey + "-" + objKey + "δ��ҳ����ʾ");
		}
		return wait ;
	}
	
	public WebElement element(String page ,String object){
		return getElement(page, object, true) ;
	}
	
	public List<WebElement> elements(String page ,String object){
		return getElements(page, object, true) ;
	}
	
	public List<String> elementsAttribute(String page ,String object , String attribute){
		List<WebElement> elements = elements(page, object);
		List<String> list = new ArrayList<String>();
		for(WebElement e : elements){
			list.add(e.getAttribute(attribute));
		}
		return list ;
	}
	
	public List<String> elementsText(String page ,String object){
		List<String> list = new ArrayList<String>();
		List<WebElement> elements = this.elements(page, object);
		for(WebElement element : elements){
			list.add(element.getText());
		}
		return list ;
	}
	
	public Select select(String pageKey ,String objKey){
		return new Select(this.getElement(pageKey, objKey, true));
	}
	
	public void selectByValue(String pageKey ,String objKey ,String value){
		Select select = select(pageKey, objKey);
		select.selectByValue(value);
	}
	
	private List<WebElement> getElements(String page ,String object , boolean wait){
		List<WebElement > elements = new ArrayList<WebElement>() ;
		
		if(xp.isExist("����/" + page + "/" + object)){
			String type = xp.getElementText("����/" + page + "/" + object + "/type");
			String value = xp.getElementText("����/" + page + "/" + object + "/value").trim();
			By by = getBy(type, value);
			elements = driver.findElements(by);
			if(elements.size()==0){
				System.out.println("δ�ҵ�ҳ��Ԫ�أ�" + page + "-" + object);
			}else{
				for(WebElement e:elements){
					if(e.equals("")||e==null){
						System.out.println("���ض���������д��ڿ�ֵ��" + page + "-" + object);
					}
				}
			}
		}else{
			System.out.println(page + "-" + object + "δ�ڶ�����ļ��д���");
		}
		return elements ;
	}
	
	private WebElement getElement(String page ,String object , boolean wait){
		WebElement element = null ;
		existFlag = false ;
		
		if(xp.isExist("����/" + page + "/" + object)){
			String type = xp.getElementText("����/" + page + "/" + object + "/type");
			String value = xp.getElementText("����/" + page + "/" + object + "/value").trim();
			final By by = getBy(type, value);
			if(wait){
				try {
					element = new WebDriverWait(driver, timeOutInSeconds).until(new ExpectedCondition<WebElement>() {
						@Override
						public WebElement apply(WebDriver d) {
							existFlag = true ;
							return driver.findElement(by);
						}
					});
					if(! waitElementToBeDisplayed(element, page, object)){
						System.out.println(page + "-" + object + "δ��ҳ����ʾ��");
					}
				} catch (Exception e) {
					System.out.println("δ���ҵ�ҳ��Ԫ��" + page + "-" +object);
				}
			}else{
				return driver.findElement(by);
			}
		}else{
			System.out.println(page + "-" + object + "δ�ڶ�����ļ��д���");
		}
		return element ;
	}
	
	private By getBy(String type,String value){
		By by = null ;
		switch (type.trim()) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "className":
			by = By.className(value);
			break;
		case "tagName":
			by = By.tagName(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "cssSelector":
			by = By.cssSelector(value);
			break;
		default:
			System.out.println("Ԫ�ض�λ���� By" + type + "������");
			break;
		}
		return by;
	}
	
}
