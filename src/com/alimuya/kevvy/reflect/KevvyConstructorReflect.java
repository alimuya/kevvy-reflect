package com.alimuya.kevvy.reflect;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.constructor.ConstructorReflectDirector;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.utils.UnsafeFactory;

/**
 * @author ov_alimuya
 *
 * @param <T>
 */
public final class KevvyConstructorReflect<T> {
	private static Map<Class<?>, KevvyConstructorReflect<?>> cache=new HashMap<Class<?>,KevvyConstructorReflect<?>>();
	private KevvyConstructor<T>[] array=null;
	private Map<String,KevvyConstructor<T>> map=new HashMap<String,KevvyConstructor<T>>();
	private ConstructorReflectDirector builder;
	private Class<T> claz; 
	
	private KevvyConstructorReflect(Class<T> claz){
		this.claz=claz;
		this.builder=new ConstructorReflectDirector(claz);
	}
	
	
	private KevvyConstructor<T> getKevvyConstructor(Constructor<T> constructor){
		if(!constructor.isAccessible()){
			constructor.setAccessible(true);
		}
		KevvyConstructor<T> kevvyConstructor = builder.build(constructor);
		kevvyConstructor.setOriginal(constructor);
		return kevvyConstructor;
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
	
	@SuppressWarnings("unchecked")
	public KevvyConstructor<T>[] getConstructors(){
		synchronized (map) {
			if(array==null){
				Constructor<T>[] constructors = (Constructor<T>[]) claz.getDeclaredConstructors();
				int length=constructors.length;
				array=new KevvyConstructor[length];
				for (int i = 0; i < length; i++) {
					Constructor<T> constructor = constructors[i];
					KevvyConstructor<T> kevvyConstructor = this.getConstructor(constructor);
					array[i]=kevvyConstructor;
				}
			}
		}
		return array.clone();
	}
	
	public KevvyConstructor<T> getConstructor(Constructor<T> constructor){
		if(constructor==null){
			throw new NullPointerException("constructor ==null");
		}
		String key=getClassesKey(constructor.getParameterTypes());
		KevvyConstructor<T> kevvyConstructor;
		synchronized (map) {
			kevvyConstructor = map.get(key);
			if(kevvyConstructor==null){
				kevvyConstructor=getKevvyConstructor(constructor);
				map.put(key, kevvyConstructor);
			}
		}
		return kevvyConstructor;
	}
	
	public KevvyConstructor<T> getConstructor(Class<?> ...args){
		if(args==null){
			throw new IllegalArgumentException("args ==null");
		}
		String key=getClassesKey(args);
		KevvyConstructor<T> kevvyConstructor;
		synchronized (map) {
			kevvyConstructor = map.get(key);
			if(kevvyConstructor==null){
				try {
					Constructor<T> constructor = this.claz.getDeclaredConstructor(args);
					kevvyConstructor=getKevvyConstructor(constructor);
					map.put(key, kevvyConstructor);
				} catch (NoSuchMethodException e) {
					return null;
				}
			}
		}
		return kevvyConstructor;
	}
	
	
	@SuppressWarnings("unchecked")
	public static<T> KevvyConstructorReflect<T> createConstructor(Class<T> claz){
		if(claz==null){
			throw new NullPointerException("claz ==null");
		}
		KevvyConstructorReflect<T> reflect;
		synchronized (cache) {
			reflect = (KevvyConstructorReflect<T>) cache.get(claz);
			if(reflect==null){
				reflect=new KevvyConstructorReflect<T>(claz);
				cache.put(claz, reflect);
			}
		}
		return reflect;
	}
	
	@SuppressWarnings("unchecked")
	public static <T>T newInstanceWithoutConstructor(Class<T> claz) throws ConstructorReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new ConstructorReflectException("argument claz==null or claz.isPrimitive()");
		}
		try {
			return (T) UnsafeFactory.getUnsafe().allocateInstance(claz);
		} catch (Throwable e) {
			throw new ConstructorReflectException(e);
		}
	} 
	
}
