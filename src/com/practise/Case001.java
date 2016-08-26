package com.practise;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.CheckPoint;


public class Case001 {
	@Test(priority = 1)
	public void case001A() {
		String str1 = "case1_001";
		String str2 = "case1_002";
//		System.out.println("run case001A");
		CheckPoint check = null;
		check.equals(str1, str2);
		check.results("成功");
	}
	@Test(priority = 2)
	@DataProvider(name="plus")
	public static Object[][] getData(){
		return new Object[][]{
				new Object[]{1,2},
				new Object[]{2,4},
		};
	}
	@Test(dataProvider="plus")
	public void test001(int a ,int b){
		System.out.println(a+b);
	}
	public void case001B(){
		int i = 1;
		int j = 2;
//		System.out.println("run case001B");
		CheckPoint check = null;
		check.equals(i, 1);
//		check.results("成功");
	}
}
