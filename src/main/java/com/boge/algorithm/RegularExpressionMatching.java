package com.boge.algorithm;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
 */
public class RegularExpressionMatching {

	//思路：使用简单的有限状态自动机
	
	//代表任意的输入
	private static String any = "?";
	//代表任意的输入
	private static String split = "#";
	
	public static boolean isMatch(String s, String p) {
		char[] charArr = s.toCharArray();
		
		if(charArr.length == 0){
			if(StringUtils.isEmpty(p)){
				return true;
			}else{
				return false;
			}
		}
		
		Integer state=0;
		HashMap<Integer,Map<String,String>> map = (HashMap<Integer,Map<String,String>>) createSimpleDfa(p);
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
		if(state==map.size()){
			return true;
		}
        return false;
    }
	
	private static Map<Integer,Map<String,String>> createSimpleDfa(String p){
		
		char[] charArr = p.toCharArray();
		
		Map<Integer,Map<String,String>> dfaMap = new HashMap<>();
		if(charArr.length == 0 || charArr[0] == '*')return dfaMap;
		
		int hasAny = -1;
		int state = 0;
		
		for(int i=0;i<charArr.length;i++){
			if(charArr[i] == '.'){
				putValue(dfaMap,state,any+split+(state+1+""));
				state = state+1;
			}else if(charArr[i] == '*'){
				if(charArr[i-1] == '.'){
					state = state-1;
					hasAny = state;
					putValue(dfaMap,state,any+split+(state+""));
				}else{
					putValue(dfaMap,state,charArr[i-1]+split+(state+""));
				}
			}else{
				putValue(dfaMap,state,charArr[i]+split+(state+1)+"");
				if(hasAny != -1 && hasAny != state){
					putValue(dfaMap,state,"rollback"+split+(hasAny)+"");
				}
				state = state+1;
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
		
		String p = "fd.*f.*sdff";//"fd.*f.*sdff";
		
		map = (HashMap<Integer,Map<String,String>>) createSimpleDfa(p);
		
		for(Map.Entry<Integer,Map<String,String>> entry:map.entrySet()){
			System.out.println(entry.getKey()+":"+entry.getValue());
		}
		
		System.out.println(isMatch("fdfsdffffffsdfsd","fd.*f.*sdf"));
	}
}
