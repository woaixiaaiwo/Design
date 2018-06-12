package com.boge.references;


/**
 * 强引用 
 */
public class StrongReferences {

	public static void main(String[] args) {
		
		//创建一个对象，new出来的对象都是分配在java堆中的
        Sample sample = new Sample();   //sample这个引用就是强引用

        sample = null;                  //将这个引用指向空指针,
                                        //那么上面那个刚new来的对象就没用任何其它有效的引用指向它了
                                        //也就说该对象对于垃圾收集器是符合条件的
                                        //因此在接下来某个时间点 GC进行收集动作的时候, 该对象将会被销毁，内存被释放
		System.gc();
	}
	
}
