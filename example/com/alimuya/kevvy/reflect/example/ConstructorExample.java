package com.alimuya.kevvy.reflect.example;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.test.constructor.TestConstructorBean;

/**
 * @author ov_alimuya
 *
 */
public class ConstructorExample {
	
	public static void main(String[] args) throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructorReflect<TestConstructorBean> kcr = KevvyConstructorReflect.createConstructor(TestConstructorBean.class);
		KevvyConstructor<TestConstructorBean> kc = kcr.getConstructor(String.class);
		TestConstructorBean bean = kc.newInstance("slslsls");
		if(bean!=null && bean instanceof TestConstructorBean){
			System.out.println("HAHA1!");
		}
		//======================================
		
		TestConstructorBean emptyBean = KevvyConstructorReflect.newIstanceWithoutConstructor(TestConstructorBean.class);
		if(emptyBean!=null && emptyBean instanceof TestConstructorBean){
			System.out.println("HAHA2!");
		}
	}
}
