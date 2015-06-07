package com.alimuya.kevvy.reflect.test.field;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;

@FixMethodOrder(MethodSorters.JVM)
public abstract class AbstractKevvyFieldTest extends TestCase {
	private KevvyFieldReflect fieldReflect;
	protected Class<?> testClass;
	protected Object bean;
	private Map<Class<?>, Field> fieldMap = new HashMap<Class<?>, Field>();
	
	protected abstract void  initBeanClass();
	
	@Before
	public void setUp() throws Exception {
		initBeanClass();
		this.fieldReflect = KevvyFieldReflect.createFieldReflect(testClass);
		Field[] fields = testClass.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			field.setAccessible(true);
			Class<?> type = field.getType();
			if (type.isPrimitive()) {
				fieldMap.put(type, field);
			} else {
				fieldMap.put(Object.class, field);
			}
		}
	}

	@Test
	public void testGet() {
		try {
			Field field = fieldMap.get(Object.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			Object ko = kfield.get(bean);
			Object fo = field.get(bean);
			assertEquals(ko,fo);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	
	@Test
	public void testGetAsWrapper() {
		try {
			Field field = fieldMap.get(long.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.get(bean),field.get(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	
	@Test
	public void testGetBoolean() {
		try {
			Field field = fieldMap.get(boolean.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getBoolean(bean),field.getBoolean(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetByte() {
		try {
			Field field = fieldMap.get(byte.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getByte(bean),field.getByte(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetShort() {
		try {
			Field field = fieldMap.get(short.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getShort(bean),field.getShort(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetChar() {
		try {
			Field field = fieldMap.get(char.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getChar(bean),field.getChar(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetInt() {
		try {
			Field field = fieldMap.get(int.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getInt(bean),field.getInt(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetLong() {
		try {
			Field field = fieldMap.get(long.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getLong(bean),field.getLong(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetFloat() {
		try {
			Field field = fieldMap.get(float.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getFloat(bean),field.getFloat(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testGetDouble() {
		try {
			Field field = fieldMap.get(double.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			assertEquals(kfield.getDouble(bean),field.getDouble(bean));
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetObject() {
		try {
			Field field = fieldMap.get(Object.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			Object sss = "ssssss";
			kfield.setObject(bean, sss);
			Object newOne = field.get(bean);
			assertEquals(newOne,sss);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetByte() {
		try {
			Field field = fieldMap.get(byte.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			byte obj=99;
			kfield.setByte(bean, obj);
			assertEquals(field.getByte(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetShort() {
		try {
			Field field = fieldMap.get(short.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			short obj=555;
			kfield.setShort(bean, obj);
			assertEquals(field.getShort(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetChar() {
		try {
			Field field = fieldMap.get(char.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			char obj=563;
			kfield.setChar(bean, obj);
			assertEquals(field.getChar(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetInt() {
		try {
			Field field = fieldMap.get(int.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			int obj=563254;
			kfield.setInt(bean, obj);
			assertEquals(field.getInt(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetLong() {
		try {
			Field field = fieldMap.get(long.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			long obj=System.currentTimeMillis();
			kfield.setLong(bean, obj);
			assertEquals(field.getLong(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetFloat() {
		try {
			Field field = fieldMap.get(float.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			float obj=563.254f;
			kfield.setFloat(bean, obj);
			assertEquals(field.getFloat(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetDouble() {
		try {
			Field field = fieldMap.get(double.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			double obj=3.31415;
			kfield.setDouble(bean, obj);
			assertEquals(field.getDouble(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}

	@Test
	public void testSetBoolean() {
		try {
			Field field = fieldMap.get(boolean.class);
			KevvyField kfield = this.fieldReflect.getField(field.getName());
			boolean obj=true;
			kfield.setBoolean(bean, obj);
			assertEquals(field.getBoolean(bean),obj);
		} catch (Exception e) {
			e.printStackTrace();
			fail("excption");
		}
	}
	
	
	@Test
	public void testSetException() {
		Field field = fieldMap.get(boolean.class);
		KevvyField kfield = this.fieldReflect.getField(field.getName());
		try {
			kfield.setChar(bean, (char)34);
			fail();
		} catch (FieldReflectException e) {
			assertTrue(true);
		}
	}
}
