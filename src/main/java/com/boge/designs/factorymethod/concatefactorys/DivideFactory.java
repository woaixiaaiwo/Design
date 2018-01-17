package com.boge.designs.factorymethod.concatefactorys;

import com.boge.designs.factorymethod.abstractfactorys.OperateFactory;
import com.boge.designs.factorymethod.impls.Divide;
import com.boge.designs.factorymethod.interfaces.Operate;

public class DivideFactory implements OperateFactory{

	public Operate getBean() {
		// TODO Auto-generated method stub
		return new Divide();
	}

}
