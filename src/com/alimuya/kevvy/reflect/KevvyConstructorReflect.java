package com.alimuya.kevvy.reflect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.factroy.ConstructorBuilder;
import com.alimuya.kevvy.reflect.factroy.UnsafeFactory;

/**
 * @author ov_alimuya
 *
 * @param <T>
 */
public class KevvyConstructorReflect<T> {
	private static Map<Class<?>, KevvyConstructorReflect<?>> cache=new HashMap<Class<?>,KevvyConstructorReflect<?>>();
	private KevvyConstructor<T>[] array=null;
	private Map<String,KevvyConstructor<T>> map=new HashMap<String,KevvyConstructor<T>>(); 
	
	@SuppressWarnings("unchecked")
	private KevvyConstructorReflect(Class<T> claz) throws ConstructorReflectException{
		try {
			Constructor<T>[] constructors = (Constructor<T>[]) claz.getDeclaredConstructors();
			int length=constructors.length;
			array=new KevvyConstructor[length];
			ConstructorBuilder builder=new ConstructorBuilder(claz);
			for (int i = 0; i < length; i++) {
				Constructor<T> constructor = constructors[i];
				KevvyConstructor<T> kevvyConstructor = builder.build(constructor);
				kevvyConstructor.setOriginalConstructor(constructor);
				array[i]=kevvyConstructor;
				map.put(getClassesKey(constructor.getParameterTypes()), kevvyConstructor);
			}
		} catch (Exception e) {
			throw new ConstructorReflectException(e);
		}
	}
	
	
	private String getClassesKey(Class<?>[] classes){
		StringBuilder sb=new StringBuilder();
		int length=classes.length;
		for (int i = 0; i < length; i++) {
			sb.append(classes[i].getName());
			sb.append("|");
		}
		return sb.toString();
	}
	
	public KevvyConstructor<T>[] getConstructors(){
		return array.clone();
	}
	
	public KevvyConstructor<T> getConstructor(Class<?> ...args){
		if(args==null){
			throw new IllegalArgumentException("args ==null");
		}
		return map.get(getClassesKey(args));
	}
	
	@SuppressWarnings("unchecked")
	public static<T> KevvyConstructorReflect<T> createKevvyConstructor(Class<T> claz) throws ConstructorReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new IllegalArgumentException("argument claz==null || claz.isPrimitive()");
		}
		KevvyConstructorReflect<T> reflect = (KevvyConstructorReflect<T>) cache.get(claz);
		if(reflect==null){
			reflect=new KevvyConstructorReflect<T>(claz);
			cache.put(claz, reflect);
		}
		return reflect;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T newIstanceWithoutConstructor(Class<T> claz) throws ConstructorReflectException{
		try {
			return (T) UnsafeFactory.getUnsafe().allocateInstance(claz);
		} catch (Throwable e) {
			throw new ConstructorReflectException(e);
		}
	} 
	
}
