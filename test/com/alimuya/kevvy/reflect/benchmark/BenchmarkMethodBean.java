package com.alimuya.kevvy.reflect.benchmark;

public class BenchmarkMethodBean{
	
	public void voidNoParameter() throws Exception{}
	
	public void voidParameters (String p0,String p1, String p2, double p3, int p4){}
	
	public String returnParameters(String p0,String p1, String p2, double p3, int p4){
		return "sssss";
	}
		
	public String returnNoParameter(){
		return "sssss";
	}
	
	private String privateReturnNoParameter(){
		return "sssss";
	}
	
	private void privateVoidNoParameter(){}
	
	private void privateVoidParameters (String p0,String p1, String p2, double p3, int p4){}
	
	public String privateReturnParameters(String p0,String p1, String p2, double p3, int p4){
		return "sssss";
	}
	
}
