package com.boge.references;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;


/**
 * 虚引用 
 * 虚幻应用和弱引用的回收机制差不多，都是可以被随时回收的。但是不同的地方是，它的构造方法必须强制传入ReferenceQueue，
 * 因为在jvm回收前(重点： 对，就是回收前，软引用和弱引用都是回收后)，会将PhantomReference对象加入ReferenceQueue中; 
 * 还有一点就是PhantomReference.get()方法永远返回空，不管对象有没有被回收。
 */
public class PhantomReferences {
	
	private static final ReferenceQueue<Sample> QUEUE = new ReferenceQueue<>();

	public static void main(String[] args) throws InterruptedException {
		
        //创建一个虚引用指向这个对象
		Sample sample = new Sample();
		//如果重写了Sample的finalize方法，那么就不会放入引用队列中
		PhantomReference<Sample> phantomRef = new PhantomReference<Sample>(sample,QUEUE);
		sample = null;
		//永远都返回null
        System.out.println(phantomRef.get());
        
        System.gc();
        Thread.sleep(2000);
        if(QUEUE.poll()!=null){
        	System.out.println("虚引用被回收了");
        }
        
	}
	
}
