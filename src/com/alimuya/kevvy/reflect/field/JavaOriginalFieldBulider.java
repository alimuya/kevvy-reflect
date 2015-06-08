package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Member;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;

/**
 * @author ov_alimuya
 *
 */
class JavaOriginalFieldBulider implements IReflectObjectBuilder<KevvyField>{
	

	@Override
	public boolean isSuitable(Member member) {
		return true;
	}

	@Override
	public KevvyField build(Class<?> beanClass, Member member){
		return new JavaOriginalReflectField();
	}
	
	/**
	 * @author ov_alimuya
	 *
	 */
	public static  class JavaOriginalReflectField extends KevvyField{
		
		@Override
		public Object get(Object obj) throws FieldReflectException {
			try {
				return original.get(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public boolean getBoolean(Object obj) throws FieldReflectException {
			try {
				return original.getBoolean(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public byte getByte(Object obj) throws FieldReflectException {
			try {
				return original.getByte(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public short getShort(Object obj) throws FieldReflectException {
			try {
				return original.getShort(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public char getChar(Object obj) throws FieldReflectException {
			try {
				return original.getChar(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public int getInt(Object obj) throws FieldReflectException {
			try {
				return original.getInt(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public long getLong(Object obj) throws FieldReflectException {
			try {
				return original.getLong(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public float getFloat(Object obj) throws FieldReflectException {
			try {
				return original.getFloat(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public double getDouble(Object obj) throws FieldReflectException {
			try {
				return original.getDouble(obj);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setObject(Object obj, Object value)
				throws FieldReflectException {
			try {
				original.set(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setByte(Object obj, byte value)
				throws FieldReflectException {
			try {
				original.setByte(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setShort(Object obj, short value)
				throws FieldReflectException {
			try {
				original.setShort(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
			
		}

		@Override
		public void setChar(Object obj, char value)
				throws FieldReflectException {
			try {
				original.setChar(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setInt(Object obj, int value) throws FieldReflectException {
			try {
				original.setInt(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setLong(Object obj, long value)
				throws FieldReflectException {
			try {
				original.setLong(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setFloat(Object obj, float value)
				throws FieldReflectException {
			try {
				original.setFloat(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setDouble(Object obj, double value)
				throws FieldReflectException {
			try {
				original.setDouble(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		public void setBoolean(Object obj, boolean value)
				throws FieldReflectException {
			try {
				original.setBoolean(obj, value);
			} catch (Exception e) {
				throw new FieldReflectException(e);
			}
		}

		@Override
		protected void _setObject(Object obj, Object value)
				throws FieldReflectException {
			
		}
	}
}
