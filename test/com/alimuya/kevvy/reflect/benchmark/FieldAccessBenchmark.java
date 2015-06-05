
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Field;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;

public class FieldAccessBenchmark extends Benchmark {
	public FieldAccessBenchmark () throws Exception {
		int count = 1000000;
		Object[] dontCompileMeAway = new Object[count];
		BenchmarkFieldBean testFieldBean=new BenchmarkFieldBean();
		KevvyField kevvyField = KevvyFieldReflect.createFieldReflect(BenchmarkFieldBean.class).getField("a");
		Field field = BenchmarkFieldBean.class.getDeclaredField("a");
		field.setAccessible(true);

		for (int i = 0; i < 100; i++) {
			for (int ii = 0; ii < count; ii++) {
				kevvyField.setObject(testFieldBean, "first");
				dontCompileMeAway[ii] = kevvyField.get(testFieldBean);
			}
			for (int ii = 0; ii < count; ii++) {
//				field.set(testBean, "first");
//				dontCompileMeAway[ii] = field.get(testBean);
				testFieldBean.setA("first");
				dontCompileMeAway[ii] = testFieldBean.getA();
			}
		}
		warmup = false;

		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				kevvyField.setObject(testFieldBean, "first");
				dontCompileMeAway[ii] = kevvyField.get(testFieldBean);
//				testBean.setA("first");
//				dontCompileMeAway[ii] = testBean.getA();
			}
			end("Kevvy-Reflection");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
//				field.set(testBean, "first");
//				dontCompileMeAway[ii] = field.get(testBean);
				testFieldBean.setA("first");
				dontCompileMeAway[ii] = testFieldBean.getA();
			}
			end("Java-Reflection");
		}

		chart("Field Set/Get");
	}

	public static void main (String[] args) throws Exception {
		new FieldAccessBenchmark();
	}
}
