package com.alimuya.kevvy.reflect;

import java.lang.reflect.Field;

import com.alimuya.kevvy.reflect.exception.FieldReflectException;

/**
 * @author ov_alimuya
 *
 */
public abstract class KevvyField {
	protected Field originalFeld;
	public abstract Object get(Object obj) throws FieldReflectException;
	public abstract boolean getBoolean(Object obj) throws FieldReflectException;
	public abstract byte getByte(Object obj) throws FieldReflectException;
	public abstract short getShort(Object obj) throws FieldReflectException;
	public abstract char getChar(Object obj) throws FieldReflectException;
	public abstract int getInt(Object obj) throws FieldReflectException;
	public abstract long getLong(Object obj) throws FieldReflectException;
	public abstract float getFloat(Object obj) throws FieldReflectException;
	public abstract double getDouble(Object obj) throws FieldReflectException;
	
	public abstract void setObject(Object obj,Object value) throws FieldReflectException;
	public abstract void setByte(Object obj,byte value) throws FieldReflectException;
	public abstract void setShort(Object obj,short value) throws FieldReflectException;
	public abstract void setChar(Object obj,char value) throws FieldReflectException;
	public abstract void setInt(Object obj,int value) throws FieldReflectException;
	public abstract void setLong(Object obj,long value) throws FieldReflectException;
	public abstract void setFloat(Object obj,float value) throws FieldReflectException;
	public abstract void setDouble(Object obj,double value) throws FieldReflectException;
	public abstract void setBoolean(Object obj,boolean value) throws FieldReflectException;
	
	public Field getOriginalFeld() {
		return originalFeld;
	}
	
	protected void setOriginalFeld(Field field) {
		this.originalFeld = field;
	}
	  
	
	
}
