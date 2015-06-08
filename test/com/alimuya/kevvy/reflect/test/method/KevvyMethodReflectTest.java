package com.alimuya.kevvy.reflect.test.method;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;
import com.alimuya.kevvy.reflect.method.JavaOriginalMethodBuilder.JavaOriginalReflectMethod;
import com.alimuya.kevvy.reflect.method.MethodAccessorReflectMethod;
import com.alimuya.kevvy.reflect.test.bean.TestMethodInvokeBean;

public class KevvyMethodReflectTest extends TestCase{

	private KevvyMethodReflect kr;
	private Method[] javaMethods;

	@Before
	public void setUp() throws Exception {
		this.kr = KevvyMethodReflect.createMethodReflect(TestMethodInvokeBean.class);
		this.javaMethods=TestMethodInvokeBean.class.getDeclaredMethods();
	}

	@Test
	public void testCreateMethodReflect() throws Exception {
		KevvyMethodReflect kr = KevvyMethodReflect.createMethodReflect(TestMethodInvokeBean.class);
		assertNotNull(kr);
	}

	@Test
	public void testGetMethods() {
		KevvyMethod[] methods = kr.getMethods();
		assertEquals(methods.length,javaMethods.length);
		for (int i = 0; i < methods.length; i++) {
			assertEquals(methods[i].getOriginal(),javaMethods[i]);
		}
		return ;
	}

	@Test
	public void testGetMethod() {
		for (int i = 0; i < javaMethods.length; i++) {
			Method javaMethod = javaMethods[i];
			KevvyMethod method = kr.getMethod(javaMethod.getName(), javaMethod.getParameterTypes());
			assertEquals(method.getOriginal(), javaMethod);
			boolean  isPublic=(javaMethod.getModifiers()& Modifier.PUBLIC)==Modifier.PUBLIC;
			boolean notStatic= (javaMethod.getModifiers()& Modifier.STATIC)!=Modifier.STATIC;
			if(!(isPublic && notStatic)){
				boolean tmp=(method instanceof JavaOriginalReflectMethod || method instanceof MethodAccessorReflectMethod);
				assertTrue(tmp);
			}
		}
	}

}
