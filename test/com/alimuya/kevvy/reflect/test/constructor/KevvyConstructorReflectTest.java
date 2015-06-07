package com.alimuya.kevvy.reflect.test.constructor;

import java.lang.reflect.Constructor;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestConstructorBean;

import junit.framework.TestCase;

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
			assertEquals(kcs[i].getOriginalConstructor(),jcs[i]);
		}
	}

	@Test
	public void testGetConstructor() {
		Constructor<?>[] jcs = TestConstructorBean.class.getDeclaredConstructors();
		for (int i = 0; i < jcs.length; i++) {
			Class<?>[] types = jcs[i].getParameterTypes();
			KevvyConstructor<TestConstructorBean> kc = this.constructorReflect.getConstructor(types);
			assertEquals(kc.getOriginalConstructor(), jcs[i]);
		}
	}

	@Test
	public void testCreateKevvyConstructor() throws ConstructorReflectException {
		KevvyConstructorReflect<TestConstructorBean> cr = KevvyConstructorReflect.createConstructor(TestConstructorBean.class);
		assertNotNull(cr);
	}

	@Test
	public void testNewIstanceWithoutConstructor() throws ConstructorReflectException {
		TestConstructorBean bean = KevvyConstructorReflect.newIstanceWithoutConstructor(TestConstructorBean.class);
		assertNotNull(bean);
		assertTrue(bean instanceof TestConstructorBean);
	}

}
