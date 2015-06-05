package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.InvocationTargetException;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;

/**
 * @author ov_alimuya
 *
 */
public class JavaOriginalReflectMethod extends KevvyMethod{

	@Override
	protected Object _invoke(Object obj, Object... args) throws MethodReflectException, InvokeTargetException {
		try {
			return this.originalMethod.invoke(obj, args);
		} catch (IllegalAccessException e) {
			throw new MethodReflectException(e);
		} catch (IllegalArgumentException e) {
			throw new MethodReflectException(e);
		} catch (InvocationTargetException e) {
			throw new InvokeTargetException(e);
		}
	}
	

}
