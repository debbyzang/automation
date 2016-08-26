package com.practise;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Element;
//import org.testng.annotations.Test;






import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import base.DriverFactory;
import base.TestBase;
import util.XMLParser;



public class Test01{
	
	public static WebDriver driver;
	public static void main(String[] args){
		driver = DriverFactory.getDriver("Firefox");
		driver.get("https://www.baidu.com/");
	}
}