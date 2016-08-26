package com.homework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.DriverFactory;

public class RandomClick {
	public static String url1 = "http://www.huicewang.com/ecshop/";
	public static void clickToText(String linktext){
//		String linktext
		WebDriver driver = DriverFactory.getDriver("firefox");
		driver.get(url1);
		List<WebElement> list = driver.findElements(By.xpath("//div[@id='mainNav']/a"));
		for (WebElement link : list) {
			if(link.getText().equals(linktext)){
				link.click();
				break;
			}
		}
		//System.out.println(list);
	}
}
