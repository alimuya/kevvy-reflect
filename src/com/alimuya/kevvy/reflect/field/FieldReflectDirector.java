package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Field;
import java.util.ArrayList;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyField;

/**
 * @author ov_alimuya
 *
 */
public class FieldReflectDirector {
	private ArrayList<IReflectObjectBuilder<KevvyField>> builders=new ArrayList<IReflectObjectBuilder<KevvyField>>();
	private Class<?> beanClass;

	public FieldReflectDirector (Class<?> beanClass){
		this.beanClass=beanClass;
		builders.add(new DirectFieldAsmBuilder());
		builders.add(new MixedFieldAsmBuilder());
		builders.add(new JavaOriginalFieldBulider());
	}
	
	public KevvyField build(Field field){
		KevvyField result = null;
		int length = builders.size();
		for (int i = 0; i < length; i++) {
			IReflectObjectBuilder<KevvyField> builder = builders.get(i);
			if(builder.isSuitable(field)){
				try {
					result=builder.build(beanClass, field);
					break;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return result;
	}
}
