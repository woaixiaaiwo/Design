package com.boge.algorithm.disjointset;

import java.util.Arrays;

/**
 * 按数量合并的不相交集 
 */
public class DisJointSetByNumber extends AbstractDisJointSet{
	
	public DisJointSetByNumber(){}
	
	public DisJointSetByNumber(int[] ele) {
		super(ele);
		// TODO Auto-generated constructor stub
	}

	//按照数量合并方法
	//数量为存储值的相反数
	@Override
	public void union(int root1,int root2){
		if(ele[root1] > 0)root1 = find(root1);
		if(ele[root2] > 0)root2 = find(root2);
		//存储的值一样，或者root1的数量小于root2的数量，把root1链接到root2上，root2数量等于当前数量加上root1数量
		if(ele[root1] >= ele[root2]){
			//root2的数据加上root1的数量
			ele[root2]+=ele[root1];
			ele[root1] = root2;
		}else{
			ele[root1]+=ele[root2];
			ele[root2] = root1;
		}
	}
	
	//改进查找方法，路径压缩(从x出发，直到根处的每一个节点，都指向根节点)
	/*public int find(int x){
		if(ele[x] < 0)
			return x;
		return ele[x] = find(ele[x]);
	}*/
	
	
	public static void main(String[] args) {
		
		int[] arr = {-1,-1,-1,-1,-1,-1,-1,-1};
		DisJointSetByNumber disJointSetByNumber = new DisJointSetByNumber(arr);
		System.out.println(Arrays.toString(arr));
		for(int i=arr.length-1;i>=1;i--){
			disJointSetByNumber.union(i, i-1);
		}
		System.out.println(Arrays.toString(arr));
	}
	
}
