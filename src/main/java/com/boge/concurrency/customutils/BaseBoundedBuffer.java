package com.boge.concurrency.customutils;

/**
 * 条件队列基类 
 */
public abstract class BaseBoundedBuffer<V> {

	private final V[] buf;
	
	private int tail;
	
	private int head;
	
	private int count;
	
	protected BaseBoundedBuffer(int capacity){
		buf = (V[]) new Object[capacity];
	}
	
	protected synchronized final void doPut(V v){
		buf[tail] = v;
		if(++tail == buf.length){
			tail = 0;
		}
		count++;
	}
	
	protected synchronized final V doTake(){
		V v = buf[head];
		buf[head] = null;
		if(++head == buf.length){
			head = 0;
		}
		count--;
		return v;
	}
	
	public synchronized final boolean isFull(){
		return count == buf.length;
	}
	
	public synchronized final boolean isEmpty(){
		return count == 0;
	}
	
	public abstract void put(V v) throws InterruptedException;
	
	public abstract V take() throws InterruptedException;
}
