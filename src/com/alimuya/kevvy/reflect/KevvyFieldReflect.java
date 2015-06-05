package com.alimuya.kevvy.reflect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.factroy.FieldClassBuilder;

/**
 * @author ov_alimuya
 *
 */
public class KevvyFieldReflect {
	private Map<String,KevvyField> map=new HashMap<String,KevvyField>();
	private KevvyField[] array=null;
	
	private KevvyFieldReflect (Class<?> claz) throws FieldReflectException{
		try {
			Field[] fields = claz.getDeclaredFields();
			int length=fields.length;
			this.array=new KevvyField[length];
			FieldClassBuilder builder=new FieldClassBuilder(claz);
			builder.analyse();
			for (int i = 0; i < length; i++) {
				Field field = fields[i];
				KevvyField kevvyField=builder.build(field);
				kevvyField.setOriginalFeld(field);
				map.put(field.getName(), kevvyField);
				array[i]=kevvyField;
			}
		} catch (Throwable e) {
			throw new FieldReflectException(e);
		}
	}
	
	public static KevvyFieldReflect createFieldReflect(Class<?> claz) throws FieldReflectException{
		if(claz==null || claz.isPrimitive()){
			throw new IllegalArgumentException("argument claz==null || claz.isPrimitive()");
		}
		return new KevvyFieldReflect(claz);
	}
	
	public KevvyField[] getFields(){
		return array.clone();
	}
	
	public KevvyField getField(String name){
		return map.get(name);
	}
	
}
