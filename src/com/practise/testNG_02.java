package com.practise;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class testNG_02 extends testNG_annotations{

//	@BeforeClass
//	public void setup(){
//		System.out.println(this.getClass().getSimpleName() + "-" +"setup_beforeClass");
//	}
//	
//	@BeforeTest
//	public void setup1(){
//		System.out.println(this.getClass().getSimpleName() + "-" +"setup_beforeTest");
//	}
//	
	@Test
	public void test01(){
		System.out.println("case: test02-1 run");
	}
	
	@Test
	public void test02(){
		System.out.println("case: test02-2 run");
	}
	
//	@BeforeMethod
//	public void setup2(){
//		System.out.println(this.getClass().getSimpleName() + "-" +"setup_beforeMethod");
//	}
//	
//	@AfterMethod
//	public void teardown2(){
//		System.out.println(this.getClass().getSimpleName() + "-" +"teardown_afterMethod");
//	}
//	
//	@AfterTest
//	public void teardown1(){
//		System.out.println(this.getClass().getSimpleName() + "-" +"teardown_afterTest");
//	}
	
	@AfterClass
	public void teardown(){
		System.out.println(this.getClass().getSimpleName() + "-" +"teardown_afterClass");
	}
}
