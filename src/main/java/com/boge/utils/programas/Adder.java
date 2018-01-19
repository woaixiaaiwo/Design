package com.boge.utils.programas;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;

public class Adder {
	
	public static void main(String[] args) {
		//System.out.println(Arrays.toString(fullAdder(carry, a, b)));
		/*int input1[] = {0,0,1,1,0,0,1,1};
		int input2[] = {0,1,0,1,0,1,0,1};
		int carry[] = {0,0,0,0,1,1,1,1};
		for(int i=-1;i<8;i++){
			if(i == -1){
				System.out.println("A输入\tB输入\t进位输入\t和输出\t进位输出\t");
			}
			else{
				int[] data = fullAdder(carry[i],input1[i],input2[i]);
				System.out.println(" "+input1[i]+"\t "+input2[i]+"\t  "+carry[i]+"\t  "+data[0]+"\t  "+data[1]);
			}
			
		}*/
		/*int a=4352;
		int b=-9;
		System.out.println(Integer.toBinaryString(a+b));
		System.out.println(Integer.toBinaryString(a));
		System.out.println(Integer.toBinaryString(b));*/
		
		A a = new A();
		B b = new B(12,"fds");
		a.setB(b);
		System.out.println(a.getB());
		B b1 = a.getB();
		b1.setAge(13);
		System.out.println(a.getB());
		System.out.println(b.hashCode());
		System.out.println(a.getB().hashCode());
		System.out.println(b1.hashCode());
		//System.out.println(add(a,b));
	}
	
	/**
	 * 一个半加器是由一个异或门和一个与门表示的
	 * 异或门输出和
	 * 与门输出进位 
	 */
	public static int[] halfAdder(int input1,int input2){
		int data[] = new int[2];
		data[0] = input1^input2;
		data[1] = input1&input2;
		return data;
	}
	
	/**
	 * 一个全加器是由两个半加器和一个或门组成的
	 */
	public static int[] fullAdder(int carry,int input1,int input2){
		int data[] = new int[2];
		
		//输出两个输入数据的进位以及和
		int halfData1[] = halfAdder(input1,input2);
		//半加器1的和和进位相加，输出和以及进位
		int halfData2[] = halfAdder(carry,halfData1[0]);
		//半加器2的和就是全加器的和
		data[0] = halfData2[0];
		//全加器的进位是两个半加器的进位的或
		data[1] = halfData1[1]|halfData2[1];
		return data;
	}
	
	public static String fillData(final String input1,final String input2){
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<input1.length()-input2.length();i++)sb.append("0");
		return sb.toString()+input2;
	}
	
	public static int add(final int input1,final int input2){
		String param1 = Integer.toBinaryString(input1);
		String param2 = Integer.toBinaryString(input2);
		if(param1.length() != param2.length()){
			//补齐
			if(param1.length()>param2.length()){
				param2 = fillData(param1, param2);
			}else{
				param1 = fillData(param2, param1);
			}
		}
		int length = param1.length()+1;
		char[] data = new char[length];
		int fullData[] = new int[2];
		int fullAdderCarry = 0;
		int fullAdderParam1=0;
		int fullAdderParam2=0;
		for(int i=length-1;i>=1;i--){
			fullAdderParam1 = param1.charAt(i-1)-'0';
			fullAdderParam2 = param2.charAt(i-1)-'0';
			fullData = fullAdder(fullAdderCarry,fullAdderParam1,fullAdderParam2);
			fullAdderCarry = fullData[1];
			data[i] = (char)(fullData[0]+'0');
		}
		data[0] = (char)(fullData[1]+'0');
		if(data.length>32){
			char data1[] = new char[32];
			System.arraycopy(data,data.length-32,data1,0,32);
			data = data1;
		}
		//return new String(data);
		return Integer.parseInt(new String(data),2);
	}

}


class A{
	private B b;

	public B getB() {
		return (B) b.clone();
	}

	public void setB(B b) {
		this.b = b;
	}
	
	
	
}

class B implements Cloneable{
	
	private int age;
	
	private String name;
	
	public B(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "B [age=" + age + ", name=" + name + "]";
	}
	
	public Object clone(){
		
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return object;
	}
	
}