package com.alimuya.kevvy.reflect.assistant;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestGSettersFieldBean;

public class GSField extends KevvyField{

	@Override
	public Object get(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).getC();
	}

	@Override
	public boolean getBoolean(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).isB();
	}

	@Override
	public byte getByte(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).getC();
	}

	@Override
	public short getShort(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).getE();
	}

	@Override
	public char getChar(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).getD();
	}

	@Override
	public int getInt(Object obj) throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		return ((AssTestGSettersFieldBean)obj).getF();
		throw new FieldReflectException("class type error!");
	}

	@Override
	public long getLong(Object obj) throws FieldReflectException {

		return ((TestGSettersFieldBean)obj).getG();
	}

	@Override
	public float getFloat(Object obj) throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		return ((AssTestGSettersFieldBean)obj).getH();
		throw new FieldReflectException("class type error!");
	}
	

	@Override
	public double getDouble(Object obj) throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		return ((AssTestGSettersFieldBean)obj).getI();
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setObject(Object obj, Object value)
			throws FieldReflectException {
		((TestGSettersFieldBean)obj).setA((String)value);
	}

	@Override
	public void setByte(Object obj, byte value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setC(value);
	}

	@Override
	public void setShort(Object obj, short value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setE(value);
	}

	@Override
	public void setChar(Object obj, char value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setD(value);
	}

	@Override
	public void setInt(Object obj, int value) throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		((AssTestGSettersFieldBean)obj).setF(value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setLong(Object obj, long value)
			throws FieldReflectException {
//		TestBean bean=(TestBean)obj;
//		((AssTestGSettersFieldBean)obj).setG(value);
		throw new FieldReflectException("class type error!");
	}

	@Override
	public void setFloat(Object obj, float value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setH(value);
	}

	@Override
	public void setDouble(Object obj, double value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setI(value);
	}

	@Override
	public void setBoolean(Object obj, boolean value)
			throws FieldReflectException {

		((TestGSettersFieldBean)obj).setB(value);
	}

}
