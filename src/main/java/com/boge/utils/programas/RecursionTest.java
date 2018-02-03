package com.boge.utils.programas;

import java.util.ArrayList;
import java.util.List;

public class RecursionTest {

	/**
	 * 排列方法 
	 */
	public static void pailie(int i,char[] data){
		if(i == data.length){
			System.out.println(new String(data));
		}
		for(int j=i;j<data.length;j++){
			swap(data,j,i);
			pailie(i+1,data);
			swap(data,j,i);
		}
		
	}
	
	/**
	 * 组合方法 
	 */
	public static void zuhe(int begin,int number,char[] data,List<Character> list){
		
		if(list.size() == number){
			System.out.println(list);
			return;
		}
		
		for(int i=begin;i<data.length;i++){
			list.add(data[i]);
			zuhe(i+1,number,data,list);
			list.remove(list.size()-1);
		}
		
	}
	
	public static void swap(char[] data,int i,int j){
		
		char temp = data[i];
		data[i] = data[j];
		data[j] = temp;
		
	}
	

	
	public static void main(String[] args) {
		char[] data = "abcd".toCharArray();
		//pailie(0,data);
		
		/*for(int i=1;i<=data.length;i++){
			zuhe(0,i,data, new ArrayList<Character>());
		}*/
		zuhe(0,2,data, new ArrayList<Character>());
		
	}
	
}
