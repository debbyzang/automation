package com.practise;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testNG_01 extends testNG_annotations{

	
	
	@Test
	public void test01(){
		System.out.println("case: test01-1 run");
	}
	
	@Test
	public void test02(){
		System.out.println("case: test01-2 run");
	}
	
	
	
}
