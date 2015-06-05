package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Field;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;

/**
 * @author ov_alimuya
 *
 */
public class JavaOriginalReflectField extends KevvyField{
	
	@Override
	public Object get(Object obj) throws FieldReflectException {
		try {
			return originalFeld.get(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public boolean getBoolean(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getBoolean(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public byte getByte(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getByte(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public short getShort(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getShort(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public char getChar(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getChar(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public int getInt(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getInt(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public long getLong(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getLong(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public float getFloat(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getFloat(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public double getDouble(Object obj) throws FieldReflectException {
		try {
			return originalFeld.getDouble(obj);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setObject(Object obj, Object value)
			throws FieldReflectException {
		try {
			originalFeld.set(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setByte(Object obj, byte value)
			throws FieldReflectException {
		try {
			originalFeld.setByte(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setShort(Object obj, short value)
			throws FieldReflectException {
		try {
			originalFeld.setShort(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
		
	}

	@Override
	public void setChar(Object obj, char value)
			throws FieldReflectException {
		try {
			originalFeld.setChar(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setInt(Object obj, int value) throws FieldReflectException {
		try {
			originalFeld.setInt(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setLong(Object obj, long value)
			throws FieldReflectException {
		try {
			originalFeld.setLong(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setFloat(Object obj, float value)
			throws FieldReflectException {
		try {
			originalFeld.setFloat(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setDouble(Object obj, double value)
			throws FieldReflectException {
		try {
			originalFeld.setDouble(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	public void setBoolean(Object obj, boolean value)
			throws FieldReflectException {
		try {
			originalFeld.setBoolean(obj, value);
		} catch (Exception e) {
			throw new FieldReflectException(e);
		}
	}

	@Override
	protected void setOriginalFeld(Field field) {
		super.setOriginalFeld(field);
		this.originalFeld.setAccessible(true);
	}

}
