package com.boge.designs.composite;

import java.util.List;

import com.boge.designs.composite.abstracts.Company;
import com.boge.designs.composite.branch.CompanyBranch;
import com.boge.designs.composite.leaves.FinLeaf;
import com.boge.designs.composite.leaves.HrLeaf;

public class Test {

	public static void main(String[] args) {
		
		Company root = new CompanyBranch("北京总部");
		Company leaf1 = new HrLeaf("北京总部");
		Company fin1 = new FinLeaf("北京总部");
		
		root.add(leaf1);
		root.add(fin1);
		
		Company hefei = new CompanyBranch("合肥分部");
		Company leaf2 = new HrLeaf("合肥分部");
		Company fin2 = new FinLeaf("合肥分部");
		hefei.add(leaf2);
		hefei.add(fin2);
		
		root.add(hefei);
		
		root.display(0);
	}
	
}
