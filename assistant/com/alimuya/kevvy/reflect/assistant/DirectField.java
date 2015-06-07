package com.alimuya.kevvy.reflect.assistant;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;

public class DirectField extends KevvyField{

	@Override
	public Object get(Object obj) throws FieldReflectException {
		return ((FieldBean)obj).b;
//		throw new KevvyFieldReflectException("class type error!");
	}

	@Override
	public boolean getBoolean(Object obj) throws FieldReflectException {
		return ((FieldBean)obj).b;
//		throw new KevvyFieldReflectException("class type error!");
	}

	@Override
	public byte getByte(Object obj) throws FieldReflectException {
//		return ((TestPublicFieldBean)obj).c;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public short getShort(Object obj) throws FieldReflectException {
		return ((FieldBean)obj).e;
//		throw new KevvyFieldReflectException("class type error!");
	}

	@Override
	public char getChar(Object obj) throws FieldReflectException {
//		return ((TestPublicFieldBean)obj).d;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public int getInt(Object obj) throws FieldReflectException {
//		return ((TestPublicFieldBean)obj).f;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public long getLong(Object obj) throws FieldReflectException {
		return ((FieldBean)obj).g;
//		throw new KevvyFieldReflectException("class type error!");
	}

	@Override
	public float getFloat(Object obj) throws FieldReflectException {
//		return ((TestPublicFieldBean)obj).h;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public double getDouble(Object obj) throws FieldReflectException {
//		return ((TestPublicFieldBean)obj).i;
		throw new FieldReflectException("class type error!");
	}
	
	//===============================================================================
	
	@Override
	public void _setObject(Object obj, Object value)
			throws FieldReflectException {
		((FieldBean)obj).a=(String[])value;
	}

	@Override
	public void setByte(Object obj, byte value)
			throws FieldReflectException {
		((FieldBean)obj).c=value;
	}

	@Override
	public void setShort(Object obj, short value)
			throws FieldReflectException {
		((FieldBean)obj).e=value;
	}

	@Override
	public void setChar(Object obj, char value)
			throws FieldReflectException {
		((FieldBean)obj).d=value;
	}

	@Override
	public void setInt(Object obj, int value) throws FieldReflectException {
		((FieldBean)obj).f=value;
	}

	@Override
	public void setLong(Object obj, long value)
			throws FieldReflectException {
		((FieldBean)obj).g=value;
	}

	@Override
	public void setFloat(Object obj, float value)
			throws FieldReflectException {
//		((TestPublicFieldBean)obj).h=value;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setDouble(Object obj, double value)
			throws FieldReflectException {
//		((TestPublicFieldBean)obj).i=value;
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setBoolean(Object obj, boolean value)
			throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		((TestPublicFieldBean)obj).b=value;
		throw new FieldReflectException("class type error!");
	}

}
