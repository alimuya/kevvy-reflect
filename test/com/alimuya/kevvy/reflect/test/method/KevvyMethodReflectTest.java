package com.alimuya.kevvy.reflect.test.method;

import java.lang.reflect.Method;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;

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
	public void testBaseClassP() throws Exception {
		KevvyMethodReflect kr = KevvyMethodReflect.createMethodReflect(long.class);
		assertEquals(kr.getMethods().length, 0);
	}
	
	
	@Test
	public void testInterClassP() throws Exception {
		KevvyMethodReflect kr = KevvyMethodReflect.createMethodReflect(TestMethodInvokeBean.getInnterClass());
		assertEquals(kr.getMethods().length, 1);
		KevvyMethod method = kr.getMethod("test");
		assertEquals(method.invoke(TestMethodInvokeBean.getInnter()),5);
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
	public void testGetMethodPolicy() {
		
	}

}
