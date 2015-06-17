package com.alimuya.kevvy.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.field.FieldReflectDirector;

/**
 * @author ov_alimuya
 *
 */
public final class KevvyFieldReflect {
	private static Map<Class<?>, KevvyFieldReflect> cache=new HashMap<Class<?>, KevvyFieldReflect>();
	private Map<String,KevvyField> map=new HashMap<String,KevvyField>();
	private KevvyField[] array=null;
	private FieldReflectDirector builder;
	private Class<?> claz;
	
	private KevvyFieldReflect (Class<?> claz){
		this.claz=claz;
		this.builder=new FieldReflectDirector(claz);
	}
	
	public static KevvyFieldReflect createFieldReflect(Class<?> claz){
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
		synchronized (map) {
			Field[] fields = claz.getDeclaredFields();
			int length=fields.length;
			this.array=new KevvyField[length];
			for (int i = 0; i < length; i++) {
				Field field = fields[i];
				KevvyField kevvyField=getField(field);
				array[i]=kevvyField;
			}
		}
		return array.clone();
	}
	
	public KevvyField getField(String fieldName){
		if(fieldName==null){
			throw new NullPointerException("field name ==null");
		}
		KevvyField kevvyField;
		synchronized (map) {
			kevvyField = map.get(fieldName);
			if(kevvyField==null){
				Field field;
				try {
					field = claz.getDeclaredField(fieldName);
					kevvyField=getKevvyField(field);
					map.put(fieldName, kevvyField);
				} catch (NoSuchFieldException e) {
					return null;
				}
			}
		}
		return kevvyField;
	}
	
	public KevvyField getField(Field field){
		if(field==null){
			throw new NullPointerException("field ==null");
		}
		String fieldName=field.getName();
		KevvyField kevvyField;
		synchronized (map) {
			kevvyField = map.get(fieldName);
			if(kevvyField==null){
				kevvyField=getKevvyField(field);
				map.put(fieldName, kevvyField);
			}
		}
		return kevvyField;
	}
	
	private KevvyField getKevvyField(Field field){
		if(!field.isAccessible()){
			field.setAccessible(true);
		}
		KevvyField kevvyField=builder.build(field);
		kevvyField.setOriginal(field);
		return kevvyField;
	}
}
