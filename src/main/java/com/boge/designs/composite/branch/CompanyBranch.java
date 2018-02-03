package com.boge.designs.composite.branch;

import java.util.ArrayList;
import java.util.List;

import com.boge.designs.composite.abstracts.Company;

/**
 * 组合模式，公司分支类 
 */
public class CompanyBranch extends Company{

	private List<Company> list = new ArrayList<Company>();
	
	public CompanyBranch(){}
	
	public CompanyBranch(String name){
		super(name);
	}
	
	@Override
	public void add(Company company) {
		// TODO Auto-generated method stub
		list.add(company);
	}

	@Override
	public void romove(Company company) {
		// TODO Auto-generated method stub
		list.remove(company);
	}

	@Override
	public void display(int depth) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("");
		for (int i = 0; i < depth; i++) {
			sb.append("-");
		}
		System.out.println(new String(sb) + this.getName());
		for(int i=0;i<list.size();i++){
			list.get(i).display(depth+1);
		}
	}

}
