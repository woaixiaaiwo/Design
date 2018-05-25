package com.boge.concurrency.threadmethods;

/**
 * 优先级测试 
 * yield测试
 * 
 *  Java线程中的Thread.yield( )方法，译为线程让步。顾名思义，就是说当一个线程使用了这个方法之后，它就会把自己CPU执行的时间让掉，
 *  让自己或者其它的线程运行，注意是让自己或者其他线程运行，并不是单纯的让给其他线程。
 *  yield()的作用是让步。它能让当前线程由“运行状态”进入到“就绪状态”，从而让其它具有相同优先级的等待线程获取执行权；但是，并不能保
 *  证在当前线程调用yield()之后，其它具有相同优先级的线程就一定能获得执行权；也有可能是当前线程又进入到“运行状态”继续运行！
 *  举个例子：一帮朋友在排队上公交车，轮到Yield的时候，他突然说：我不想先上去了，咱们大家来竞赛上公交车。然后所有人就一块冲向公交车，
 *  有可能是其他人先上车了，也有可能是Yield先上车了。
 *  虽然线程是有优先级的，但是优先级越高的人，就一定能第一个上车吗？这是不一定的，优先级高的人仅仅只是第一个上车的概率大了一点而已，
 *  最终第一个上车的，也有可能是优先级最低的人。并且所谓的优先级执行，是在大量执行次数中才能体现出来的。
 */
public class PriorityAndYieldTest {

	public static void main(String[] args) {
		 // 最高优先级的线程
        Thread max = new Thread() {
            public void run() {
                for(int i = 0; i < 100; i++){
                    System.out.println("max");
                    Thread.yield();
                }
            }
        };
        // 最低优先级的线程
        Thread min = new Thread() {
            public void run() {
                for(int i = 0; i < 100; i++){
                    System.out.println("min");
                    Thread.yield();
                }
            }
        };
        // 默认优先级的线程
        Thread norm = new Thread() {
            public void run() {
                for(int i = 0; i < 100; i++){
                    System.out.println("norm");
                    Thread.yield();
                }
            }
        };
        // void setPriority(int p),设置当前线程的优先级, 最高，最低，默认都有常量对应。
        // 设置了优先级也不能100%控制线程调度。只是最大程度的告知线程调度以更多的几率分配时间片给线程优先级高的线程
        max.setPriority(Thread.MAX_PRIORITY);
        min.setPriority(Thread.MIN_PRIORITY);
        // 这项设置可以省略，默认情况下就是该值
        norm.setPriority(Thread.NORM_PRIORITY);

        min.start();
        norm.start();
        max.start();
	}
	
}
