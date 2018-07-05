package com.boge.concurrency.threadpools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 基本的线程池使用 
 * 参数说明：
 * corePoolSize：核心线程数，线程池开始执行时就会创建的线程数量
 * maximumPoolSize：最大线程数，线程池中最大的线程数量
 * keepAliveTime：线程超时时间，如果线程池的线程数量大于核心线程数，空闲的线程就会
 * 使用这个超时时间，超过该时间，线程被终止
 * unit：超时时间的单位
 * workQueue：工作队列：见下面的注释
 * threadFactory：线程工厂，要实现ThreadFactory接口，线程池
 * 创建线程时会调用ThreadFactory的newThread方法。
 * RejectedExecutionHandler：用于设置饱和策略，也可以使用setRejectedExecutionHandler
 * 方法设置。可以参考RejectedExecutionHandlerTest类
 */
public class ExcutorServiceTest {

	public static void main(String[] args) {
		
		/**
		 * 没有设置初始线程个数的情况：
		 * 当队列是无限大时，任务会进来排队，此时线程池会从任务中一个一个拿，所以不会创建线程
		 * 当队列有一定大小时，如果此时线程池中所有线程都在工作，而且队列满了，线程池就会创建一个新
		 * 的线程，执行工作。如果此时线程池的线程数量已经到最大值，不能创建线程了，就会使用饱和策略
		 * 如果有初始线程，道理一样：
		 * 线程池中所有线程都在工作，而且队列满了，且线程池中线程数量没有到最大线程数，线程池就会创建
		 * 一个新的线程，执行工作。反之，不符合任意一点，都不会创建线程。如果此时线程池的线程数量已经
		 * 到最大值，不能创建线程了，就会使用饱和策略。
		 * SynchronousQueue是一个特殊的队列，它不保存任务信息，任务提交给它，就必须有一个
		 * 线程来执行，否则会阻塞(参考SynchronousQueueTest类)
		 * newCachedThreadPool使用的就是SynchronousQueue
		 */
		ThreadPoolExecutor executorService =  new ThreadPoolExecutor(1, Integer.MAX_VALUE,
                5L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(10));
		executorService.allowCoreThreadTimeOut(true);
		int threadNum = 13;
		
		TestThread runnable[] = new TestThread[threadNum];
		
		
		for(int i=0;i<threadNum;i++){
			runnable[i] = new TestThread();
			executorService.execute(runnable[i]);
		}
		
	}
}

class TestThread implements Runnable{

	@Override
	public void run() {
		/*try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// TODO Auto-generated method stub
		System.out.println(Thread.currentThread().getName());
	}
	
}

