package com.practise;

public class Singleton {
	//方式一
//	private static Singleton instance ;
//	private  Singleton(){}
//	public static Singleton getInstance(){
//		if(instance ==null){
//			instance = new Singleton();
//		}
//		return instance;
//	}
	
	//方式二
	private static Singleton instance = new Singleton();
	private Singleton(){}
	public static Singleton getInstance(){
		return instance;
	}
}
