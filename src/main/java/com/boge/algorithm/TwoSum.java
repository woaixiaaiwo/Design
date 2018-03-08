package com.boge.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * 给定一个整数数列，找出其中和为特定值的那两个数。
 * 你可以假设每个输入都只会有一种答案，同样的元素不能被重用。
 * 
 * 给定 nums = [2, 7, 11, 15], target = 9
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * 所以返回 [0, 1] 
 */
public class TwoSum {

	public static void main(String[] args) {
		int nums[] = {3,2,4};
		
		System.out.println(Arrays.toString(getData(nums,6)));
	}
	
	public static int[] getData(int[] nums, int target){
		
		HashMap<Integer,Integer> map = new HashMap<>();
		
		for(int i=0;i<nums.length;i++)map.put(nums[i],i);
		
		for(int i=0;i<nums.length;i++){
			int data = target-nums[i];
			if(map.containsKey(data) && map.get(data) != null && i != map.get(data)){
				return new int[]{i,map.get(data)};
			}
		}
		return new int[]{};
	}
}
