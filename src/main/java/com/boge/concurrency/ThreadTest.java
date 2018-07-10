package com.boge.concurrency;

public class ThreadTest {

	private static volatile C c = new C();
	
	public static void main(String[] args) {
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					if(c.x == 'A') {
						System.out.println('A');
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
					if(c.x == 'B') {
						System.out.println('B');
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


class C{
	public char x = 'A';

	@Override
	public String toString() {
		return "C [x=" + x + "]";
	}
	
	
}

