package com.boge.algorithm.map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;



/**
 * 使用数组实现的循环队列
 */
public class ArrayQueue<E>{
	
	//默认容量
	private static final int DEFAULT_CAPACITY = 3;
	
	//数据数组
    Object[] elementData;
    
    private int size;
    
    public ArrayQueue(){
    	this(DEFAULT_CAPACITY);
    }
    
    public ArrayQueue(int arrSize){
    	elementData = new Object[arrSize];
    }
    
    //入队
    public void enqueue(E e){
    	if(size >= elementData.length){
    		Object[] newEleData = new Object[elementData.length<<1];
    		System.arraycopy(elementData, 0, newEleData, 0, elementData.length);
    		elementData = newEleData;
    	}
    	elementData[size++] = e;
    }
    
    //出队
    public E dequeue(){
    	if(size <= 0)return null;
    	E res = (E) elementData[0];
    	System.arraycopy(elementData, 1, elementData, 0, elementData.length-1);
    	elementData[elementData.length - 1] = null;
    	size--;
		return res;
    }
    
    public boolean isEmpty(){
    	return size == 0;
    }
    
    public Iterator itetor(){
    	return new Itr();
    }
    
    private class Itr implements Iterator<E>{

    	int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such
    	
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor != size ;
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementData = ArrayQueue.this.elementData;
            cursor = i+1;
			return (E) elementData[lastRet = i];
		}
    	
    }
    
    public void printQueue(){
    	System.out.println(Arrays.toString(elementData));
    }
	
    public static void main(String[] args) {
		ArrayQueue<String> arrayQueue = new ArrayQueue<>();
		arrayQueue.enqueue("123");
		arrayQueue.enqueue("456");
		arrayQueue.enqueue("789");
		arrayQueue.enqueue("146");
		Iterator<String> iterator = arrayQueue.itetor();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		/*arrayQueue.printQueue();
		System.out.println(arrayQueue.dequeue());
		arrayQueue.printQueue();
		System.out.println(arrayQueue.dequeue());
		arrayQueue.printQueue();
		System.out.println(arrayQueue.dequeue());
		arrayQueue.printQueue();
		System.out.println(arrayQueue.dequeue());
		arrayQueue.printQueue();
		System.out.println(arrayQueue.dequeue());
		arrayQueue.printQueue();*/
	}
}
