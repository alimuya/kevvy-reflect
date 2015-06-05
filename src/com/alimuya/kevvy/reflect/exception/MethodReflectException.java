package com.alimuya.kevvy.reflect.exception;

/**
 * @author ov_alimuya
 *
 */
public class MethodReflectException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MethodReflectException (){
		super();
	}
	
	public MethodReflectException (String name){
		super(name);
	}
	
	public MethodReflectException (Throwable throwable){
		super(throwable);
	}
	
	public MethodReflectException (String name,Throwable throwable){
		super(name,throwable);
	}

}
