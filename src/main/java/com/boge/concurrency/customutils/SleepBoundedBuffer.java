package com.boge.concurrency.customutils;

/**
 * 使用轮询与休眠实现阻塞队列 
 */
public class SleepBoundedBuffer<V> extends BaseBoundedBuffer<V>{

	private final Long SLEEP_TIME = 2000L;
	
	public SleepBoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public void put(V v)throws InterruptedException{
		while(true){
			//先请求锁
			synchronized (this) {
				System.out.println(Thread.currentThread().getName()+":获取到锁，判断队列是否已满...");
				//不是满的
				if(!isFull()){
					System.out.println(Thread.currentThread().getName()+":队列未满，插入数据并释放锁...");
					this.doPut(v);
					return;
				}
				System.out.println(Thread.currentThread().getName()+":队列已满，休眠...");
			}
			//休眠2s后再去竞争锁
			Thread.sleep(SLEEP_TIME);
		}
	}
	
	public V take()throws InterruptedException{
		while(true){
			//先请求锁
			synchronized (this) {
				System.out.println(Thread.currentThread().getName()+":获取到锁，判断队列是否为空...");
				//不是空的
				if(!isEmpty()){
					return this.doTake();
				}
			}
			System.out.println(Thread.currentThread().getName()+":队列为空，休眠...");
			//休眠2s后再去竞争锁
			Thread.sleep(SLEEP_TIME);
		}
	}
	
	public static void main(String[] args) {
		SleepBoundedBuffer sleepBoundedBuffer = new SleepBoundedBuffer<String>(5);
		PutThread putThread = new PutThread(sleepBoundedBuffer);
		GetThread getThread = new GetThread(sleepBoundedBuffer);
		
		Thread putThread1 = new Thread(putThread,"生产者1");
		Thread putThread2 = new Thread(putThread,"生产者2");
		Thread putThread3 = new Thread(putThread,"生产者3");
		
		Thread getThread1 = new Thread(getThread,"消费者1");
		Thread getThread2 = new Thread(getThread,"消费者2");
		
		putThread1.start();
		putThread2.start();
		putThread3.start();
		//getThread1.start();
		//getThread2.start();
	}
	
}
