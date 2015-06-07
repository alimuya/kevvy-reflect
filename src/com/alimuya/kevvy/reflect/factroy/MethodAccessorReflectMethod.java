package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;

/**
 * @author Administrator
 *
 */
public class MethodAccessorReflectMethod extends KevvyMethod{
	private sun.reflect.MethodAccessor methodAccessor;
	private static Method methodAccessorMethod;
	static {
		try {
			methodAccessorMethod = Method.class.getDeclaredMethod("acquireMethodAccessor");
			methodAccessorMethod.setAccessible(true);
		} catch (Exception e) {
			
		}
	}
	
	public static KevvyMethod getMethodAccessorReflectMethod(Method method){
		if(methodAccessorMethod!=null){
			try {
				return new MethodAccessorReflectMethod(method);
			} catch (Exception e) {
			}
		}
		return null;
	}
	
	
	private MethodAccessorReflectMethod(Method method) throws Exception{
		methodAccessor = (sun.reflect.MethodAccessor)methodAccessorMethod.invoke(method);
	}
	
	@Override
	protected Object _invoke(Object obj, Object... args)
			throws MethodReflectException, InvokeTargetException {
		try {
			return methodAccessor.invoke(obj, args);
		} catch (IllegalArgumentException e) {
			throw new MethodReflectException(e);
		} catch (InvocationTargetException e) {
			throw new InvokeTargetException(e);
		}
	}
	

}
