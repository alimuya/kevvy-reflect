package com.alimuya.kevvy.reflect.assistant;

import java.util.List;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.test.bean.TestConstructorBean;

public class AsmConstructor extends KevvyConstructor<TestConstructorBean>{

//	@Override
//	protected TestConstructorBean _newInstance(Object... args){
//		return new TestConstructorBean();
//	}
	
	
//	
//	@Override
//	protected TestConstructorBean _newInstance(Object... args){
//		Integer p1=(Integer)args[0];
//		return new TestConstructorBean(p1);
//	}
	
//	@Override
//	protected TestConstructorBean _newInstance(Object... args){
//		List<String> p1=(List<String>)args[0];
//		return new TestConstructorBean(p1);
//	}
	
	@Override
	protected TestConstructorBean _newInstance(Object... args){
		String p1=(String)args[0];
		Double p2=(Double)args[1];
		return new TestConstructorBean(p1,p2);
	}
}
