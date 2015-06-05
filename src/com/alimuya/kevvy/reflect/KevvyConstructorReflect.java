package com.alimuya.kevvy.reflect;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.factroy.ConstructorBuilder;
import com.alimuya.kevvy.reflect.factroy.UnsafeFactory;

/**
 * @author ov_alimuya
 *
 * @param <T>
 */
public class KevvyConstructorReflect<T> {
	private KevvyConstructor<T>[] array=null;
	
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
			}
		} catch (Exception e) {
			throw new ConstructorReflectException(e);
		}
	}
	
	public KevvyConstructor<T>[] getConstructors(){
		return array.clone();
	}
	
	public KevvyConstructor<T> getConstructor(Class<?> ...args){
		int length=array.length;
		if(args==null){
			args=new Class<?>[0];
		}
		for (int i = 0; i < length; i++) {
			KevvyConstructor<T> kevvyConstructor= array[i];
			Class<?>[] types = kevvyConstructor.getOriginalConstructor().getParameterTypes();
			if(Arrays.equals(types, args)){
				return kevvyConstructor;
			}
		}
		return null;
	}
	
	public static<T> KevvyConstructorReflect<T> createKevvyConstructor(Class<T> claz) throws ConstructorReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new IllegalArgumentException("argument claz==null || claz.isPrimitive()");
		}
		return new KevvyConstructorReflect<T>(claz);
	}
	
//	public
	
	@SuppressWarnings("unchecked")
	public static <T>T newIstanceWithoutConstructor(Class<T> claz) throws InstantiationException{
		return (T) UnsafeFactory.getUnsafe().allocateInstance(claz);
	} 
}
