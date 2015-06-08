
package com.alimuya.kevvy.reflect.benchmark;

import java.lang.reflect.Method;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.KevvyMethodReflect;

public class MethodAccessBenchmark extends Benchmark {
	public MethodAccessBenchmark () throws Exception {
		int count = 100000;
		Object[] dontCompileMeAway = new Object[count];
		BenchmarkMethodBean bean=new BenchmarkMethodBean();
		
		Method javaVoidNoParameter = BenchmarkMethodBean.class.getDeclaredMethod("voidNoParameter");
		Method javaVoidParameters = BenchmarkMethodBean.class.getDeclaredMethod("voidParameters",String.class,String.class,String.class,double.class,int.class);
		Method javaReturnNoParameter = BenchmarkMethodBean.class.getDeclaredMethod("returnNoParameter");
		Method javaReturnParameters = BenchmarkMethodBean.class.getDeclaredMethod("returnParameters",String.class,String.class,String.class,double.class,int.class);
		javaVoidNoParameter.setAccessible(true);
		javaVoidParameters.setAccessible(true);
		javaReturnNoParameter.setAccessible(true);
		javaReturnParameters.setAccessible(true);
		
		
		KevvyMethodReflect kv = KevvyMethodReflect.createMethodReflect(BenchmarkMethodBean.class);
		KevvyMethod kevvyVoidNoParameter = kv.getMethod("voidNoParameter");
		KevvyMethod kevvyVoidParameters = kv.getMethod("voidParameters",String.class,String.class,String.class,double.class,int.class);
		KevvyMethod kevvyReturnNoParameter = kv.getMethod("returnNoParameter");
		KevvyMethod kevvyReturnParameters = kv.getMethod("returnParameters",String.class,String.class,String.class,double.class,int.class);
		
		
		KevvyMethod kevvyPrivateVoidNoParameter = kv.getMethod("privateVoidNoParameter");
		KevvyMethod kevvyPrivateVoidParameters = kv.getMethod("privateVoidParameters",String.class,String.class,String.class,double.class,int.class);
		KevvyMethod kevvyPrivateReturnNoParameter = kv.getMethod("privateReturnNoParameter");
		KevvyMethod kevvyPrivateReturnParameters = kv.getMethod("privateReturnParameters",String.class,String.class,String.class,double.class,int.class);
		
		for (int i = 0; i < 100; i++) {
			//VoidNoParameter 
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyVoidNoParameter.invoke(bean);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyPrivateVoidNoParameter.invoke(bean);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaVoidNoParameter.invoke(bean);
			}
			
//			//VoidParameters
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyPrivateVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = javaVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			
//			//ReturnNoParameter
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyReturnNoParameter.invoke(bean);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyPrivateReturnNoParameter.invoke(bean);
			}
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaReturnNoParameter.invoke(bean);
			}
//			
//			// ReturnParameters
////			
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyPrivateReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = javaReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
		}
		warmup = false;
		//===========================================================
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyVoidNoParameter.invoke(bean);
//			}
//			end("Kevvy-Void-NoParameter");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyPrivateVoidNoParameter.invoke(bean);
//			}
//			end("Kevvy-Private-Void-NoParameter");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = javaVoidNoParameter.invoke(bean);
//			}
//			end("Java-Void-NoParameter");
//		}
		//===========================================================		
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Kevvy-Void-Parameters");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyPrivateVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Kevvy-Private-Void-Parameters");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = javaVoidParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Java-Void-Parameters");
//		}
//		//===========================================================
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyReturnNoParameter.invoke(bean);
			}
			end("Kevvy-Return-NoParameter");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = kevvyPrivateReturnNoParameter.invoke(bean);
			}
			end("Kevvy-Private-Return-NoParameter");
		}
		for (int i = 0; i < 100; i++) {
			start();
			for (int ii = 0; ii < count; ii++){
				dontCompileMeAway[ii] = javaReturnNoParameter.invoke(bean);
			}
			end("Java-Return-NoParameter");
		}
//		//===========================================================
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Kevvy-Return-Parameters");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = kevvyPrivateReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Kevvy-Private-Return-Parameters");
//		}
//		for (int i = 0; i < 100; i++) {
//			start();
//			for (int ii = 0; ii < count; ii++){
//				dontCompileMeAway[ii] = javaReturnParameters.invoke(bean,"vv","ov","alimuya",3.14,1);
//			}
//			end("Java-Return-Parameters");
//		}
		chart("Kevvy-Method-Invoke");
	}

	public static void main (String[] args) throws Exception {
		new MethodAccessBenchmark();
	}
}
