package com.alimuya.kevvy.reflect.exception;

/**
 * @author ov_alimuya
 *
 */
public class InvokeTargetException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public InvokeTargetException (){
		super();
	}
	
	public InvokeTargetException (String name){
		super(name);
	}
	
	public InvokeTargetException (Throwable throwable){
		super(throwable);
	}
	
	public InvokeTargetException (String name,Throwable throwable){
		super(name,throwable);
	}

}
