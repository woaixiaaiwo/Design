package com.boge.algorithm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * 给定一个字符串，找出不含有重复字符的 最长子串 的长度。
示例：

给定 "abcabcbb" ，没有重复字符的最长子串是 "abc" ，那么长度就是3。

给定 "bbbbb" ，最长的子串就是 "b" ，长度是1。

给定 "pwwkew" ，最长子串是 "wke" ，长度是3。请注意答案必须是一个子串，"pwke" 是 子序列 而不是子串。 
 */
public class LongestSubstring {

	public static void main(String[] args) {
		System.out.println(lengthOfLongestSubstring("adbdca"));
	}
	
	public static int lengthOfLongestSubstring(String s) {
		int begin=0;
		int num = 0;
		Map<Character,Integer> map = new HashMap<>();
		for(int end = 0;end<s.length();end++){
			Character key = s.charAt(end);
			if(map.containsKey(key)){
				begin = Math.max(begin,map.get(key)+1);
			}
			num = Math.max(num, end-begin+1);
			map.put(key,end);
		}
		return num;
    }
	
}
