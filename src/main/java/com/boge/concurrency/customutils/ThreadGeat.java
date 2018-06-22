package com.boge.concurrency.customutils;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait和notify实现可重新关闭的阀门类(闭锁开启后无法关闭) 
 */
public class ThreadGeat {

	private boolean isOpen;
	
	//这个作用是，如果10个线程到达阀门，此时都满足startGeneration == generation
	//那么10个线程都会等待。
	//如果这时候开启阀门，generation加一，此时假如有8个线程都被唤醒，另外两个线程还
	//没来得及唤醒，阀门就被关闭的话，另外两个线程会接着进行29行的判断，虽然此时isOpen
	//为false，但是startGeneration为0，而generation
	private int generation;
	
	public synchronized void close(){
		isOpen = false;
	}
	
	public synchronized void open(){
		++generation;
		isOpen = true;
		notifyAll();
	}
	
	public synchronized void await() throws InterruptedException{
		int startGeneration = generation;
		while(!isOpen && startGeneration == generation){
			wait();
		}
	}
	
	
	public static void main(String[] args) {
		
		final ThreadGeat geat = new ThreadGeat();
		
		final List<String> list = new ArrayList<String>();
		geat.close();
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName()+"启动了!");
				while(true){
					list.add("123");
					try {
						System.out.println(Thread.currentThread().getName()+"阻塞了!");
						geat.await();
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName()+"重新启动了!");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		Thread[] threads = new Thread[10];
		for(int i=0;i<threads.length;i++){
			threads[i] = new Thread(runnable);
			threads[i].start();
		}
		
		while(true){
			if(list.size() >= 10){
				geat.open();
				break;
			}
		}
	}
	
	
}
