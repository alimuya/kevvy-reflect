package com.alimuya.kevvy.reflect;

import java.lang.reflect.Constructor;

import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;

/**
 * @author ov_alimuya
 *
 */
public abstract class KevvyConstructor<T> extends AbstractReflectObject<Constructor<T>>{
	
	public  T newInstance (Object ... args) throws ConstructorReflectException, InvokeTargetException{
		try {
			return _newInstance(args);
		} catch (Throwable e) {
			if(e instanceof ConstructorReflectException || e instanceof InvokeTargetException){
				throw e;
			}else{
				StackTraceElement[] sts = e.getStackTrace();
				if(sts[0].getClassName().equals(this.getClass().getName())){
					throw new ConstructorReflectException(e);
				}else{
					throw new InvokeTargetException(e);
				}
			}
		}
	}
	
	protected abstract T _newInstance(Object ... args) throws ConstructorReflectException, InvokeTargetException;
	
}
