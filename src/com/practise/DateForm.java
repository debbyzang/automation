package com.practise;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateForm {

	public static void main(String[] args) {
		getDate();
		System.out.println(new Date().getTime());
		
	}
	public static void getDate(){
		SimpleDateFormat simpledate  = new SimpleDateFormat("YYYYMMddhhmmssSSS");  //��������ø�ʽ��Ĭ�ϴ�ӡ16-7-17 ����10:14
		System.out.println(simpledate.format(new Date()));
	}
}
