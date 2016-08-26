package com.practise;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import base.DriverFactory;

public class RandomSelect {
	
	public static void doRandomSelect(WebElement element){
		Select select = new Select(element);
		List<WebElement> list = select.getOptions();
		Random random = new Random();
		int index = random.nextInt(list.size());
		select.selectByIndex(index);
		System.out.println(index);
		System.out.println(list.size());
	//	list.get(index).click();
		
	}
	public static void main(String[] args) {
		WebDriver driver = DriverFactory.getDriver("firefox");
		driver.get("http://www.huicewang.com/ecshop/index.php");
		RandomSelect.doRandomSelect(driver.findElement(By.id("category")));
		
		
	}

}
