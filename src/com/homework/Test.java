package com.homework;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.API;
import base.DriverFactory;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver  = DriverFactory.getDriver("chrome");
		driver.get(API.url3);
		
		Wait.waitForElement(driver, By.id("123"), 1000);
		
		//测试下单
//		API.login("debby", "123123");
//		Order.selectItem("耳机");
//		Order.goToCheckout();
		
		//找订单号
		
		
		//正则test
//		driver.get("file:///C:/Users/Administrator/Desktop/test.html");
//		String orderid = driver.findElement(By.xpath("//font")).getText();
//		
//		Pattern num = Pattern.compile("20[1-9][0-9][0-1][0-9]{8}");
//		Matcher matcher = num.matcher(orderid);          //matcher.matches(orderid);
//		if(matcher.matches()){
//			System.out.println("下单成功");
//		}else{
//		System.out.println("未找到订单");
//		}
	}

}
