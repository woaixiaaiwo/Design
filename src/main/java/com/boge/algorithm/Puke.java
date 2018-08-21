package com.boge.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 保存扑克顺序 
 */
public class Puke {

	private class Node implements Comparable<Node>{
	  public int num;
	  public int times;
	  
	  public Node(int num,int times){
		  this.num = num;
		  this.times = times;
	  }
	  
	  @Override
	  public int compareTo(Node o) {
		// TODO Auto-generated method stub
		  
		  if(o.times == times){
			  return o.num-num;
		  }
		  return o.times-times;
	  }

	  @Override
	  public String toString() {
		 return "Node [num=" + num + ", times=" + times + "]";
	  }
	}
	
	private List<Node> list;
	
	private int sequenceLength;
	
	public Puke(String str){
		Map<Integer,Node> map = new HashMap<Integer, Node>();
		for(char c:str.toCharArray()){
			Node node = map.get(c+0);
			if(node == null){
				node = new Node(c,0);
				map.put(c+0,node);
			}
			node.times++;
		}
		list = new ArrayList<Node>();
		sequenceLength = str.length();
		list.addAll(map.values());
		Collections.sort(list);
	}
	
	



	@Override
	public String toString() {
		return "Puke [list=" + list + ", sequenceLength=" + sequenceLength
				+ "]";
	}





	public static void main(String[] args) {
		String string = "xcaaabbb";
		System.out.println(new Puke(string));
	}
	
	
}
