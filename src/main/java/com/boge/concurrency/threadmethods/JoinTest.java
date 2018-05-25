package com.boge.concurrency.threadmethods;

/**
 * 线程的协同工作 join方法
 * 
 * join方法的原理，等于开启了一个同步块，
 * 在同步块中wait调用join方法的这个线程对象
 * 等这个线程运行结束后，notifyAll这个线程对象
 */
public class JoinTest {

	// 判断照片是否下载完成
    public static boolean isFinish = false;

    public static void main(String[] args) {
    	
    	syncMethod();
    	
    }
    
    //使用join实现同步功能
    private static void syncMethod(){
    	Thread thread1 = new Thread(new Runnable(){
    		@Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("我是线程1");
				 for(int i = 0; i <= 100; i++){
                    System.out.println("线程1已完成" + i + "%");
				 }
			}
    		
    	});
    	
    	Thread thread2 = new Thread(new Runnable(){
    		@Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("我是线程2");
				 for(int i = 0; i <= 100; i++){
                    System.out.println("线程2已完成" + i + "%");
				 }
			}
    		
    	});
    	
    	Thread thread3 = new Thread(new Runnable(){
    		@Override
			public void run() {
				// TODO Auto-generated method stub
				 System.out.println("我是线程3");
				 for(int i = 0; i <= 100; i++){
                    System.out.println("线程3已完成" + i + "%");
				 }
			}
    		
    	});
    	
    	//不使用join时:
    	//thread1.start();
    	//thread2.start();
    	//thread3.start();
    	
    	//使用join时:
    	try {
    		thread1.start();
        	thread2.start();
    		thread2.join();
    		thread1.join();
        	thread3.start();
    		thread3.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //使用join完成图片下载和展示
    private static void picDownloadAndShow(){

        // 下载图片的线程
        final Thread download = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("download:开始下载图片");
                for(int i = 0; i <= 100; i++){
                    System.out.println("download:已完成" + i + "%");
                    try{
                        Thread.sleep(50);
                    }
                    catch(InterruptedException e){
                        e.printStackTrace();
                        if(Thread.currentThread().isInterrupted()){
                        }
                    }
                }
                System.out.println("download:图片下载完毕");
                isFinish = true;
            }
        });
        download.start();

        // 用于显示图片的线程
        Thread showImg = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("show:准备显示图片");
                // 等待下载线程工作结束后,再执行下面的代码,
                try{
                    // 此时显示图片的线程就进入阻塞状态,等待download线程运行结束,才能执行下面的代码。注意千万不要在永远也死不了的线程上等待
                    download.join();
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                if(!isFinish){
                    throw new RuntimeException("show:图片还没有下载完");
                }
                System.out.println("show:图片显示完成！");
            }
        });
        showImg.start();
    }
	
}
