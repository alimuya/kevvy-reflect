package com.alimuya.kevvy.reflect.method;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;

/**
 * @author ov_alimuya
 *
 */
public class JavaOriginalMethodBuilder implements IReflectObjectBuilder<KevvyMethod>{

	@Override
	public boolean isSuitable(Member member) {
		return true;
	}

	@Override
	public KevvyMethod build(Class<?> beanClass, Member member)
			throws Exception {
		return new JavaOriginalReflectMethod();
	}
	
	/**
	 * @author ov_alimuya
	 *
	 */
	public final static class JavaOriginalReflectMethod extends KevvyMethod{

		@Override
		protected Object _invoke(Object obj, Object... args) throws MethodReflectException, InvokeTargetException {
			try {
				return this.original.invoke(obj, args);
			} catch (IllegalAccessException e) {
				throw new MethodReflectException(e);
			} catch (IllegalArgumentException e) {
				throw new MethodReflectException(e);
			} catch (InvocationTargetException e) {
				throw new InvokeTargetException(e);
			}
		}
		

	}


}
