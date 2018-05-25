package com.boge.concurrency.threadmethods;


/**
 * 线程中断测试
 * 
 * 每一个线程都有一个boolean类型标志，用来表明当前线程是否请求中断，当一个线程调用interrupt() 方法时，线程的中断标志将被设置为true。
 * 我们可以通过调用Thread.currentThread().isInterrupted()或者Thread.interrupted()来检测线程的中断标志是否被置位。这两个方法的区别是
 * Thread.currentThread().isInterrupted()是线程对象的方法，调用它后不清除线程中断标志位；而Thread.interrupted()是一个静态方法，调用它
 * 会清除线程中断标志位。
 * Thread.currentThread().isInterrupted()：        对象方法        不清除中断标志位
 * Thread.interrupted()：                                        静态方法         清除中断标志位(设置为false)
 * 调用线程的interrupt() 方法不会中断一个正在运行的线程，只是设置了一个线程中断标志位，如果在程序中不检测线程中断标志位，那么即使
 * 设置了中断标志位为true，线程也一样照常运行。
 * 
 * 一般来说中断线程分为三种情况：
 * 
 * (一) ：中断非阻塞线程
 * 
 * (二)：中断阻塞线程
 * 
 * (三)：不可中断线程
 */
public class InterruptTest {

	public static void main(String[] args) {
		InterruptThreadTest4.test();
		/*System.out.println(Thread.interrupted());
		Thread.currentThread().interrupt();
		System.out.println(Thread.interrupted());*/
	}
	
}

/**
 * (一)：中断非阻塞线程
 * 
 */
/**
 * (1)采用线程共享变量
 * 这种方式比较简单可行，需要注意的一点是共享变量必须设置为volatile，这样才能保证修改后其他线程立即可见。
 */
class InterruptThreadTest extends Thread{
	// 设置线程共享变量  
    volatile boolean isStop = false;  
      
    public void run() {  
        while(!isStop) {  
            long beginTime = System.currentTimeMillis();  
            System.out.println(Thread.currentThread().getName() + "is running");  
            // 当前线程每隔一秒钟检测一次线程共享变量是否得到通知  
            while (System.currentTimeMillis() - beginTime < 1000) {}  
        }  
        if (isStop) {  
            System.out.println(Thread.currentThread().getName() + "is interrupted");  
        }  
    }  
      
    public static void test() {  
        InterruptThreadTest itt = new InterruptThreadTest();  
        itt.start();  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        // 线程共享变量设置为true  
        itt.isStop = true; 
    }
}

/**
 * (2)采用中断机制
 */
class InterruptThreadTest2 extends Thread{
      
    public void run() {  
        while(!Thread.currentThread().isInterrupted()) {  
            long beginTime = System.currentTimeMillis();  
            System.out.println(Thread.currentThread().getName() + "is running");  
            // 当前线程每隔一秒钟检测一次线程共享变量是否得到通知  
            while (System.currentTimeMillis() - beginTime < 1000) {}  
        }  
        if (Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + "is interrupted");  
        }  
    }  
      
    public static void test() {  
    	InterruptThreadTest2 itt = new InterruptThreadTest2();  
        itt.start();  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        //中断线程 
        itt.interrupt(); 
    }
}

/**
 * (二)：中断阻塞线程
 * 当线程调用Thread.sleep()、Thread.join()、object.wait()再或者调用阻塞的i/o操作方法时，都会使得当前线程进入阻塞状态。那么此时
 * 如果在线程处于阻塞状态是调用interrupt() 方法设置线程中断标志位时线程会抛出一个异常，并且会清除线程中断标志位(设置为false)。
 * 这样一来线程就能退出阻塞状态。当然抛出异常的方法就是造成线程处于阻塞状态的Thread.sleep()、Thread.join()、object.wait()这些方法。
 * 
 * 需要注意的地方就是 Thread.sleep()、Thread.join()、object.wait()这些方法，会检测线程中断标志位，如果发现中断标志位为true则抛出异常
 * 并且将中断标志位设置为false。所以while循环之后每次调用阻塞方法后 都要在捕获异常之后，调用Thread.currentThread().interrupt()重置状态标志位。
 */
class InterruptThreadTest3 extends Thread{
    
	public void run() {  
        // 这里调用的是非清除中断标志位的isInterrupted方法  
        while(!Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + " is running");  
            try {  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep begin");  
                Thread.sleep(1000);  
                System.out.println(Thread.currentThread().getName() + " Thread.sleep end");  
            } catch (InterruptedException e) {  
                //由于调用sleep()方法清除状态标志位 所以这里需要再次重置中断标志位 否则线程会继续运行下去  
                Thread.currentThread().interrupt();  
                e.printStackTrace();  
            }  
        }  
        if (Thread.currentThread().isInterrupted()) {  
            System.out.println(Thread.currentThread().getName() + "is interrupted");  
        }  
    }  
      
    public static void test() {  
    	InterruptThreadTest3 itt = new InterruptThreadTest3();  
        itt.start();  
        try {  
            Thread.sleep(5000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
        //中断线程 
        itt.interrupt(); 
    }
}

/**
 * (三)：不可中断线程
 * 有一种情况是线程不能被中断的，就是调用synchronized关键字和reentrantLock.lock()获取锁的过程。
 * 但是如果调用带超时的tryLock方法reentrantLock.tryLock(longtimeout, TimeUnit unit)，那么如果线程在等待时被中断，将
 * 抛出一个InterruptedException异常，这是一个非常有用的特性，因为它允许程序打破死锁。你也可以调用reentrantLock.lockInterruptibly()方法，
 * 它就相当于一个超时设为无限的tryLock方法。
 */
class InterruptThreadTest4{
    
	/**
	 * 制造一个死锁 
	 */
	public void deathLock(Object lock1, Object lock2) {  
        try {  
            synchronized (lock1) {  
                System.out.println(Thread.currentThread().getName()+ " is running");  
                // 让另外一个线程获得另一个锁  
                Thread.sleep(10);  
                // 造成死锁  
                synchronized (lock2) {  
                    System.out.println(Thread.currentThread().getName());  
                }  
            }  
        } catch (InterruptedException e) {  
            System.out.println(Thread.currentThread().getName()+ " is interrupted");  
            e.printStackTrace();  
        }  
    }   
      
    public static void test() {  
        
    	final InterruptThreadTest4 itt = new InterruptThreadTest4();  
    	final Object lock1 = new Object();
    	final Object lock2 = new Object();
    	Thread t1 = new Thread(new Runnable(){  
            public void run() {  
                itt.deathLock(lock1, lock2);  
            }  
        },"A");   
        Thread t2 = new Thread(new Runnable(){  
            public void run() {  
                itt.deathLock(lock2, lock1);  
            }  
        },"B");   
          
        t1.start();  
        t2.start();  
        
        //睡眠3秒，让A和B都启动完成
        try {  
            Thread.sleep(3000);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }
        // 中断线程t1、t2,此时中断将不起作用
        t1.interrupt();  
        t2.interrupt();  
    	
    }
}

