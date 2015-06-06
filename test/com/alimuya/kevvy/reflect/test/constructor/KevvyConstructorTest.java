package com.alimuya.kevvy.reflect.test.constructor;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestConstructorBean;

public class KevvyConstructorTest extends TestCase {

	private KevvyConstructorReflect<TestConstructorBean> constructorReflect;

	@Before
	protected void setUp() throws Exception {
		this.constructorReflect=KevvyConstructorReflect.createKevvyConstructor(TestConstructorBean.class);
		super.setUp();
	}

	@Test
	public void testNewInstance1() throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor();
		TestConstructorBean bean = constructor.newInstance();
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	@Test
	public void testNewInstance2() throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(int.class);
		TestConstructorBean bean = constructor.newInstance(23);
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	@Test
	public void testNewInstance3() throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(List.class);
		TestConstructorBean bean = constructor.newInstance(new ArrayList<String>());
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	@Test
	public void testNewInstance4() throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(String.class,double.class);
		TestConstructorBean bean = constructor.newInstance("abc",2.3);
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	@Test
	public void testNewInstance5() throws ConstructorReflectException, InvokeTargetException {
		KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(long[].class,String[].class);
		TestConstructorBean bean = constructor.newInstance(new long[]{12l,34l},new String[]{"sss","rr"});
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	@Test
	public void testInvokeExceptionInvoke() {
		try {
			KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(long.class);
			TestConstructorBean bean = constructor.newInstance(234L);
			fail();
		} catch (ConstructorReflectException e) {
			fail();
		} catch (InvokeTargetException e) {
			assertTrue(true);
		}
	}
	

	@Test
	public void testKevvyExceptionInvoke() {
		try {
			KevvyConstructor<TestConstructorBean> constructor = constructorReflect.getConstructor(long[].class,String[].class);
			TestConstructorBean bean = constructor.newInstance(1);
			fail();
		} catch (ConstructorReflectException e) {
			assertTrue(true);
		} catch (InvokeTargetException e) {
			fail();
		}
	}
}
