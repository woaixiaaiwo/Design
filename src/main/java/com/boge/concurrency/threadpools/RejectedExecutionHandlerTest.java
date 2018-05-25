package com.boge.concurrency.threadpools;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *  饱和策略测试
 *	如果线程池中所有线程都处于忙碌状态，且线程池待工作的
 *	队列已满，此时就要使用饱和策略
 *
 *	默认饱和策略是AbortPolicy,该策略会抛出rejectedExecution,调用者可以捕获该异常，编写处理代码
 *	
 *	DiscardPolicy:抛弃策略，会悄悄的抛弃没法加入到队列的任务
 *	DiscardOldestPolicy:抛弃最旧策略，会悄悄的抛弃下一个要执行的任务。如果使用的是优先队列，会抛弃优先级最高
 *  的任务。最好不要使用
 *  CallerRunsPolicy:调用者运行策略，即不会抛出异常，也不会放弃执行。任务会被调用了execute方法的线程调用。
 *  在本例中，如果使用该策略，那么该任务会被主线程调用
 */
public class RejectedExecutionHandlerTest{
	
	public static void main(String[] args) {
		//最大线程数设置较小，且
		ThreadPoolExecutor testThreadPoolExecutor = new ThreadPoolExecutor(0,10, 0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
		testThreadPoolExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		RejectedExecutionHandlerTestThread threadPoolTest[] = new RejectedExecutionHandlerTestThread[15];
		for(int i=0;i<15;i++){
			threadPoolTest[i] = new RejectedExecutionHandlerTestThread();
			testThreadPoolExecutor.execute(threadPoolTest[i]);
		}
	}

}

class RejectedExecutionHandlerTestThread implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			//Thread.sleep(5000);
			System.out.println("调用:"+Thread.currentThread());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
