package com.alimuya.kevvy.reflect.test.field;

import java.util.HashMap;
import java.util.Map;

public class TestBeanAnalyzerBean {
	private long standard=System.currentTimeMillis();
	private String a="abs";
	private int [] b= new int []{0};
	
	public Map <String, String[]>getResult(){
		Map <String, String[]> result=new HashMap <String, String[]>();
		result.put("standard", new String []{"getStandard","setStandard"});
		result.put("a", new String []{"getA_real","setA_real"});
		result.put("b", new String []{null,"setB_real"});
		return result;
	}
	
	public synchronized final  String getA_real() {
		labal :
		return a;
	}
	
	public Object getA_fake1(){
		return a;
	}
	
	public Object getA_fake2(int a){
		return a;
	}
	
	public Object getA_fake3(int a){
		int i=0;
		return a;
	}
	
	public strictfp void setA_real(String a) {
		int b;
		this.a = a;
		return;
	}
	
	public Object setA_fake1(String a) {
		this.a = a;
		return a;
	}
	
	public void setA_fake2(String a,String b) {
		this.a = a;
	}
	

	public Object setA_fake3(String a) {
		int b=2;
		return a;
	}
	
	private int[] getB_fake() {
		return b;
	}
	
	public int[] getB_fake(int fake) {
		return b;
	}
	
	public void setB_real(int[] b) {
		this.b = b;
	}
	
	public void setB_real() {
		this.b = null;//fake
	}
	
	
	public long getStandard() {
		return standard;
	}

	public void setStandard(long standard) {
		this.standard = standard;
	}
}
