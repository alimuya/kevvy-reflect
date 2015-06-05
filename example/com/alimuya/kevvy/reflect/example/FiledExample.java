package com.alimuya.kevvy.reflect.example;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.KevvyFieldReflect;
import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.test.bean.TestFieldBean;

public class FiledExample {
	
	public static void main(String[] args) throws FieldReflectException {
		TestFieldBean bean=new TestFieldBean();
		KevvyFieldReflect kfr = KevvyFieldReflect.createFieldReflect(TestFieldBean.class);
		KevvyField kf = kfr.getField("a");
		kf.setObject(bean, "sssss34");
		System.out.println(kf.get(bean));
	}
}
