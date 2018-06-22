package com.boge.concurrency.customutils;

/**
 * 使用wait和notify实现阻塞队列
 */
public class BoundedBuffer<V> extends BaseBoundedBuffer<V>{
	
	public BoundedBuffer(int capacity) {
		super(capacity);
		// TODO Auto-generated constructor stub
	}

	public synchronized void put(V v)throws InterruptedException{
		//如果队列已满，阻塞
		while(isFull()){
			System.out.println("队列已满，阻塞...");
			wait();
		}
		doPut(v);
		//插入一条数据，唤醒所有线程。其实主要是为了唤醒get线程
		notifyAll();
	}
	
	public synchronized  V take()throws InterruptedException{
		//如果队列为空，阻塞
		while(isEmpty()){
			System.out.println("队列为空，阻塞...");
			wait();
		}
		//取出一条数据，唤醒所有线程。其实主要是为了唤醒put线程
		V v = doTake();
		notifyAll();
		return v;
	}
	
	public static void main(String[] args) {
		BoundedBuffer boundedBuffer = new BoundedBuffer<String>(5);
		PutThread putThread = new PutThread(boundedBuffer);
		GetThread getThread = new GetThread(boundedBuffer);
		
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
