package com.boge.designs.composite.leaves;

import com.boge.designs.composite.abstracts.Company;

/**
 * 组合模式，叶子结点，财务办事处 
 */
public class FinLeaf extends Company{

	public FinLeaf() {
		// TODO Auto-generated constructor stub
	}
	
	public FinLeaf(String name){
		super(name+"财务部");
	}
	
	@Override
	public void add(Company company) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void romove(Company company) {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void display(int depth) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < depth; i++) {
			sb.append("-");
		}
		System.out.println(new String(sb) + this.getName());
	}

	
}
