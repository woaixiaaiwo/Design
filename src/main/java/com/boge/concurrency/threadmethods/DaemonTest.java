package com.boge.concurrency.threadmethods;


/**
 * 守护线程测试
 * 后台线程的特点：用法与前台线程无异，只是当一个进程中所有前台线程都结束后，无论后台线程是否还处于运行中都将被强制结束，从而使得进程结束程序退出。
 * 后台线程也称为：守护线程。精灵线程。
 * 在运行程序时，操作系统会启动一个进程来运行jvm,jvm运行后会创建第一个前台线程来运行我们程序的main方法。同时也会创建一个后台线程运行GC。
 */
public class DaemonTest {

	public static void main(String[] args) {
		
		//创建一个前台线程(用户线程)
		final Thread userThread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("线程"+Thread.currentThread().getName()+":我是前台线程，我运行五秒后就退出啦!");
				for(int i=5;i>=1;i--){
					 try {
						System.out.println("线程"+Thread.currentThread().getName()+":"+i+"...");
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
				System.out.println("线程"+Thread.currentThread().getName()+":Bye!");
				return;
			}
		});
		userThread.start();
		
		//创建一个后台线程(守护线程)
		Thread daemonthread = new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("线程"+Thread.currentThread().getName()+":我是守护线程，只要没有用户线程执行，我就退出!");
				int i=1;
				while(true){
					 try {
						Thread.sleep(1000);
						System.out.println("线程"+Thread.currentThread().getName()+":我已经存活了"+(i++)+"秒");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 }
			}
		});
		//设置线程为守护线程
		daemonthread.setDaemon(true);
		daemonthread.start();
	}
	
}
