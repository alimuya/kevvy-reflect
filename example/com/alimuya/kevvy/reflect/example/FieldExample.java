package com.alimuya.kevvy.reflect.example;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.test.field.TestGSettersFieldBean;

/**
 * @author ov_alimuya
 *
 */
public class FieldExample {
	
	public static void main(String[] args) throws FieldReflectException {
		TestGSettersFieldBean bean=new TestGSettersFieldBean();
		KevvyFieldReflect kfr = KevvyFieldReflect.createFieldReflect(TestGSettersFieldBean.class);
		KevvyField kf = kfr.getField("a");
		kf.setObject(bean, "sssss34");
		System.out.println(kf.get(bean));
	}
}
