package com.boge.references;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;


/**
 * 软引用 
 * 软引用在java.lang.ref包中有与之对应的类java.lang.ref.SoftReference。 
 * 重点： 被软引用指向的对象不会被垃圾收集器收集(即使该对象没有强引用指向它)，除非jvm使用内存不够了，才会对这类对象进行销毁，释放内存。
 */
public class SoftReferences {

	public static void main(String[] args) throws InterruptedException {
		
        //创建一个软引用指向这个对象,使用-Xmx10m参数，可以将堆内存分配为10M
        SoftReference<Sample> softRef = new SoftReference<Sample>(new Sample());

        //如果此时堆内存不够用了，软引用指向的对象就会被回收。
        List<Object> list = new ArrayList<Object>();
        while(true){
        	/*如果重写了Sample类的finalize方法，内存不足时就可以看到
        	该方法被调用，说明Sample对象被回收*/
        	System.out.println(softRef.get());
        	list.add(new byte[1024 * 1024 * 1]);
        	Thread.sleep(100);
        }
	}
	
}
