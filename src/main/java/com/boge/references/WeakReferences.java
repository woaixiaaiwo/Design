package com.boge.references;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * 弱引用会被jvm忽略，也就说在GC进行垃圾收集的时候，如果一个对象只有弱引用指向它，那么和没有引用指向它是一样的效果，jvm都会对它就行果断的销毁，释放内存。
 * 其实这个特性是很有用的，jdk也提供了java.util.WeakHashMap这么一个key为弱引用的Map。比如某个资源对象你要释放(比如 db connection), 但是如果
 * 被其它map作为key强引用了，就无法被jvm收集。
 */
public class WeakReferences {

	public static void main(String[] args) throws InterruptedException {
		testWeakReferences1();
		//testWeakReferences2();
	}
	
	/**
	 * 弱引用会被JVM忽略，直接回收 
	 */
	public static void testWeakReferences1(){
		Sample sample = new Sample();
		WeakReference<Sample> weakRef = new WeakReference<Sample>(sample);
		System.out.println(sample);
		//此时只有弱引用指向sample了，sample会被回收
		sample = null;
		System.gc();
		//这里会返回null
		System.out.println(sample);
	}
	
	/**
	 * 弱引用Map
	 * @throws InterruptedException 
	 */
	public static void testWeakReferences2() throws InterruptedException{
		WeakReference<Sample> weakRef = new WeakReference<Sample>(new Sample());

		//此处如果使用hashMap，就算使用弱引用，GC也会认为weakRef指向的对象不可回收，不能回收该Key对象
        //Map<Sample,String> map = new HashMap<Sample, String>();
        //此处如果使用WeakHashMap，Key使用弱引用，而且WeakHashMap的Key是可以回收的，所以weakRef指向的对象会被回收
        Map<Sample,String> map = new WeakHashMap<Sample, String>();
        
        map.put(weakRef.get(),"test");

        //这里会返回test
        System.out.println(map.get(weakRef.get()));
        System.gc();
        Thread.sleep(2000);
        //这里会返回null，说明键已经被回收
        System.out.println(map.get(weakRef.get()));

        //此处使用WeakHashMap，key是一个对象，但是没有其他引用指向它
        //所以会被GC回收，map的长度会变化
        Map<Sample,String> map2 = new WeakHashMap<Sample, String>();
        map2.put(new Sample(),"test");
        //这里返回1
        System.out.println(map2.size());
        System.gc();
        Thread.sleep(2000);
        //这里返回0，说明键已经被回收
        System.out.println(map2.size());
	}
	
}
