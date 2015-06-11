package com.alimuya.kevvy.reflect.test.constructor;

import java.util.List;

public class TestConstructorBean {
	private class Inter{
		
	}
	
	private Inter in=new Inter();
	public static Class<?> getInnterClass(){
		TestConstructorBean bean=new TestConstructorBean();
		return bean.in.getClass();
	}
	
	public TestConstructorBean (){}
	
	TestConstructorBean (String  p1){}
	TestConstructorBean (int p1){}
	
	protected TestConstructorBean (String p1, double p2){}
//	
	protected TestConstructorBean (long [] p1, String [] p2){}
//	
	TestConstructorBean (List<String> list){}
	
	public TestConstructorBean (long p1)throws Exception{
		throw new Exception();
	}
}
