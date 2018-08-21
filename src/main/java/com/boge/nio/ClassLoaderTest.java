package com.boge.nio;

public class ClassLoaderTest {

	public static void main(String[] args) throws ClassNotFoundException {
		
		ClassLoaderTest.class.getClassLoader().loadClass("com.boge.nio.TestClass");
		
		Class.forName("com.boge.nio.TestClass");
		
	}
	
	
}
class TestClass{
	
	public static int a;
	
	static {
		a = 2;
		System.out.println("a="+a);
	}
	
}