package com.alimuya.kevvy.reflect;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.method.MethodReflectDirector;

/**
 * @author ov_alimuya
 *
 */
public final class KevvyMethodReflect {
	private static Map<Class<?>, KevvyMethodReflect> cache=new HashMap<Class<?>, KevvyMethodReflect>();
	private Map<String,KevvyMethod> map=new HashMap<String,KevvyMethod>();
	private KevvyMethod[] array=null;
	private MethodReflectDirector builder;
	private Class<?> claz;
	
	private KevvyMethodReflect (Class<?> claz){
		this.claz=claz;
		this.builder=new MethodReflectDirector(claz);
	}
	
	public static KevvyMethodReflect createMethodReflect(Class<?> claz){
		KevvyMethodReflect reflect;
		synchronized (cache) {
			reflect = cache.get(claz);
			if(reflect==null){
				reflect=new KevvyMethodReflect(claz);
				cache.put(claz, reflect);
			}
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
		synchronized (map) {
			if(array==null){
				Method[] ms = claz.getDeclaredMethods();
				int length=ms.length;
				this.array=new KevvyMethod[length];
				for (int i = 0; i < length; i++) {
					Method method = ms[i];
					KevvyMethod kevvyMethod=this.getMethod(method);
					array[i]=kevvyMethod;
				}
			}
		}
		return array.clone();
	}
	
	public KevvyMethod getMethod(String methodName,Class<?> ... argClasses){
		if(methodName ==null || argClasses==null){
			throw new NullPointerException("methodName ==null or argClasses ==null");
		}
		String key=getMethodKey(methodName, argClasses);
		KevvyMethod kevvyMethod;
		synchronized (map) {
			kevvyMethod = map.get(key);
			if(kevvyMethod==null){
				try {
					Method method = this.claz.getDeclaredMethod(methodName, argClasses);
					kevvyMethod=this.getKevvyMethod(method);
					map.put(key, kevvyMethod);
				} catch (NoSuchMethodException e) {
					return null;
				}
			}
		}
		return kevvyMethod;
	}
	
	public KevvyMethod getMethod(Method method){
		if(method==null){
			throw new NullPointerException("method ==null");
		}
		String key=getMethodKey(method.getName(), method.getParameterTypes());
		KevvyMethod kevvyMethod;
		synchronized (map) {
			kevvyMethod = map.get(key);
			if(kevvyMethod==null){
				kevvyMethod=getKevvyMethod(method);
				map.put(key, kevvyMethod);
			}
		}
		return kevvyMethod;
	}
	
	
	private KevvyMethod getKevvyMethod (Method method){
		if(!method.isAccessible()){
			method.setAccessible(true);
		}
		KevvyMethod kevvyMethod = builder.build(method);
		kevvyMethod.setOriginal(method);
		return kevvyMethod;
	}
}
