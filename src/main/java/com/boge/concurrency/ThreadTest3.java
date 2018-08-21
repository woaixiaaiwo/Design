package com.boge.concurrency;


public class ThreadTest3 {

	private volatile static C[] arr = {new C()};
	
	public static void main(String[] args) throws Exception {
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					C c = arr[0];
					if(c.x == 'A') {
						System.out.print('A');
						c.x = 'B';
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
					C c = arr[0];
					if(c.x == 'B') {
						System.out.print('B');
						c.x = 'C';
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
					C c = arr[0];
					if(c.x == 'C') {
						System.out.println('C');
						c.x = 'A';
					}else {
						//System.out.println("ccccccc===="+c.x);
					}
				}
			}
		}).start(); 
		
	}
	
	
}


