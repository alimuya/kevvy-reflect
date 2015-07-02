package com.alimuya.kevvy.reflect.test.constructor;

import java.lang.reflect.Constructor;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;

public class KevvyConstructorReflectTest extends TestCase {

	private KevvyConstructorReflect<TestConstructorBean> constructorReflect;

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		this.constructorReflect=KevvyConstructorReflect.createConstructor(TestConstructorBean.class);
	}

	@Test
	public void testGetConstructors() {
		KevvyConstructor<TestConstructorBean>[] kcs = this.constructorReflect.getConstructors();
		Constructor<?>[] jcs = TestConstructorBean.class.getDeclaredConstructors();
		assertEquals(kcs.length, jcs.length);
		for (int i = 0; i < kcs.length; i++) {
			assertEquals(kcs[i].getOriginal(),jcs[i]);
		}
	}

	@Test
	public void testGetConstructor() {
		Constructor<?>[] jcs = TestConstructorBean.class.getDeclaredConstructors();
		for (int i = 0; i < jcs.length; i++) {
			Class<?>[] types = jcs[i].getParameterTypes();
			KevvyConstructor<TestConstructorBean> kc = this.constructorReflect.getConstructor(types);
			assertEquals(kc.getOriginal(), jcs[i]);
		}
	}

	@Test
	public void testCreateKevvyConstructor() throws ConstructorReflectException {
		KevvyConstructorReflect<TestConstructorBean> cr = KevvyConstructorReflect.createConstructor(TestConstructorBean.class);
		assertNotNull(cr);
	}
	
	@Test
	public void testBaseClassP() throws ConstructorReflectException {
		KevvyConstructorReflect<Long> cr = KevvyConstructorReflect.createConstructor(long.class);
		KevvyConstructor<Long>[] cs = cr.getConstructors();
		assertEquals(cs.length, 0);
	}
	
	@Test
	public void testAbstractClassP() throws ConstructorReflectException {
		KevvyConstructorReflect<Runnable> cr = KevvyConstructorReflect.createConstructor(Runnable.class);
		KevvyConstructor<Runnable>[] cs = cr.getConstructors();
		assertEquals(cs.length, 0);
	}
	
	@Test
	public void testNewIstanceWithoutConstructor() throws ConstructorReflectException {
		TestConstructorBean bean = KevvyConstructorReflect.newInstanceWithoutConstructor(TestConstructorBean.class);
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}
	
	
	@Test
	public void testWithoutConstructorAbstractClassP(){
		Runnable bean;
		try {
			bean = KevvyConstructorReflect.newInstanceWithoutConstructor(Runnable.class);
			fail();
		} catch (ConstructorReflectException e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testWithoutConstructorInnterClassP() throws ConstructorReflectException{
		Object bean;
		bean = KevvyConstructorReflect.newInstanceWithoutConstructor(TestConstructorBean.getInnterClass());
		assertNotNull(bean);
		assertEquals(bean.getClass(), TestConstructorBean.getInnterClass());
	}
	
	@Test
	public void testWithoutConstructorBaseClassP(){
		long bean;
		try {
			bean = KevvyConstructorReflect.newInstanceWithoutConstructor(long.class);
			fail();
		} catch (ConstructorReflectException e) {
			assertTrue(true);
		}
	}

}
