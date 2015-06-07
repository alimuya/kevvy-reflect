
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Constructor;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.KevvyConstructorReflect;


public class ConstructorAccessBenchmark extends Benchmark {
	public ConstructorAccessBenchmark () throws Exception {
		int count = 1000000;
		Object[] dontCompileMeAway = new Object[count];

		Class<BenchmarkConstructorBean> type = BenchmarkConstructorBean.class;
		KevvyConstructorReflect<BenchmarkConstructorBean> kevvyReflect = KevvyConstructorReflect.createConstructor(type);
		KevvyConstructor<BenchmarkConstructorBean> kevvyNoParameter = kevvyReflect.getConstructor();
		KevvyConstructor<BenchmarkConstructorBean> kevvyParameters = kevvyReflect.getConstructor(String.class, String.class,String.class,double.class,int.class);
		Constructor<BenchmarkConstructorBean> javaNoParameter = type.getConstructor();
		Constructor<BenchmarkConstructorBean> javaParameters = type.getConstructor(String.class, String.class,String.class,double.class,int.class);
		
		for (int i = 0; i < 100; i++){
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyNoParameter.newInstance();
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaNoParameter.newInstance();
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyParameters.newInstance("vv","ov","alimuya",3.14,1);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaParameters.newInstance("vv","ov","alimuya",3.14,1);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = KevvyConstructorReflect.newIstanceWithoutConstructor(BenchmarkConstructorBean.class);
			}
		}
		warmup = false;

		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyNoParameter.newInstance();
			}
			end("Kevvy-No-Parameter");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaNoParameter.newInstance();
			}
			end("Java-No-Parameter");
		}
		
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyParameters.newInstance("vv","ov","alimuya",3.14,1);
			}
			end("Kevvy-Parameters");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaParameters.newInstance("vv","ov","alimuya",3.14,1);
			}
			end("Java-Parameters");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = KevvyConstructorReflect.newIstanceWithoutConstructor(BenchmarkConstructorBean.class);
			}
			end("Kevvy-Without-Constructor");
		}
		
		chart("Kevvy Constructor");
	}

	public static void main (String[] args) throws Exception {
		new ConstructorAccessBenchmark();
	}
}
