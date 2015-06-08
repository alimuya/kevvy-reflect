package com.alimuya.kevvy.reflect.constructor;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyConstructor;

/**
 * @author ov_alimuya
 *
 */
public class ConstructorReflectDirector {
	private ArrayList<IReflectObjectBuilder<KevvyConstructor<?>>> builders=new ArrayList<IReflectObjectBuilder<KevvyConstructor<?>>>();
	private Class<?> beanClass;

	public ConstructorReflectDirector (Class<?> beanClass){
		this.beanClass=beanClass;
		builders.add(new DirectConstructorAsmBuilder());
		builders.add(new JavaOriginalConstructorBuilder());
	}
	
	@SuppressWarnings("unchecked")
	public<T> KevvyConstructor<T> build(Constructor<T> constructor){
		KevvyConstructor<T> result = null;
		int length = builders.size();
		for (int i = 0; i < length; i++) {
			IReflectObjectBuilder<KevvyConstructor<?>> builder = builders.get(i);
			if(builder.isSuitable(constructor)){
				try {
					result=(KevvyConstructor<T>) builder.build(beanClass, constructor);
					break;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return result;
	}
}
