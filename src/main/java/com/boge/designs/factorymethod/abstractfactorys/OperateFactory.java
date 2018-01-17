package com.boge.designs.factorymethod.abstractfactorys;

import com.boge.designs.factorymethod.interfaces.Operate;

public interface OperateFactory {
	
	Operate getBean();

}
