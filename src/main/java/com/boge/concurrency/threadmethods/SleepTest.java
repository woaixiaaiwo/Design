package com.boge.concurrency.threadmethods;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Thread.sleep(times)使当前线程从Running状态放弃处理器进入Block状态,休眠times毫秒，再返回Runnable状态 
 */
public class SleepTest {

	public static void main(String[] args) {
		
		//线程睡眠测试
		//new Thread(new TestSleepThread()).start();
	
		//在内部类中使用变量，要将变量置位final
		final Thread sleepThread = new Thread(new InterruptedThread());
		
		sleepThread.start();
		//睡眠线程中断测试
		new Thread(new Runnable() {
			@Override
			public void run() {
				 System.out.println("线程"+Thread.currentThread().getName()+":有个线程在睡觉，让我来中断它!");
				 for(int i=5;i>=1;i--){
					 try {
						System.out.println("线程"+Thread.currentThread().getName()+":"+i+"...");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				 System.out.println("线程"+Thread.currentThread().getName()+":中断这个懒惰的家伙!");
				 sleepThread.interrupt();
		    }
		}).start();
	
	}
	
}

class TestSleepThread implements Runnable{

	@Override
	public void run() {
		SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss");
        //输出系统时间的时分秒。每隔一秒显示一次。可能会出现跳秒的情况，因为阻塞1秒过后进入runnable状态，等待分配时间片进入running状态后还需要一点时间
        while(true){
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                 e.printStackTrace();

            }
             System.out.println(format.format(new Date()));
          }
    }
}


class InterruptedThread implements Runnable{

	@Override
	public void run() {
		 System.out.println("线程"+Thread.currentThread().getName()+":有点困了，先睡一会。。。!");
         try{
             // 当一个线程处于睡眠阻塞时，若被其他线程调用interrupt()方法中断，则sleep()方法会抛出 InterruptedException异常
             Thread.sleep(100000000);
         }
         catch(InterruptedException e){
        	 //interrupt（）是用来设置中断状态的。返回true说明中断状态被设置了而不是被清除了。
        	 //我们调用sleep、wait,join等此类可中断（throw InterruptedException）方法时，一旦方法
        	 //抛出InterruptedException，当前调用该方法的线程的中断状态就会被jvm自动清除了，就是说我们调
        	 //用该线程的isInterrupted 方法时是返回false。如果你想保持中断状态，可以再次调用interrupt方法设置中断状态。
             System.out.println("线程"+Thread.currentThread().getName()+":啊，我被干掉了!");
             System.out.println("当前中断状态为:"+Thread.currentThread().isInterrupted());
         }
    }
}
