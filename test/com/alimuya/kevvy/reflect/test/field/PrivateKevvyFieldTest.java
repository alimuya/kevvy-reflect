package com.alimuya.kevvy.reflect.test.field;

import com.alimuya.kevvy.reflect.test.bean.TestPrivateFieldBean;


public class PrivateKevvyFieldTest extends AbstractKevvyFieldTest {

	@Override
	protected void initBeanClass() {
		this.testClass=TestPrivateFieldBean.class;
		this.bean=new TestPrivateFieldBean();
		
	}


}
