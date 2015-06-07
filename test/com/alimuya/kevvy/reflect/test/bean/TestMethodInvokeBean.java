package com.alimuya.kevvy.reflect.test.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestMethodInvokeBean {
	
	public void test1() throws Exception{
//		System.out.println("ssssssssssss");
//		throw new Exception();
	}
	
	public void test2 (String p1,long p2){
		
	}
	
	
	public String test3(String p1, String p2, double p3, Long p4){
		return "sssss";
	}
		
	public double test4(String [] p1,  Long p2){
		return 0.2;
	}
	
	public List<String> test5(){
		return new ArrayList<String>(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{this.add("ssss");}
		};
	}
	
	public int[] test6(String [] p1,  int p2){
		return new int[]{0,1,3,4,5};
	}
	
	public int[] test7(String [] p1,  int p2) throws IOException{
		throw new IOException();
	}
	
	
	@SuppressWarnings("unused")
	private void test8(){
		
	}
	
	@SuppressWarnings("unused")
	private int test9(long p1, String p2){
		return 2;
	}
	
	
	@SuppressWarnings("unused")
	private static int test10(long p1, String p2){
		return 2;
	}
}
