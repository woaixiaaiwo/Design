package com.boge.concurrency;

public class TestStatic {

	private static StaticTest s1 = new StaticTest(1);
	
	public static void main(String[] args) {
		
		//s1 = new StaticTest(1);
		System.out.println(s1.hashCode());
		s1 = new StaticTest(1);
		System.out.println(s1.hashCode());
		System.gc();
		
	}
	
}
class StaticTest{
	private int a;
	StaticTest(int a){
		this.a = a;
	}
	
	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		System.out.println(this.hashCode()+"被回收了!");
	}
}