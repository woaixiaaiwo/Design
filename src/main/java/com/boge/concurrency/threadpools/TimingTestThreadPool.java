package com.boge.concurrency.threadpools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 *  线程池的beforeExecute，afterExecute，和terminated方法测试
 *	beforeExecute：线程执行之前运行,每个线程启动之前都会运行一次
 *  afterExecute：线程执行完毕后运行,每个线程执行完毕后都会运行一次
 *  terminated：线程池退出后执行，只执行一次
 */
public class TimingTestThreadPool extends ThreadPoolExecutor{
	
	private final ThreadLocal<Long> startTime = new ThreadLocal<Long>();
	private final AtomicLong numTasks = new AtomicLong();
	private final AtomicLong totalTime = new AtomicLong();

	public TimingTestThreadPool(int corePoolSize, int maximumPoolSize,
			long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void beforeExecute(Thread t, Runnable r) {
		super.beforeExecute(t, r);
		System.out.println(String.format("Thread %s:start %s",t,r));
		startTime.set(System.currentTimeMillis());
	}
	
	@Override
	public void afterExecute(Runnable r,Throwable t) {
		try{
			Long taskTime = System.currentTimeMillis() - startTime.get();
			numTasks.incrementAndGet();
			totalTime.addAndGet(taskTime);
			System.out.println(String.format("Thread %s:end %s,time = %dms",Thread.currentThread(),r,taskTime));
		}
		finally{
			super.afterExecute(r, t);
		}
	}
	
	@Override
	public void terminated() {
		try{
			System.out.println(String.format("Terminated :avgtime = %dms",totalTime.get()/numTasks.get()));
		}finally{
			super.terminated();
		}
	}
	
	
	public static void main(String[] args) {
		TimingTestThreadPool timingTestThreadPool = new TimingTestThreadPool(0, Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
		ThreadPoolTest threadPoolTest[] = new ThreadPoolTest[10];
		for(int i=0;i<10;i++){
			threadPoolTest[i] = new ThreadPoolTest();
			timingTestThreadPool.execute(threadPoolTest[i]);
		}
		//shutdown方法比较柔和，会等所有线程都执行完毕后再关闭线程池。且不再接受任务
		//timingTestThreadPool.shutdown();
		//shutdownNow方法比较暴力，会立即关闭线程池
		timingTestThreadPool.shutdownNow();
	}

}

class ThreadPoolTest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
	
}
