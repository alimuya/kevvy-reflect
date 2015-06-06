package com.alimuya.kevvy.reflect.test.bean;

import java.util.List;

public class TestConstructorBean {
	public TestConstructorBean (){}
	
	public TestConstructorBean (String  p1){}
	public TestConstructorBean (int p1){}
	
	public TestConstructorBean (String p1, double p2){}
//	
	public TestConstructorBean (long [] p1, String [] p2){}
//	
	public TestConstructorBean (List<String> list){}
	
	public TestConstructorBean (long p1)throws Exception{
		throw new Exception();
	}
}
