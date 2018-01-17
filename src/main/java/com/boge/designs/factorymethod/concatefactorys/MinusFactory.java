package com.boge.designs.factorymethod.concatefactorys;

import com.boge.designs.factorymethod.abstractfactorys.OperateFactory;
import com.boge.designs.factorymethod.impls.Minus;
import com.boge.designs.factorymethod.interfaces.Operate;

public class MinusFactory implements OperateFactory{

	public Operate getBean() {
		// TODO Auto-generated method stub
		return new Minus();
	}

}
