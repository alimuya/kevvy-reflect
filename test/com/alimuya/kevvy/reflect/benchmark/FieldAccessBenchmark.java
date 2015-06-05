
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Field;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;

public class FieldAccessBenchmark extends Benchmark {
	
	public FieldAccessBenchmark () throws Exception {
		int count = 1000000;
		Object[] dontCompileMeAway = new Object[count];
		BenchmarkFieldBean testFieldBean=new BenchmarkFieldBean();
		KevvyField kevvyPublicField = KevvyFieldReflect.createFieldReflect(BenchmarkFieldBean.class).getField("publicField");
		KevvyField kevvyMixedField = KevvyFieldReflect.createFieldReflect(BenchmarkFieldBean.class).getField("mixedField");
		KevvyField kevvyPrivateField = KevvyFieldReflect.createFieldReflect(BenchmarkFieldBean.class).getField("privateField");
		Field javaField = BenchmarkFieldBean.class.getDeclaredField("publicField");
		javaField.setAccessible(true);
		String testString ="ov-alimuya";
		for (int i = 0; i < 100; i++) {
			for (int ii = 0; ii < count; ii++) {
				testFieldBean.setGsField(testString);
				dontCompileMeAway[ii] = testFieldBean.getGsField();
			}
			for (int ii = 0; ii < count; ii++) {
				kevvyPublicField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyPublicField.get(testFieldBean);
			}
			for (int ii = 0; ii < count; ii++) {
				kevvyMixedField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyMixedField.get(testFieldBean);
			}
			for (int ii = 0; ii < count; ii++) {
				kevvyPrivateField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyPrivateField.get(testFieldBean);
			}
			for (int ii = 0; ii < count; ii++) {
				javaField.set(testFieldBean, testString);
				dontCompileMeAway[ii] = javaField.get(testFieldBean);
			}
		}
		warmup = false;
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				testFieldBean.setGsField(testString);
				dontCompileMeAway[ii] = testFieldBean.getGsField();
			}
			end("Java-Getter/Setter");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				kevvyPublicField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyPublicField.get(testFieldBean);
			}
			end("Kevvy-Public-Field");
		}
		
		
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				kevvyMixedField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyMixedField.get(testFieldBean);
			}
			end("Kevvy-Mixed-Field");
		}
		
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				kevvyPrivateField.setObject(testFieldBean, testString);
				dontCompileMeAway[ii] = kevvyPrivateField.get(testFieldBean);
			}
			end("Kevvy-Private-Field");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++) {
				javaField.set(testFieldBean, testString);
				dontCompileMeAway[ii] = javaField.get(testFieldBean);
			}
			end("Java-Reflect");
		}

		chart("Kevvy Field Set/Get Benchmark");
	}

	public static void main (String[] args) throws Exception {
		new FieldAccessBenchmark();
	}
}
