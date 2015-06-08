package com.alimuya.kevvy.reflect.test.field;



public class PublicKevvyFieldTest extends AbstractKevvyFieldTest {

	@Override
	protected void initBeanClass() {
		this.testClass=TestDirectFieldBean.class;
		this.bean=new TestDirectFieldBean();
		
	}


}
