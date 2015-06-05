package com.alimuya.kevvy.reflect.test.field;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

// RunWith表示这个类是一个suite的类
@RunWith(Suite.class)
// 说明这个类中包含哪些测试组建
@SuiteClasses({	
	BeanAnalyzerTest.class,
	KevvyFieldReflectTest.class,
	PrivateKevvyFieldTest.class,
	PublicKevvyFieldTest.class,
	GSKevvyFieldTest.class
})
public class KevvyTestFieldSuite {

}




