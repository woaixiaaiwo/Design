package com.boge.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddThreeNum {

	public static void main(String[] args) {
		int nums[] = {-1, 0, 1, 2, -1, -4};
		getMap(nums);
	}
	
	private static void getMap(int[] nums) {
		Map<Integer,Set<String>> map = new HashMap<>();
		Set<String> set = null;
		for(int i=0;i<nums.length;i++) {
			for(int j=i+1;j<nums.length;j++) {
				Integer key = nums[i]+nums[j];
				String value = i+";"+j;//nums[i]>nums[j]?nums[j]+";"+nums[i]:nums[i]+";"+nums[j];
				set = map.get(key);
				if(set == null) {
					set = new HashSet<>();
				}
				set.add(value);
				map.put(key, set);
			}
		}
		
		for(int i=0;i<nums.length;i++) {
			
		}
		
		
		System.out.println(map);
	}
	
}
