package com.homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.DriverFactory;

public class Login {

	@Test
	@Parameters("type")
	public static void login(String type) {

		WebDriver driver = DriverFactory.getDriver(type);
		driver.get("http://182.48.115.36:8080/lzbank_portal_s2/index.php/welcome/login");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		driver.findElement(By.id("USER_NAME")).sendKeys("123");
		driver.findElement(By.id("USER_PWD")).sendKeys("infohold1");
		driver.findElement(By.xpath("//p[@class='login_link']/a")).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.close();
		
	}

}
