package com.alimuya.kevvy.reflect.test.method;

import java.lang.reflect.Method;
import java.util.Arrays;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestMethodInvokeBean;

public class KevvyMethodTest extends TestCase {

	private KevvyMethodReflect kr;
	private TestMethodInvokeBean bean;

	@Before
	protected void setUp() throws Exception {
		this.kr = KevvyMethodReflect.createMethodReflect(TestMethodInvokeBean.class);
		this.bean=new TestMethodInvokeBean();
		super.setUp();
	}

	@Test
	public void test1Invoke() {
		KevvyMethod method = kr.getMethod("test1");
		try {
			method.invoke(bean);
		} catch (MethodReflectException e) {
			e.printStackTrace();
			fail();
		} catch (InvokeTargetException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test2Invoke() {
		KevvyMethod method = kr.getMethod("test2",String.class,long.class);
		try {
			method.invoke(bean, "womeng",System.currentTimeMillis());
		} catch (MethodReflectException e) {
			e.printStackTrace();
			fail();
		} catch (InvokeTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3Invoke() {
		KevvyMethod method = kr.getMethod("test3",String.class,String.class,double.class,Long.class);
		try {
			Method javaMethod = TestMethodInvokeBean.class.getMethod("test3",String.class,String.class,double.class,Long.class);
			Object javaResult=javaMethod.invoke(bean, "womeng","234",2.3,new Long(345673L));
			Object kevvyResult=method.invoke(bean, "womeng","234",2.3,new Long(345673L));
			assertEquals(javaResult, kevvyResult);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test4Invoke() {
		try {
			KevvyMethod method = kr.getMethod("test4",String [].class,  Long.class);
			Method javaMethod = TestMethodInvokeBean.class.getMethod("test4",String [].class,  Long.class);
			Object javaResult=javaMethod.invoke(bean, new String []{"womeng","234"},new Long(345673L));
			Object kevvyResult=method.invoke(bean,  new String []{"womeng","234"},new Long(345673L));
			assertEquals(javaResult, kevvyResult);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test5Invoke() {
		try {
			KevvyMethod method = kr.getMethod("test5");
			Method javaMethod = TestMethodInvokeBean.class.getMethod("test5");
			Object javaResult=javaMethod.invoke(bean);
			Object kevvyResult=method.invoke(bean);
			assertEquals(javaResult, kevvyResult);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void test6Invoke() {
		try {
			KevvyMethod method = kr.getMethod("test6",String [].class,  int.class);
			Method javaMethod = TestMethodInvokeBean.class.getMethod("test6",String [].class,  int.class);
			Object javaResult=javaMethod.invoke(bean,new String []{"womeng","234"},23);
			Object kevvyResult=method.invoke(bean,new String []{"womeng","234"},23);
			assertTrue(Arrays.equals((int[])javaResult,(int[]) kevvyResult));
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testInvokeExceptionInvoke() {
		try {
			KevvyMethod method = kr.getMethod("test7",String [].class,  int.class);
			method.invoke(bean,new String []{"womeng","234"},23);
			fail();
		} catch (MethodReflectException e) {
			fail();
		} catch (InvokeTargetException e) {
			assertTrue(true);
		}
	}
	

	@Test
	public void testKevvyExceptionInvoke() {
		try {
			KevvyMethod method = kr.getMethod("test6",String [].class,  int.class);
			method.invoke(bean,2);
			fail();
		} catch (MethodReflectException e) {
			assertTrue(true);
		} catch (InvokeTargetException e) {
			fail();
		}
	}
	
}
