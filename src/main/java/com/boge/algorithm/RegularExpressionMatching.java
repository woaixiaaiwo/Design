package com.boge.algorithm;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;


/**
 * 实现支持 '.' 和 '*' 的正则表达式匹配。
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个输入字符串（不是部分字符串）。
 * 函数:
 * bool isMatch(const char *s, const char *p)
 * 例子:
	isMatch("aa","a") → false
	isMatch("aa","aa") → true
	isMatch("aaa","aa") → false
	isMatch("aa", "a*") → true
	isMatch("aa", ".*") → true
	isMatch("ab", ".*") → true
	isMatch("aab", "c*a*b") → true 
	
	由于涉及到NFA到DFA的化简，暂时不做
 */
public class RegularExpressionMatching {

	//思路：使用简单的有限状态自动机
	
	//代表任意的输入
	private static String any = "?";
	//代表任意的输入
	private static String split = "#";
	
	private static int lastState = 0;
	
	public static boolean isMatch(String s, String p) {
		char[] charArr = s.toCharArray();
		
		if(charArr.length == 0){
			if(p == null || "".equals(p)){
				return true;
			}else{
				return false;
			}
		}
		
		Integer state = 0;
		if(state == null)return false;
		HashMap<Integer,Map<String,String>> map = (HashMap<Integer,Map<String,String>>) createSimpleNfa(p);
		Map<String,String> regular = null;
		int i = 0;
		boolean notMatch = true;
		while(true){
			regular = map.get(state);
			//规则未找到
			if(regular == null){
				//判断是否已经越界，越界则回滚
				regular = map.get(state-1);
				if(regular != null && regular.containsKey("rollback")){
					state = Integer.valueOf(regular.get("rollback"));
					regular = map.get(state);
				}else{
					//规则还未找到，说明正则表达式为空，不匹配
					return false;
				}
				
			}
			if(regular.containsKey(charArr[i]+"")){
				state = Integer.valueOf(regular.get(charArr[i]+""));
				notMatch = false;
				i++;
			}else if(regular.containsKey("?")){
				state = Integer.valueOf(regular.get("?"));
				notMatch = false;
				i++;
			}else if(regular.containsKey("rollback")){
				state = Integer.valueOf(regular.get("rollback"));
				notMatch = false;
			}else{
				if(!notMatch){
					return false;
				}
				state++;
			}
			if(i == s.length()){
				break;
			}
		}
		if(state==lastState){
			return true;
		}
        return false;
    }
	
	/**
	 * 通过正则表达式创建简单的NFA 
	 */
	private static Map<Integer,Map<String,String>> createSimpleNfa(String p){
		
		char[] charArr = p.toCharArray();
		
		Map<Integer,Map<String,String>> dfaMap = new HashMap<>();
		if(charArr.length == 0 || charArr[0] == '*')return dfaMap;
		
		int hasAny = -1;
		int state = 0;
		
		for(int i=0;i<charArr.length;i++){
			if(charArr[i] == '.'){
				putValue(dfaMap,state,any+split+(state+1+""));
				state = state+1;
				lastState = state;
			}else if(charArr[i] == '*'){
				if(charArr[i-1] == '.'){
					state = state-1;
					hasAny = state;
					putValue(dfaMap,state,any+split+(state+""));
				}else{
					putValue(dfaMap,state,charArr[i-1]+split+(state+""));
					if(i+1 <= charArr.length-1){
						putValue(dfaMap,state-1,charArr[i+1]+split+((state+1)+""));
					}
				}
				lastState = state;
			}else{
				putValue(dfaMap,state,charArr[i]+split+(state+1)+"");
				if(hasAny != -1){
					putValue(dfaMap,state,"rollback"+split+(hasAny)+"");
				}
				state = state+1;
				lastState = state;
			}
		}
		
		return dfaMap;
	}
	
	private static void putValue(Map<Integer,Map<String,String>> map,Integer key,String value){
		Map<String,String> mapValue = map.get(key);
		String[] valueArr = value.split(split);
		if(mapValue == null){
			mapValue = new HashMap<>();
		}
		mapValue.put(valueArr[0],valueArr[1]);
		map.put(key,mapValue);
	}
	
	public static void main(String[] args) {
		
		HashMap<Integer,Map<String,String>> map = new HashMap<>();
		
		String p = "a*a";//"fd.*f.*sdff";
		
		map = (HashMap<Integer,Map<String,String>>) createSimpleNfa(p);
		
		for(Map.Entry<Integer,Map<String,String>> entry:map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		System.out.println(lastState);
		System.out.println(isMatch("aaa","a*a"));
	}
}
