package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import base.WebSuite;

public class Page extends WebSuite {

	protected static String url ;
	
	public Page(String url){
		this.url = url ;
		driver.get(url);
	}
	
	public WebElement getElement(By by){
		return driver.findElement(by);
	}
	
}
