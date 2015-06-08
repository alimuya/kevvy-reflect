package com.alimuya.kevvy.reflect.constructor;

import java.lang.reflect.InvocationTargetException;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;

/**
 * @author ov_alimuya
 *
 * @param <T>
 */
public class JavaOriginalReflectConstructor<T> extends KevvyConstructor<T>{
	
	@Override
	protected T _newInstance(Object... args) throws ConstructorReflectException, InvokeTargetException {
		try {
			return this.original.newInstance(args);
		} catch (InstantiationException e) {
			throw new ConstructorReflectException(e);
		} catch (IllegalAccessException e) {
			throw new ConstructorReflectException(e);
		} catch (IllegalArgumentException e) {
			throw new ConstructorReflectException(e);
		} catch (InvocationTargetException e) {
			throw new InvokeTargetException(e);
		}
	}

}
