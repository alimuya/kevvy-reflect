
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Method;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;
import com.alimuya.kevvy.reflect.test.bean.TestFieldBean;

public class MethodAccessBenchmark extends Benchmark {
	public MethodAccessBenchmark () throws Exception {
		int count = 100000;
		Object[] dontCompileMeAway = new Object[count];
		TestFieldBean bean=new TestFieldBean();
//		Method method = TestBean.class.getDeclaredMethod("content",String.class,long.class,int.class);
		Method method = TestFieldBean.class.getDeclaredMethod("say");
//		Method method = TestBean.class.getDeclaredMethod("sayLong",String.class);
		method.setAccessible(true);
		
		KevvyMethodReflect kv = KevvyMethodReflect.createMethodReflect(TestFieldBean.class);
//		KevvyMethod kevvyMethod = kv.getMethod("content",String.class,long.class,int.class);
		KevvyMethod kevvyMethod = kv.getMethod("say");
//		KevvyMethod kevvyMethod = kv.getMethod("sayLong",String.class);
		for (int i = 0; i < 100; i++) {
			for (int ii = 0; ii < count; ii++)
//				dontCompileMeAway[ii] = kevvyMethod.invoke(bean,"ssss",1234L,34);
				dontCompileMeAway[ii] = kevvyMethod.invoke(bean);
//				dontCompileMeAway[ii] = kevvyMethod.invoke(bean,"sss");
			for (int ii = 0; ii < count; ii++)
//				dontCompileMeAway[ii] = method.invoke(bean,"ssss",1234L,34);
				dontCompileMeAway[ii] = method.invoke(bean);
//				dontCompileMeAway[ii] = method.invoke(bean,"sss");
		}
		warmup = false;

		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++)
//				dontCompileMeAway[ii] = kevvyMethod.invoke(bean,"ssss",1234L,34);
				dontCompileMeAway[ii] = kevvyMethod.invoke(bean);
//				dontCompileMeAway[ii] = kevvyMethod.invoke(bean,"sss");
			end("Kevvy-Reflection");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++)
//				dontCompileMeAway[ii] = method.invoke(bean,"ssss",1234L,34);
				dontCompileMeAway[ii] = method.invoke(bean);
//				dontCompileMeAway[ii] = method.invoke(bean,"sss");
			end("Java-Reflection");
		}

		chart("Method Call");
	}

	public static void main (String[] args) throws Exception {
		new MethodAccessBenchmark();
	}
}
