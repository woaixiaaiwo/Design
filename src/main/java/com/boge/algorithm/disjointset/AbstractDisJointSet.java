package com.boge.algorithm.disjointset;

/**
 * 不相交集 
 */
public abstract class AbstractDisJointSet{

	protected int[] ele;
	
	public AbstractDisJointSet(){}
	
	public AbstractDisJointSet(int[] ele){
		this.ele = ele;
	}
	
	//基础合并方法
	//改进版本有按高度(秩)合并和按大小合并
	public void union(int root1,int root2){
		ele[root2] = root1;
	}
	
	//基础查找方法
	//改进版本有路径压缩方法
	public int find(int x){
		if(ele[x] < 0)
			return x;
		return find(ele[x]);
	}
	
	
	
	
}
