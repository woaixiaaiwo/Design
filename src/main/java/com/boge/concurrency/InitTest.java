package com.boge.concurrency;

import java.util.Arrays;

/**
 * 多线程下，可能会导致初始化不完全
 * 
 * 线程1和线程2之间没有happens-before关系，所以线程1的初始化是无序的
 * 给init加上volatile关键字，可以禁止init上操作的重排序
 */
public class InitTest {

	private volatile static Init init;
	
	public volatile static boolean stop = true;
	
	public static void main(String[] args) {
		Runnable creater = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(stop){
					init = new Init("aa","b","c",new Byte[4096]);
				}
			}
		};
		
		Runnable geter = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
					if(init != null){
						if(init.isSuccess()){
							System.out.println("初始化成功！");
						}else{
							System.out.println("初始化失败！");
							System.out.println(init);
							break;
						}
					}
				}
				
			}
		};
		
		Thread thread1 = new Thread(creater);
		//Thread thread2 = new Thread(creater);
		//Thread thread3 = new Thread(creater);
		//Thread thread4 = new Thread(creater);
		Thread thread5 = new Thread(geter);
		thread1.start();
		//thread2.start();
		//thread3.start();
		//thread4.start();
		thread5.start();
		
		/*Byte[] arr = new Byte[1024];
		System.out.println(arr == null);
		System.out.println(Arrays.toString(arr));*/
	}
	
}

class Init{
	
	private String a;
	
	private String b;
	
	private String c;
	
	private Byte[] arr;

	public Init(String a, String b, String c, Byte[] arr) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.arr = arr;
	}
	
	public boolean isSuccess(){
		if(a == null){
			System.out.println(a);
			InitTest.stop = false;
			return false;
		}
		if(b == null){
			System.out.println(b);
			InitTest.stop = false;
			return false;
		}
		if(c == null){
			System.out.println(c);
			InitTest.stop = false;
			return false;
		}
		if(arr == null){
			System.out.println(arr);
			InitTest.stop = false;
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Init [a=" + a + ", b=" + b + ", c=" + c + ", arr="
				+ Arrays.toString(arr) + "]";
	}
	
	
}