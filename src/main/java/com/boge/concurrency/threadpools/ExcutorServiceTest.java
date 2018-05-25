package com.boge.concurrency.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基本的线程池使用 
 */
public class ExcutorServiceTest {

	public static void main(String[] args) {
		
		/**
		 * 没有设置初始线程个数的情况：
		 * 当队列是无限大时，任务会进来排队，此时线程池会从任务中一个一个拿，所以不会创建线程
		 * 当队列有一定大小时，如果此时线程池中所有线程都在工作，而且队列满了，线程池就会创建一个新
		 * 的线程，执行工作
		 * 如果有初始线程，道理一样：
		 * 线程池中所有线程都在工作，而且队列满了，线程池就会创建一个新的线程，执行工作
		 * 反之，不符合任意一点，都不会创建线程
		 * 
		 * SynchronousQueue是一个特殊的队列，它不保存任务信息，任务提交给它，就必须有一个
		 * 线程来执行，否则会阻塞(回头验证一下)
		 * newCachedThreadPool使用的就是SynchronousQueue
		 */
		ExecutorService executorService =  new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(9));
		
		TestThread runnable[] = new TestThread[10];
		
		
		for(int i=0;i<10;i++){
			runnable[i] = new TestThread();
			executorService.execute(runnable[i]);
		}
		
	}
}

class TestThread implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		//while(true){
			System.out.println(Thread.currentThread().getName());
		//}
		
	}
	
}

