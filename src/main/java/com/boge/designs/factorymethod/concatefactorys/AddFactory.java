package com.boge.designs.factorymethod.concatefactorys;

import com.boge.designs.factorymethod.abstractfactorys.OperateFactory;
import com.boge.designs.factorymethod.impls.Add;
import com.boge.designs.factorymethod.interfaces.Operate;

public class AddFactory implements OperateFactory{

	public Operate getBean() {
		// TODO Auto-generated method stub
		return new Add();
	}

}
