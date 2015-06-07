## Kevvy-Reflect
faster-reflect, just alpha version

## Overview


## Performance



generated on Oracle's Java 7u21, server VM.

## Usage

Method reflection :

```java
TestClass bean=new TestClass();
KevvyMethodReflect methodReflect = KevvyMethodReflect.createMethodReflect(TestClass.class);
KevvyMethod method = methodReflect.getMethod("someMethod", String.class);
method.invoke(bean, "test_str");
```

Field reflection :

```java
TestClass bean=new TestClass();
KevvyFieldReflect fieldReflect = KevvyFieldReflect.createFieldReflect(TestClass.class);
KevvyField field = fieldReflect.getField("someField");
field.setObject(bean, "test_str"); //set value
field.get(bean);   //get value
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

