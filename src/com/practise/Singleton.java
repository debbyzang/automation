package com.practise;

public class Singleton {
	//��ʽһ
//	private static Singleton instance ;
//	private  Singleton(){}
//	public static Singleton getInstance(){
//		if(instance ==null){
//			instance = new Singleton();
//		}
//		return instance;
//	}
	
	//��ʽ��
	private static Singleton instance = new Singleton();
	private Singleton(){}
	public static Singleton getInstance(){
		return instance;
	}
}
