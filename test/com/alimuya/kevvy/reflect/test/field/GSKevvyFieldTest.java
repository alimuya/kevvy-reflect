package com.alimuya.kevvy.reflect.test.field;

import com.alimuya.kevvy.reflect.test.bean.TestGSettersFieldBean;


public class GSKevvyFieldTest extends AbstractKevvyFieldTest {

	@Override
	protected void initBeanClass() {
		this.testClass=TestGSettersFieldBean.class;
		this.bean=new TestGSettersFieldBean();
	}


}
