package com.boge.concurrency.threadmethods;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		
		Test test = new Test();
		System.out.println(Thread.currentThread().getName());
		test.join();
		System.out.println("fdsf");
		
	}
	
	public final synchronized void join() throws InterruptedException{
        while (Thread.currentThread().isAlive()) {
    		System.out.println(Thread.currentThread().getName());
        	System.out.println(this);
            wait(0);
        }
   }
	
}
