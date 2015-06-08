package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

import sun.misc.Unsafe;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.utils.ReflectUtils;
import com.alimuya.kevvy.reflect.utils.UnsafeFactory;

/**
 * @author Administrator
 *
 */
public class PrivateStaticFieldBuilder implements IReflectObjectBuilder<KevvyField>{

	@Override
	public boolean isSuitable(Member member) {
		return ReflectUtils.isStatic(member) && ReflectUtils.isPrivate(member);
	}

	@Override
	public KevvyField build(Class<?> beanClass, Member member) throws Exception {
		Field field=(Field)member;
		Unsafe unsafe =UnsafeFactory.getUnsafe();
		Object base = unsafe.staticFieldBase(field);
		long offset=unsafe.staticFieldOffset(field);
		return new UnsafePrivateStaticFiled(base, offset, field);
	}
	
	public static class UnsafePrivateStaticFiled extends KevvyField{
		
		private Object base;
		private long offset;
		private Unsafe unsafe;
		private Class<?> fieldClass;
		private boolean isVolatile;

		UnsafePrivateStaticFiled(Object base,long offset,Field field){
			this.base=base;
			this.offset=offset;
			this.unsafe =UnsafeFactory.getUnsafe();
			this.fieldClass=field.getType();
			this.isVolatile=ReflectUtils.isVolatile(field);
		}
		@Override
		public Object get(Object obj) throws FieldReflectException {
			return unsafe.getObject(base, offset);
		}

		@Override
		public boolean getBoolean(Object obj) throws FieldReflectException {
			if(fieldClass==boolean.class){
				return unsafe.getBoolean(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public byte getByte(Object obj) throws FieldReflectException {
			if(fieldClass==byte.class){
				return unsafe.getByte(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public short getShort(Object obj) throws FieldReflectException {
			if(fieldClass==short.class){
				return unsafe.getShort(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public char getChar(Object obj) throws FieldReflectException {
			if(fieldClass==char.class){
				return unsafe.getChar(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public int getInt(Object obj) throws FieldReflectException {
			if(fieldClass==int.class){
				return unsafe.getInt(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public long getLong(Object obj) throws FieldReflectException {
			if(fieldClass==long.class){
				return unsafe.getLong(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public float getFloat(Object obj) throws FieldReflectException {
			if(fieldClass==float.class){
				return unsafe.getFloat(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public double getDouble(Object obj) throws FieldReflectException {
			if(fieldClass==double.class){
				return unsafe.getDouble(base, offset);
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setByte(Object obj, byte value)
				throws FieldReflectException {
			if(fieldClass==byte.class){
				if(isVolatile){
					unsafe.putByteVolatile(base, offset, value);
				}else{
					unsafe.putByte(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setShort(Object obj, short value)
				throws FieldReflectException {
			if(fieldClass==short.class){
				if(isVolatile){
					unsafe.putShortVolatile(base, offset, value);
				}else{
					unsafe.putShort(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setChar(Object obj, char value)
				throws FieldReflectException {
			if(fieldClass==char.class){
				if(isVolatile){
					unsafe.putCharVolatile(base, offset, value);
				}else{
					unsafe.putChar(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setInt(Object obj, int value) throws FieldReflectException {
			if(fieldClass==int.class){
				if(isVolatile){
					unsafe.putIntVolatile(base, offset, value);
				}else{
					unsafe.putInt(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setLong(Object obj, long value)
				throws FieldReflectException {
			if(fieldClass==long.class){
				if(isVolatile){
					unsafe.putLongVolatile(base, offset, value);
				}else{
					unsafe.putLong(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setFloat(Object obj, float value)
				throws FieldReflectException {
			if(fieldClass==float.class){
				if(isVolatile){
					unsafe.putFloatVolatile(base, offset, value);
				}else{
					unsafe.putFloat(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setDouble(Object obj, double value)
				throws FieldReflectException {
			if(fieldClass==double.class){
				if(isVolatile){
					unsafe.putDoubleVolatile(base, offset, value);
				}else{
					unsafe.putDouble(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		public void setBoolean(Object obj, boolean value)
				throws FieldReflectException {
			if(fieldClass==byte.class){
				if(isVolatile){
					unsafe.putBooleanVolatile(base, offset, value);
				}else{
					unsafe.putBoolean(base, offset, value);
				}
			}else{
				throw new FieldReflectException("class type error!");
			}
		}

		@Override
		protected void _setObject(Object obj, Object value)
				throws FieldReflectException {
			if(!fieldClass.isPrimitive()){
				Object tmp;
				if(value!=null){
					tmp = fieldClass.cast(value);
				}else{
					tmp=value;
				}
				if(isVolatile){
					unsafe.putObjectVolatile(base, offset, tmp);
				}else{
					unsafe.putObject(base, offset, tmp);
				}
				return;
			}
			throw new FieldReflectException("class type error!");
		}
		
	}

}
