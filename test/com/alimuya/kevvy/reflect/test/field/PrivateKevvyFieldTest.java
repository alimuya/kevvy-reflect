package com.alimuya.kevvy.reflect.test.field;



public class PrivateKevvyFieldTest extends AbstractKevvyFieldTest {

	@Override
	protected void initBeanClass() {
		this.testClass=TestPrivateFieldBean.class;
		this.bean=new TestPrivateFieldBean();
		
	}


}
