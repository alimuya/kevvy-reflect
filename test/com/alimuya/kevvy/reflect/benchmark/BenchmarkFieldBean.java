package com.alimuya.kevvy.reflect.benchmark;


public class BenchmarkFieldBean {
	public String publicField="ssss1";
	private String privateField="ssss1";
	private String mixedField="ssss1";
	private String gsField="ssss1";
	
	public String getMixedField() {
		return mixedField;
	}

	public String getGsField() {
		return gsField;
	}

	public void setGsField(String gsField) {
		this.gsField = gsField;
	}
	
}
