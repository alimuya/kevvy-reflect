package com.alimuya.kevvy.reflect.test.bean;



public class TestFieldBean {
	public String a="adc";
	public boolean b=true;
	private byte c=23;
	private char d=98;
	private short e=345;
	private int f=34567;
	private long g=System.currentTimeMillis();
	private float h=1.23f;
	private double i=234.58909;
	public short getE() {
		return e;
	}
	public void setE(short e) {
		this.e = e;
	}
	public int getF() {
		return f;
	}
	public long getG() {
		return g;
	}
	public void setG(long g) {
		this.g = g;
	}
	public void setH(float h) {
		this.h = h;
	}
	public double getI() {
		return i;
	}
}
