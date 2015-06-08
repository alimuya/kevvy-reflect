## Kevvy-Reflect
faster-reflect, just alpha version

## Overview


## Performance
Field Reflection :

Non-Private Field

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/field/field_not_private_jdk7.png)

Private Field

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/field/field_private_jdk7.png)

Bean Field, Non-Private Field and Java-Getter/Setter Comparison

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/field/field_bean_public_gs_jdk7.png)

Field Family Portrait

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/field/field_all_jdk7.png)





Public Method Invoke :

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/method/jdk7_method_all.png)

Public Constructor Invoke:

![image](https://github.com/alimuya/kevvy-reflect/raw/master/build/res/benchmark/constructor/jdk7_constructor_all.png)


generated on Oracle's Java 7u21, server VM.

## Usage

Field reflection :

```java
TestClass bean=new TestClass();
KevvyFieldReflect fieldReflect = KevvyFieldReflect.createFieldReflect(TestClass.class);
KevvyField field = fieldReflect.getField("someField");
field.setObject(bean, "test_str"); //set value
field.get(bean);   //get value
```

Method reflection :

```java
TestClass bean=new TestClass();
KevvyMethodReflect methodReflect = KevvyMethodReflect.createMethodReflect(TestClass.class);
KevvyMethod method = methodReflect.getMethod("someMethod", String.class);
method.invoke(bean, "test_str");
```

Constructor reflection :

```java
KevvyConstructorReflect<TestClass> constructorReflect = KevvyConstructorReflect.createConstructor(TestClass.class);
KevvyConstructor<TestClass> constructor = constructorReflect.getConstructor(String.class);
TestClass bean = constructor.newInstance("test_str");
```

Instantiate Without Constructor:
```java
TestClass testEmptyBean = KevvyConstructorReflect.newIstanceWithoutConstructor(TestClass.class);
```

