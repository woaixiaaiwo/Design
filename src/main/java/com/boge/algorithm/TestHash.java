package com.boge.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TestHash {

	public static void main(String[] args) {
		
		Random random = new Random();
		int data = 10000000;
		int datas[] = new int[data];
		for(int i=0;i<data;i++){
			int value = random.nextInt();
			datas[i] = value;
		}
		HashMap<Integer,Integer> hashMap = new HashMap();
		Long start1 = System.currentTimeMillis();
		for(int i=0;i<data;i++){
			hashMap.put(datas[i],datas[i]);
		}
		Long end1 = System.currentTimeMillis();
		System.out.println("耗时:"+(end1-start1)/1000);
		

		hashMap = new HashMap(1<<18,(float)0.95);
		start1 = System.currentTimeMillis();
		for(int i=0;i<data;i++){
			hashMap.put(datas[i],datas[i]);
		}
		end1 = System.currentTimeMillis();
		System.out.println("耗时:"+(end1-start1)/1000);
	}
	
}
