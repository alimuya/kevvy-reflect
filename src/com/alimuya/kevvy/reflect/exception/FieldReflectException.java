package com.alimuya.kevvy.reflect.exception;

/**
 * @author ov_alimuya
 *
 */
public class FieldReflectException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public FieldReflectException (){
		super();
	}
	
	public FieldReflectException (String name){
		super(name);
	}
	
	public FieldReflectException (Throwable throwable){
		super(throwable);
	}
	
	public FieldReflectException (String name,Throwable throwable){
		super(name,throwable);
	}

}
