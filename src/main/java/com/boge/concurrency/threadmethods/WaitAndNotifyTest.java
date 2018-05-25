package com.boge.concurrency.threadmethods;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用wait()与notify()方法完成线程协同工作
 * 
 * wait,notify和notifyAll方法都必须在同步块中调用
 *  void notify() 
 *  唤醒在此对象监视器上等待的单个线程。 
 *  void notifyAll() 
 *  唤醒在此对象监视器上等待的所有线程。 
 *  void wait() 
 *  导致当前的线程等待，直到其他线程调用此对象的 notify() 方法或 notifyAll() 方法。
 *  
 *  1.wait( )，notify( )，notifyAll( )都不属于Thread类，而是属于Object基础类，也就是每个对象都有wait( )，notify( )，notifyAll( ) 
 *  的功能，因为每个对象都有锁，锁是每个对象的基础，当然操作锁的方法也是最基础了。
 *  2.当需要调用以上的方法的时候，一定要对竞争资源进行加锁，如果不加锁的话，则会报 IllegalMonitorStateException 异常
 *  3.当想要调用wait( )进行线程等待时，必须要取得这个锁对象的控制权（对象监视器），一般是放到synchronized(obj)代码中。
 *  4.在while循环里而不是if语句下使用wait，这样，会在线程暂停恢复后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知
 *  5.调用obj.wait()会释放obj的锁，否则其他线程也无法获得obj的锁，也就无法在synchronized(obj){ obj.notify() } 代码段内唤醒A。
 *  6.notify( )方法只会通知等待队列中的第一个相关线程（不会通知优先级比较高的线程）
 *  7.notifyAll( )通知所有等待该竞争资源的线程（也不会按照线程的优先级来执行）
 *  8.假设有三个线程执行了obj.wait( )，那么obj.notifyAll( )则能全部唤醒tread1，thread2，thread3，但是要继续执行obj.wait（）的
 *  下一条语句，必须获得obj锁，因此，tread1，thread2，thread3只有一个有机会获得锁继续执行，例如tread1，其余的需要等待thread1释放obj锁
 *  之后才能继续执行。
 *  9.当调用obj.notify/notifyAll后，调用线程依旧持有obj锁，因此，thread1，thread2，thread3虽被唤醒，但是仍无法获得obj锁。直到调用线程
 *  退出synchronized块，释放obj锁后，thread1，thread2，thread3中的一个才有机会获得锁继续执行。
 */
public class WaitAndNotifyTest {
	
	// 判断照片是否下载完成
    public static boolean isFinish = false;
    public static Object object = new Object();

	public static void main(String[] args) {
		waitAndNotifyTest();
	}
	
	
	
	//使用wait和notify实现简单的生产者和消费者
    private static void waitAndNotifyTest(){
    	
    	final List<Integer> list = new ArrayList<Integer>();
    	
    	//生产者的线程
        final Thread Producer = new Thread() {
        	public void run(){
        		System.out.println("开始生产数据...");
        		for(int i=0;i<20;i++){
        			list.add(i);
        			System.out.println("download:已完成" + i + "%");
                    try{
                        Thread.sleep(50);
                    }
                    catch(InterruptedException e){
                    }
        		}
        		System.out.println("生产数据完成！唤醒消费者。。。");
        		synchronized (object) {
        			object.notifyAll();
				}
        	}
        };
        
       //消费者的线程
       Thread threads[] = new Thread[2];
       Runnable runnable = new Runnable() {
        	public void run(){
        		System.out.println("线程："+Thread.currentThread().getName()+"准备消费数据...");
        		while(true){
        			synchronized (object) {
            			try {
            				//由于生产者创建数据时没有加锁，所以这一步判断可能不为空，造成线程不阻塞
            				if(list.size() == 0){
            	        		System.out.println("线程："+Thread.currentThread().getName()+"还没有数据，等待数据生产...");
            					object.wait();
            				}
    						System.out.println("线程："+Thread.currentThread().getName()+"获取锁，开始消费数据...");
    						if(list.size()>0){
    							list.remove(0);
    						}
    						Thread.sleep(500);
    					} catch (InterruptedException e) {
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    				}
        		}
        		
        	}
        };
        for(int i=0;i<2;i++){
        	threads[i] = new Thread(runnable);
        	threads[i].start();
        }
        Producer.start();
    	
    }
	
	
	//使用wait和notify完成图片下载和展示
    private static void picDownloadAndShow(){
    	// 下载图片的线程
        final Thread download = new Thread() {
            public void run() {
                System.out.println("download:开始下载图片");
                for(int i = 0; i <= 100; i++){
                    System.out.println("download:已完成" + i + "%");
                    try{
                        Thread.sleep(50);
                    }
                    catch(InterruptedException e){
                    }
                }
                System.out.println("download:图片下载完毕");
                isFinish = true;// 表示图片下载完毕了

                // 当图片下载完毕后，就可以通知showImg开始显示图片了
                synchronized(object){
                    // 通知在object身上等待的线程解除等待阻塞，这一步notify还没有释放object的锁，所以其他线程还无法获取锁
                    object.notify();
                }
                //退出同步块后，释放obj的锁

                System.out.println("download:开始下载附件");
                for(int i = 0; i <= 100; i++){
                    System.out.println("download:已完成" + i + "%");
                    try{
                        Thread.sleep(50);
                    }
                    catch(InterruptedException e){
                    }
                }
                System.out.println("download:附件下载完毕");
            }

        };

        // 用于显示图片的线程
        Thread showImg = new Thread() {
            public void run() {
                System.out.println("show:准备显示图片");
                // 等待下载线程将图片下载结束后，再执行下面的代码
                try{
                    // wait()阻塞会在以下两种情况被解除,1:当download线程结束. 2:当调用了download的notify()
                    synchronized(object){
                        object.wait();
                    }
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(!isFinish){
                    throw new RuntimeException("图片没有下载完毕");
                }
                System.out.println("show:图片已经显示了!");
            }
        };

        download.start();
        showImg.start();
    }
	
}
