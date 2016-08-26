package com.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Wait {
	public static  WebElement waitForElement(WebDriver driver , final By by , int time){
		WebElement element = null ;
		try{
			element =  new WebDriverWait(driver, time).until(new ExpectedCondition<WebElement>() {

				@Override
				public WebElement apply(WebDriver driver) {
					
					return driver.findElement(by);
				}
				
			} );
		}catch(Exception e){
			e.printStackTrace();
			System.out.println(by + "等待" + time/1000 + "秒后未出现！");
		}
		return element;
	}
}
