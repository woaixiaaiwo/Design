package com.boge.concurrency;


public class TwoThread {

	private volatile static int i=0;
	
	public static void main(String[] args) {
		
		Thread jishu = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (i<=100) {
					if(i%2 == 1){
						System.out.println(Thread.currentThread().getName()+":"+i);
						i = i+1;
					}
					
				}
			}
		};
		
		Thread oushu = new Thread() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (i<=100) {
					if(i%2 == 0){
						System.out.println(Thread.currentThread().getName()+":"+i);
						i = i+1;
					}
					
				}
			}
		};
		jishu.start();
		oushu.start();
		
	}
	
}