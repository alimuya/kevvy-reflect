package com.alimuya.kevvy.reflect.test.field;

import com.alimuya.kevvy.reflect.test.bean.TestPublicFieldBean;


public class PublicKevvyFieldTest extends AbstractKevvyFieldTest {

	@Override
	protected void initBeanClass() {
		this.testClass=TestPublicFieldBean.class;
		this.bean=new TestPublicFieldBean();
		
	}


}
