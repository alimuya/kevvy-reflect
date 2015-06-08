package com.alimuya.kevvy.reflect.method;

import java.lang.reflect.Method;
import java.util.ArrayList;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyMethod;

/**
 * @author ov_alimuya
 *
 */
public class MethodReflectDirector {
	private ArrayList<IReflectObjectBuilder<KevvyMethod>> builders=new ArrayList<IReflectObjectBuilder<KevvyMethod>>();
	private Class<?> beanClass;

	public MethodReflectDirector (Class<?> beanClass){
		this.beanClass=beanClass;
		builders.add(new DirectMethodAsmBuilder());
		builders.add(new JavaOriginalMethodBuilder());
	}
	
	public KevvyMethod build(Method method){
		KevvyMethod result = null;
		int length = builders.size();
		for (int i = 0; i < length; i++) {
			IReflectObjectBuilder<KevvyMethod> builder = builders.get(i);
			if(builder.isSuitable(method)){
				try {
					result=builder.build(beanClass, method);
					break;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return result;
	}
	
}
