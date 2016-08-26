package com.practise;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateForm {

	public static void main(String[] args) {
		getDate();
		System.out.println(new Date().getTime());
		
	}
	public static void getDate(){
		SimpleDateFormat simpledate  = new SimpleDateFormat("YYYYMMddhhmmssSSS");  //如果不设置格式，默认打印16-7-17 上午10:14
		System.out.println(simpledate.format(new Date()));
	}
}
