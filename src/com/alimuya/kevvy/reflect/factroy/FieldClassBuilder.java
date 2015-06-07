package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;

import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.analyzer.BeanAnalyzer;
import com.alimuya.kevvy.reflect.analyzer.GetSetMethodNode;

/**
 * @author ov_alimuya
 *
 */
public class FieldClassBuilder {
	private static AtomicInteger id=new AtomicInteger(0);
	private Class<?> beanClass;
	private Map<String, GetSetMethodNode> fieldGSetterMap;

	public FieldClassBuilder (Class<?> beanClass){
		this.beanClass=beanClass;
	}
	
	public void analyse(){
		BeanAnalyzer analyzer = new  BeanAnalyzer(this.beanClass);
		this.fieldGSetterMap=analyzer.getFieldGetSetMethodMap();
	}
	
	public KevvyField build(Field field) throws Exception{
		KevvyField result;
		if(isStaticField(field)){
			result=new JavaOriginalReflectField();
		}else{
			String newClassName=this.createNewClassName(field);
			ClassWriter classWriter = AsmClassBuilder.build(newClassName,KevvyField.class);
			IGSMethodBuilder getterbuilder=null;
			IGSMethodBuilder setterbuilder=null;
			if((field.getModifiers() & Modifier.PUBLIC)==Modifier.PUBLIC){
				getterbuilder =setterbuilder= new DirectGSMethodBuilder(beanClass, classWriter, field);
			}else{
				GetSetMethodNode gSNode = fieldGSetterMap.get(field.getName());
				if(gSNode==null){
					getterbuilder=setterbuilder=new UnsafeGSMethodBuilder(newClassName, classWriter, field);
				}else{
					if(gSNode.getGetter()!=null && gSNode.getSetter() !=null){
						getterbuilder =setterbuilder= new AnalyzerGSMethodBuilder(beanClass, field, classWriter, gSNode);
					}else if(gSNode.getGetter() !=null){
						getterbuilder = new AnalyzerGSMethodBuilder(beanClass, field, classWriter, gSNode);
						setterbuilder=new UnsafeGSMethodBuilder(newClassName, classWriter, field);
					}else{
						setterbuilder= new AnalyzerGSMethodBuilder(beanClass, field, classWriter, gSNode);
						getterbuilder=new UnsafeGSMethodBuilder(newClassName, classWriter, field);
					}
				}
			}
			getterbuilder.addGetters();
			setterbuilder.addSetters();
			classWriter.visitEnd();
			byte[] bytes = classWriter.toByteArray();
			result = AsmUtils.asmNewInstance(KevvyField.class, newClassName, bytes);
		}
		return result;
	}
	
	private  boolean isStaticField(Field field){
		return (field.getModifiers() & Modifier.STATIC)==Modifier.STATIC;
	}
	
	private String createNewClassName(Field field){
		String className=beanClass.getName()+"_autofield_"+field.getName()+"_"+id.getAndIncrement();
		return className;
	}
}
