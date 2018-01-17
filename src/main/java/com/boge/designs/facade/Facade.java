package com.boge.designs.facade;

import com.boge.designs.facade.oldinterfaces.Old1;
import com.boge.designs.facade.oldinterfaces.Old2;
import com.boge.designs.facade.oldinterfaces.Old3;


public class Facade {

	public void invoke1(){
		new Old1().print();
		new Old2().print();
	}
	
	public void invoke2(){
		new Old1().print();
		new Old3().print();
	}
	
	public void invoke3(){
		new Old2().print();
		new Old3().print();
	}
	
	public static void main(String[] args) {
		new Facade().invoke1();
	}
}
