package com.boge.concurrency;


public class ThreadTest3 {

	private volatile static C[] arr = {new C()};
	
	public static void main(String[] args) throws Exception {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					if(arr[0].x == 'A') {
						System.out.println('A');
						arr[0].x = 'B';
					}else {
						//System.out.println("aaaaaa===="+c.x);
					}
				}
			}
		}).start(); 
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if(arr[0].x == 'B') {
						System.out.println('B');
						arr[0].x = 'C';
					}else {
						//System.out.println("bbbbbbbb===="+c.x);
					}
				}
			}
		}).start(); 
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true) {
					if(arr[0].x == 'C') {
						System.out.println('C');
						arr[0].x = 'A';
					}else {
						//System.out.println("ccccccc===="+c.x);
					}
				}
			}
		}).start(); 
		
	}
	
	
}


