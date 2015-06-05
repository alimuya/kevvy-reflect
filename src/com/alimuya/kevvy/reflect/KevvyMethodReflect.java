package com.alimuya.kevvy.reflect;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.factroy.MethodClassBuilder;

/**
 * @author ov_alimuya
 *
 */
public class KevvyMethodReflect {
	private Map<String,List<KevvyMethod>> map=new HashMap<String,List<KevvyMethod>>();
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
				String name = method.getName();
				List<KevvyMethod> sameNameMethods = map.get(name);
				if(sameNameMethods==null){
					sameNameMethods=new ArrayList<KevvyMethod>();
					map.put(name, sameNameMethods);
				}
				sameNameMethods.add(kevvyMethod);
				array[i]=kevvyMethod;
				map.put(name,sameNameMethods);
			}
		} catch (Exception e) {
			throw new MethodReflectException(e);
		}
	}
	
	public static KevvyMethodReflect createMethodReflect(Class<?> claz) throws MethodReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new IllegalArgumentException("argument claz==null || claz.isPrimitive()");
		}
		return new KevvyMethodReflect(claz);
	}

	public KevvyMethod[] getMethods(){
		return array.clone();
	}
	
	public KevvyMethod getMethod(String methodName,Class<?> ... argClasses){
		if(argClasses==null){
			argClasses=new Class<?>[0];
		}
		List<KevvyMethod> methods = map.get(methodName);
		KevvyMethod result=null;
		if(methods!=null){
			int length=methods.size();
			for (int i = 0; i < length; i++) {
				KevvyMethod method = methods.get(i);
				Class<?>[] types = method.getOriginalMethod().getParameterTypes();
				if(Arrays.equals(argClasses, types)){
					result=method;
					break;
				}
			}
		}
		return result;
	}
}
