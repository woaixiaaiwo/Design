package com.boge.concurrency.interrupts;

/**
 * 使用synchronized加锁，在获取锁的过程中无法响应中断，且线程会阻塞（自旋等待）
 */
public class SyncInterruptTest2 {

	public static void main(String[] args) throws InterruptedException {
		SyncInterrupt syncInterrupt = new SyncInterrupt();
		Thread thread1 = new Thread(syncInterrupt);
		thread1.start();
		thread1.interrupt();
		Thread.sleep(5000L);
	}
	
}
class SyncInterrupt implements Runnable{
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			synchronized (SyncInterrupt.class) {
				while(true) {
					System.out.println("线程正在运行...");
					Thread.sleep(1000L);
				}
			}
			
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
