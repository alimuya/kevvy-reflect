package com.alimuya.kevvy.reflect.test.field;

import java.lang.reflect.Field;

import junit.framework.TestCase;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestPrivateFieldBean;
@FixMethodOrder(MethodSorters.JVM)
public class KevvyFieldReflectTest extends TestCase{ 

	private static final Class<?> testClass=TestPrivateFieldBean.class;
	
	@Test
	public void testCreateFieldReflect() throws FieldReflectException {
		KevvyFieldReflect kevvyFieldReflect = KevvyFieldReflect.createFieldReflect(testClass);
		assertNotNull(kevvyFieldReflect);
	}

	@Test
	public void testGetFieldsSize() throws FieldReflectException {
		KevvyFieldReflect kevvyFieldReflect = KevvyFieldReflect.createFieldReflect(testClass);
		KevvyField[] fields = kevvyFieldReflect.getFields();
		Field[] jFeilds = testClass.getDeclaredFields();
		assertEquals(fields.length, jFeilds.length);
	}
	
	@Test
	public void testGetFieldsSame() throws FieldReflectException {
		KevvyFieldReflect kevvyFieldReflect = KevvyFieldReflect.createFieldReflect(testClass);
		KevvyField[] fields = kevvyFieldReflect.getFields();
		Field[] jFeilds = testClass.getDeclaredFields();
		for (int i = 0; i < jFeilds.length; i++) {
			if(!fields[i].getOriginal().equals(jFeilds[i])){
				assertTrue(false);
				return;
			}
		}
		assertTrue(true);
	}
	
	@Test
	public void testGetField() throws FieldReflectException {
		KevvyFieldReflect kevvyFieldReflect = KevvyFieldReflect.createFieldReflect(testClass);
		Field[] jFeilds = testClass.getDeclaredFields();
		for (int i = 0; i < jFeilds.length; i++) {
			Field field = jFeilds[i];
			field.setAccessible(true);
			KevvyField kf = kevvyFieldReflect.getField(field.getName());
			if(kf==null || !kf.getOriginal().equals(field)){
				assertTrue(false);
				return;
			}
		}
		assertTrue(true);
		
	}

}
