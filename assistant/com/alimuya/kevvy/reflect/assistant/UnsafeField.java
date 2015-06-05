package com.alimuya.kevvy.reflect.assistant;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.factroy.UnsafeFactory;

import sun.misc.Unsafe;

public class UnsafeField extends KevvyField {
	private static Unsafe unsafe=UnsafeFactory.getUnsafe();
	
	@Override
	public Object get(Object obj)  throws FieldReflectException{
		return unsafe.getDouble(obj, 49L);
//		throw new KevvyFieldReflectException("class type error!");
	}
	
	@Override
	public boolean getBoolean(Object obj) throws FieldReflectException {
//		return unsafe.getBoolean(obj, 49L);
		throw new FieldReflectException("class type error!");
	}
	
	@Override
	public byte getByte(Object obj)  throws FieldReflectException{
//		return unsafe.getByte(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public short getShort(Object obj)  throws FieldReflectException{
//		return unsafe.getShort(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public char getChar(Object obj)  throws FieldReflectException{
//		return unsafe.getChar(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public int getInt(Object obj)  throws FieldReflectException{
//		return unsafe.getInt(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public long getLong(Object obj)  throws FieldReflectException{
//		return unsafe.getLong(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public float getFloat(Object obj)  throws FieldReflectException{
//		return unsafe.getFloat(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public double getDouble(Object obj)  throws FieldReflectException{
//		return unsafe.getDouble(obj, 49L);
		throw new FieldReflectException("class type error!");
	}

	
	
	
	//============================================================================
	
	@Override
	public void setObject(Object obj, Object value)
			throws FieldReflectException {
//		unsafe.putObject(obj, 49L, value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setByte(Object obj, byte value)
			throws FieldReflectException {
//		unsafe.putByte(obj, 49L, value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setShort(Object obj, short value)
			throws FieldReflectException {
//		unsafe.putShort(obj, 49L, value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setChar(Object obj, char value)
			throws FieldReflectException {
//		unsafe.putChar(obj, 49L, value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setInt(Object obj, int value) throws FieldReflectException {
//		unsafe.putInt(obj, 49L, value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setLong(Object obj, long value)
			throws FieldReflectException {
//		unsafe.putLong(obj, 49L, value);
		throw new FieldReflectException("class type error!");
		
	}

	@Override
	public void setFloat(Object obj, float value)
			throws FieldReflectException {
//		unsafe.putFloat(obj, 49L, value);
		throw new FieldReflectException("class type error!");
		
	}

	@Override
	public void setDouble(Object obj, double value)
			throws FieldReflectException {
//		unsafe.putDouble(obj, 49L, value);
		throw new FieldReflectException("class type error!");
		
	}

	@Override
	public void setBoolean(Object obj, boolean value)
			throws FieldReflectException {
		unsafe.putBoolean(obj, 49L, value);
//		throw new KevvyFieldReflectException("class type error!");
	}



}
