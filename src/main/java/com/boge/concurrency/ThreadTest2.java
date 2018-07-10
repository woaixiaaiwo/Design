package com.boge.concurrency;

public class ThreadTest2 {

	private static B b = new B();
	
	public static void main(String[] args) {
		
		new T(b, 'A', 'B').start();
		new T(b, 'B', 'C').start();
		new T(b, 'C', 'A').start();
		
	}
	
	
}


class B{	
	public char x = 'A';
}

class T extends Thread{
	
	public T(B b,char sysChar,char nextChar) {
		this.b = b;
		this.sysChar = sysChar;
		this.nextChar = nextChar;
	}
	
	public volatile  B b;
	public char sysChar;
	public char nextChar;
	
	@Override
	public void run() {
		while(true) {
			if(b.x == sysChar) {
				System.out.println(sysChar);
				b.x = nextChar;
				//System.out.println(b);
			}else {
				//System.out.println("ccccccc===="+c.x);
			}
		}
	}
}






