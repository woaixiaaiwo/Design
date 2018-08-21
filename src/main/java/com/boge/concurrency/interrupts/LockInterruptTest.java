package com.boge.concurrency.interrupts;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock加锁，可以响应中断 
 */
public class LockInterruptTest {

	public static void main(String[] args) throws InterruptedException {
		LockInterrupt lockInterrupt = new LockInterrupt();
		Thread thread1 = new Thread(lockInterrupt);
		thread1.start();
		thread1.interrupt();
		Thread.sleep(5000L);
	}
	
}
class LockInterrupt implements Runnable{

	private Lock Lock = new ReentrantLock();
	
	@Override
	public void run() {
		try {
			//lockInterruptibly和带超时时间的tryLock都能响应中断，立即抛出
			//InterruptedException异常，并释放锁
			Lock.lockInterruptibly();
			Lock.tryLock(1000,TimeUnit.SECONDS);
			while(true) {
				System.out.println("线程正在运行...");
				Thread.sleep(1000L);
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
	}
}
