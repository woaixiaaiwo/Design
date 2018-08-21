package com.boge.utils.programas;

import java.util.concurrent.Semaphore;

public class SamphoneTest {
	
	private static Semaphore semaphore = new Semaphore(1);
	
	private static class FileThread implements Runnable{

		@Override
		public void run() {
			try {
				semaphore.acquire();
				int i=0;
				while (true) {
					System.out.println("线程:"+Thread.currentThread().getName());
					if(i++ > 5){
						break;
					}
					Thread.sleep(1000);
				}
				semaphore.release();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		FileThread fileThread = new FileThread();
		Thread[] threads = new Thread[5];
		for(int i=0;i<threads.length;i++){
			threads[i] = new Thread(fileThread);
			threads[i].start();
		}
		
	}
}
