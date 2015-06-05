package com.alimuya.kevvy.reflect.exception;

/**
 * @author ov_alimuya
 *
 */
public class ConstructorReflectException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public ConstructorReflectException (){
		super();
	}
	
	public ConstructorReflectException (String name){
		super(name);
	}
	
	public ConstructorReflectException (Throwable throwable){
		super(throwable);
	}
	
	public ConstructorReflectException (String name,Throwable throwable){
		super(name,throwable);
	}

}
