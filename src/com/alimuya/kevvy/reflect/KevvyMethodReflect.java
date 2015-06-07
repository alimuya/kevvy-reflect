package com.alimuya.kevvy.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.factroy.MethodClassBuilder;

/**
 * @author ov_alimuya
 *
 */
public class KevvyMethodReflect {
	private static Map<Class<?>, KevvyMethodReflect> cache=new HashMap<Class<?>, KevvyMethodReflect>();
	private Map<String,KevvyMethod> map=new HashMap<String,KevvyMethod>();
	private KevvyMethod[] array=null;
	
	private KevvyMethodReflect (Class<?> claz) throws MethodReflectException{
		try {
			MethodClassBuilder builder=new MethodClassBuilder(claz);
			Method[] ms = claz.getDeclaredMethods();
			int length=ms.length;
			this.array=new KevvyMethod[length];
			for (int i = 0; i < length; i++) {
				Method method = ms[i];
				if(!method.isAccessible()){
					method.setAccessible(true);
				}
				KevvyMethod kevvyMethod = builder.build(method);
				kevvyMethod.setOriginalMethod(method);
				String methodName = method.getName();
				array[i]=kevvyMethod;
				map.put(getMethodKey(methodName, method.getParameterTypes()),kevvyMethod);
			}
		} catch (Exception e) {
			throw new MethodReflectException(e);
		}
	}
	
	public static KevvyMethodReflect createMethodReflect(Class<?> claz) throws MethodReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new IllegalArgumentException("argument claz==null || claz.isPrimitive()");
		}
		KevvyMethodReflect reflect = cache.get(claz);
		if(reflect==null){
			reflect=new KevvyMethodReflect(claz);
			cache.put(claz, reflect);
		}
		return reflect;
	}
	
	private String getMethodKey(String methodName,Class<?>[] classes){
		StringBuilder sb=new StringBuilder();
		sb.append(methodName);
		sb.append('#');
		int length=classes.length;
		for (int i = 0; i < length; i++) {
			sb.append(classes[i].getName());
			sb.append("|");
		}
		return sb.toString();
	}
	
	public KevvyMethod[] getMethods(){
		return array.clone();
	}
	
	public KevvyMethod getMethod(String methodName,Class<?> ... argClasses){
		if(methodName ==null || argClasses==null){
			throw new IllegalArgumentException("methodName ==null or argClasses ==null");
		}
		String key=getMethodKey(methodName, argClasses);
		return map.get(key);
	}
}
