package com.boge.designs.factorymethod.concatefactorys;

import com.boge.designs.factorymethod.abstractfactorys.OperateFactory;
import com.boge.designs.factorymethod.impls.Mutiply;
import com.boge.designs.factorymethod.interfaces.Operate;

public class MutiplyFactory implements OperateFactory{

	public Operate getBean() {
		// TODO Auto-generated method stub
		return new Mutiply();
	}

}
