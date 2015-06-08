package com.alimuya.kevvy.reflect;

import java.lang.reflect.Method;

import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;

/**
 * @author ov_alimuya
 *
 */
public abstract class KevvyMethod extends AbstractReflectObject<Method>{
	
	public  Object invoke (Object obj,Object ... args) throws MethodReflectException, InvokeTargetException{
		try {
			return _invoke(obj,args);
		} catch (Throwable e) {
			if(e instanceof MethodReflectException || e instanceof InvokeTargetException){
				throw e;
			}else{
				StackTraceElement[] sts = e.getStackTrace();
				if(sts[0].getClassName().equals(this.getClass().getName())){
					throw new MethodReflectException(e);
				}else{
					throw new InvokeTargetException(e);
				}
			}
		}
	}
	
	
	
	protected abstract Object _invoke(Object obj,Object ... args) throws MethodReflectException, InvokeTargetException;
	
}
