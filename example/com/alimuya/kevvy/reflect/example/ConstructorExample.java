package com.alimuya.kevvy.reflect.example;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.test.bean.TestConstructorBean;

public class ConstructorExample {
	
	public static void main(String[] args) throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructorReflect<TestConstructorBean> kcr = KevvyConstructorReflect.createKevvyConstructor(TestConstructorBean.class);
		KevvyConstructor<TestConstructorBean> kc = kcr.getConstructor(String.class);
		TestConstructorBean bean = kc.newInstance("slslsls");
		if(bean!=null && bean instanceof TestConstructorBean){
			System.out.println("HAHA!");
		}
	}
}
