package com.practise;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.CheckPoint;


public class Case002 {
	
	private CheckPoint check = null;
	@Test(priority = 1)
	public static void case002A(){
		System.out.println("case002A_PASS");
	}
	@Test(priority = 2)
	@Parameters("desc")
	public void case002B(String desc){
		check.equals(2, 2);
		System.out.println(desc + "case003");
		check.results("case02 ³É¹¦");
	}
}
