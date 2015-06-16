package com.alimuya.kevvy.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.field.FieldReflectDirector;

/**
 * @author ov_alimuya
 *
 */
public final class KevvyFieldReflect {
	private static Map<Class<?>, KevvyFieldReflect> cache=new HashMap<Class<?>, KevvyFieldReflect>();
	private Map<String,KevvyField> map=new HashMap<String,KevvyField>();
	private KevvyField[] array=null;
	
	private KevvyFieldReflect (Class<?> claz) throws FieldReflectException{
		try {
			Field[] fields = claz.getDeclaredFields();
			int length=fields.length;
			this.array=new KevvyField[length];
			FieldReflectDirector builder=new FieldReflectDirector(claz);
			for (int i = 0; i < length; i++) {
				Field field = fields[i];
				KevvyField kevvyField=builder.build(field);
				kevvyField.setOriginal(field);
				map.put(field.getName(), kevvyField);
				array[i]=kevvyField;
			}
		} catch (Throwable e) {
			throw new FieldReflectException(e);
		}
	}
	
	public static KevvyFieldReflect createFieldReflect(Class<?> claz) throws FieldReflectException{
		KevvyFieldReflect reflect;
		synchronized (cache) {
			reflect = cache.get(claz);
			if(reflect==null){
				reflect=new KevvyFieldReflect(claz);
				cache.put(claz, reflect);
			}
		}
		return reflect;
	}
	
	public KevvyField[] getFields(){
		return array.clone();
	}
	
	public KevvyField getField(String fieldName){
		if(fieldName==null){
			throw new IllegalArgumentException("field name ==null");
		}
		return map.get(fieldName);
	}
	
}
