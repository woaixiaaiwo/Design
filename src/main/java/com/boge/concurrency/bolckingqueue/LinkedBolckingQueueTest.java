package com.boge.concurrency.bolckingqueue;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * LinkedBlockingQueue使用的是经典的生产者-消费者模式
 * 
 * put方法：如果队列满了，会阻塞队列
 * offer() 往queue里放一个element后立即返回，如果队列没满，offer方法返回true，认为offer成功；否则返回false。 
 * offer(2000, TimeUnit.SECONDS) 往queue里放一个element但是等待指定的时间后才返回，返回的逻辑和offer()方法一样。 
 * 
 * take() 取出并且remove掉queue里的element（认为是在queue里的。。。），取不到东西他会一直等。 
 * poll() 取出并且remove掉queue里的element（认为是在queue里的。。。），否则立即返回null。 
 * poll(2000, TimeUnit.SECONDS) 等待指定的时间然后取出并且remove掉queue里的element,其实就是再等其他的thread来往里塞。 
 */
public class LinkedBolckingQueueTest {

	public static void main(String[] args) {
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(1);
		
		LinkedCreater creater = new LinkedCreater(queue);
		LinkedGeter geter = new LinkedGeter(queue);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		executorService.execute(creater);
		executorService.execute(geter);
	}
	
}

class LinkedCreater implements Runnable{

	private BlockingQueue<String> queue;
	
	LinkedCreater(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
        System.out.println("put thread start");
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(2000);
		        System.out.println("put a value to queue");
				Random random = new Random();
				System.out.println(queue.offer(String.valueOf(random.nextInt(100)),5000,TimeUnit.MICROSECONDS));
				//System.out.println(queue.poll());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class LinkedGeter implements Runnable{

	private BlockingQueue<String> queue;
	
	LinkedGeter(BlockingQueue<String> queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
        System.out.println("take thread start");
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(1000);
		        System.out.println("wait value...");
				System.out.println("get value from queue:"+queue.poll());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}
