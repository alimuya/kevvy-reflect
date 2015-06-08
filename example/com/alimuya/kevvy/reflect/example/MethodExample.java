package com.alimuya.kevvy.reflect.example;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.test.method.TestMethodInvokeBean;


/**
 * @author ov_alimuya
 *
 */
public class MethodExample {
	
	public static void main(String[] args) throws MethodReflectException, InvokeTargetException {
		TestMethodInvokeBean bean=new TestMethodInvokeBean();
		KevvyMethodReflect kmr = KevvyMethodReflect.createMethodReflect(TestMethodInvokeBean.class);
		KevvyMethod km = kmr.getMethod("test3", String.class,String.class,double.class,Long.class);
		System.out.println(km.invoke(bean, "a","b",2.32,new Long(23L)));
	}
}
