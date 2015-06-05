package com.alimuya.kevvy.reflect.assistant;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestMethodInvokeBean;

public class AsmMethod extends KevvyMethod implements Opcodes{

	public Object test1(Object obj, Object... args) throws Exception {
		TestMethodInvokeBean bean;
		try {
			bean=(TestMethodInvokeBean)obj;
		} catch (Throwable e) {
			throw new MethodReflectException("kevvy invoke error!",e);
		}
		bean.test1();
		return null;
	}
//	
	public Object test2(Object obj, Object... args) throws MethodReflectException{
		TestMethodInvokeBean bean;
		String p1;
		Long p2;
		try {
			bean=(TestMethodInvokeBean)obj;
			p1=(String)args[0];
			p2=(Long)args[1];
		} catch (Throwable e) {
			throw new MethodReflectException("kevvy invoke error!",e);
		}
		bean.test2(p1, p2);
		return null;
	}
	
	public Object test3(Object obj, Object... args) throws MethodReflectException{
	TestMethodInvokeBean bean;
	String p1;
	String p2;
	Double p3;
	Long p4;
	try {
		bean=(TestMethodInvokeBean)obj;
		p1=(String)args[0];
		p2=(String)args[1];
		p3=(Double)args[2];
		p4=(Long)args[3];
	} catch (Throwable e) {
		throw new MethodReflectException("kevvy invoke error!",e);
	}
	bean.test3(p1, p2,p3,p4);
	return null;
}
	
	public Object _invoke(Object obj, Object... args) {
//		TestMethodInvokeBean bean=(TestMethodInvokeBean)obj;
//		String p2=(String) args[1];
//		 bean.sayLong(p2);
		 return null;	
	}
	
//	public Object invoke4(Object obj, Object... args) {
//		TestMethodInvokeBean bean=(TestMethodInvokeBean)obj;
//		return bean.sayStrings();
//	}
	
	
	
	
}
