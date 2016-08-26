package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.API;
import base.DriverFactory;

public class LoginPage extends Page{
	
	public static By userName = By.name("username");
	public static By password = By.name("password");
	public static By submit = By.name("submit");
	
	public LoginPage() {
		super(url);
	}

	public void loginAction(String name , String pwd){
		getElement(userName).sendKeys(name);
		getElement(password).sendKeys(pwd);
		getElement(submit).click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
