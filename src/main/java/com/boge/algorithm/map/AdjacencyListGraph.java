package com.boge.algorithm.map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;

/**
 * 使用邻接表表示有向图 
 */
public class AdjacencyListGraph<E>{

	//节点
	private class Node{
		//元素
		public E ele;
		//节点名称(唯一标识)
		public String name;
		//邻接表
		public List<Vertex> adjacencyList;
		//入度
		public int inDegree;
		
		public Node(E ele,String name){
			this(ele, 0, name);
		}
		
		public Node(E ele,int weight,String name){
			this.ele = ele;
			this.name = name;
			inDegree = 0;
			adjacencyList = new ArrayList<Vertex>();
		}

		@Override
		public String toString() {
			return "Node [name=" + name + ", adjacencyList=" + adjacencyList
					+ ", inDegree=" + inDegree + "]";
		}
	}
	
	private class Vertex{
		
		//权重
		public int weight;
		
		//节点名
		public String nodeName;
		
		public Vertex(String nodeName){
			this(0, nodeName);
		}
		
		public Vertex(int weight,String nodeName){
			this.weight = weight;
			this.nodeName = nodeName;
		}

		@Override
		public String toString() {
			return "Vertex [weight=" + weight + ", nodeName=" + nodeName + "]";
		}
		
		
	}
	
	private Map<String,Node> graphEle;
	
	public AdjacencyListGraph(){
		graphEle = new HashMap<String,Node>();
	}
	
	public void add(E e,String name){
		graphEle.put(name,new Node(e,name));
	}
	
	public void link(String nodeName1,String nodeName2){
		if(graphEle.get(nodeName1) == null || graphEle.get(nodeName2) == null){
			throw new RuntimeException("元素为空!");
		}
		graphEle.get(nodeName1).adjacencyList.add(new Vertex(nodeName2));
		graphEle.get(nodeName2).inDegree++;
	}
	
	public void printMap(){
		for(Map.Entry<String,Node> entry:graphEle.entrySet()){
			System.out.println(entry.getKey()+"--->"+entry.getValue());
		}
	}
	
	/**
	 * 拓扑排序，使用队列 
	 */
	public void topologySort(){
		List<String> sortList = new ArrayList<>();
		ArrayQueue<String> arrayQueue = new ArrayQueue<>();
		//将入度为0的节点入队
		for(Map.Entry<String,Node> entry:graphEle.entrySet()){
			if(entry.getValue().inDegree == 0){
				arrayQueue.enqueue(entry.getKey());
			}
		}
		if(arrayQueue.isEmpty()){
			throw new RuntimeException("Has Cicle");
		}
		String nodeName;
		Node node;
		while (true) {
			if(arrayQueue.isEmpty()){
				if(sortList.size() != graphEle.size()){
					throw new RuntimeException("Has Cicle");
				}else{
					break;
				}
			}
			nodeName = arrayQueue.dequeue();
			node = graphEle.get(nodeName);
			sortList.add(nodeName);
			if(CollectionUtils.isNotEmpty(node.adjacencyList)){
				for(Vertex str:node.adjacencyList){
					graphEle.get(str.nodeName).inDegree--;
					if(graphEle.get(str.nodeName).inDegree == 0){
						arrayQueue.enqueue(str.nodeName);
					}
				}
			}
		}
		System.out.println(sortList);
	}
	
	/**
	 * 广度优先搜索 (无权最短路径)
	 */
	public void scopeSearch(String start){
		List<String> sortList = new ArrayList<>();
		ArrayQueue<String> arrayQueue = new ArrayQueue<>();
		arrayQueue.enqueue(start);
		String key;
		Node node;
		while (true) {
			if(arrayQueue.isEmpty())break;
			key = arrayQueue.dequeue();
			if(!sortList.contains(key)){
				sortList.add(key);
			}
			node = graphEle.get(key);
			for(Vertex s:node.adjacencyList){
				if(!sortList.contains(s.nodeName)){
					arrayQueue.enqueue(s.nodeName);
				}
			}
		}
		System.out.println(sortList);
	}
	
	public static void main(String[] args) {
		
		AdjacencyListGraph<String> AdjacencyListGraph = new AdjacencyListGraph<>();
		AdjacencyListGraph.add("v1","v1");
		AdjacencyListGraph.add("v2","v2");
		AdjacencyListGraph.add("v3","v3");
		AdjacencyListGraph.add("v4","v4");
		AdjacencyListGraph.add("v5","v5");
		AdjacencyListGraph.add("v6","v6");
		AdjacencyListGraph.add("v7","v7");
		
		AdjacencyListGraph.link("v1","v2");
		AdjacencyListGraph.link("v1","v4");
		

		AdjacencyListGraph.link("v2","v4");
		AdjacencyListGraph.link("v2","v5");
		
		AdjacencyListGraph.link("v3","v1");
		AdjacencyListGraph.link("v3","v6");
		
		AdjacencyListGraph.link("v4","v3");
		AdjacencyListGraph.link("v4","v6");
		AdjacencyListGraph.link("v4","v7");
		
		AdjacencyListGraph.link("v5","v4");
		AdjacencyListGraph.link("v5","v7");
		
		AdjacencyListGraph.link("v7","v6");
		AdjacencyListGraph.printMap();
		
		AdjacencyListGraph.scopeSearch("v1");
		
	}
}
