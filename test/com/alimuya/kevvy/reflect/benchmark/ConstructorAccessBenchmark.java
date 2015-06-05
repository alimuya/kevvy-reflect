
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Constructor;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;
import com.alimuya.kevvy.reflect.test.bean.TestConstructorBean;


public class ConstructorAccessBenchmark extends Benchmark {
	public ConstructorAccessBenchmark () throws Exception {
		int count = 1000000;
		Object[] dontCompileMeAway = new Object[count];

		Class type = TestConstructorBean.class;
		KevvyConstructorReflect<TestConstructorBean> constructorReflect = KevvyConstructorReflect.createKevvyConstructor(TestConstructorBean.class);
		KevvyConstructor<TestConstructorBean> access = constructorReflect.getConstructor(String.class);
		Constructor javac = type.getConstructor(String.class);

		for (int i = 0; i < 100; i++)
			for (int ii = 0; ii < count; ii++)
				dontCompileMeAway[ii] = access.newInstance("sss");
//				dontCompileMeAway[ii] =KevvyConstructorReflect.newIstanceWithoutConstructor(type);
		for (int i = 0; i < 100; i++)
			for (int ii = 0; ii < count; ii++)
				dontCompileMeAway[ii] = javac.newInstance("sss");
		warmup = false;

		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++)
				dontCompileMeAway[ii] = access.newInstance("sss");
//				dontCompileMeAway[ii] =KevvyConstructorReflect.newIstanceWithoutConstructor(type);
			end("Kevvy-Constructor");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++)
				dontCompileMeAway[ii] = javac.newInstance("sss");
			end("java-Reflection");
		}

		chart("Constructor");
	}

	public static void main (String[] args) throws Exception {
		new ConstructorAccessBenchmark();
	}
}
