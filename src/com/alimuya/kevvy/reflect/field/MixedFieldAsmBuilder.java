package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.Map;

import org.objectweb.asm.ClassWriter;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.utils.AsmUtils;
import com.alimuya.kevvy.reflect.utils.ReflectUtils;

class MixedFieldAsmBuilder extends AbstractAsmFiledBulder 
	implements IReflectObjectBuilder<KevvyField>{
	
	@Override
	public boolean isSuitable(Member member) {
		return !ReflectUtils.isStatic(member);
	}
	
	@Override
	public KevvyField build(Class<?> beanClass, Member member) throws Exception {
		Field field = (Field)member;
		String newClassName=this.createNewClassName(beanClass,field);
		ClassWriter classWriter = AsmUtils.buildClass(newClassName,KevvyField.class);
		IAddGetSetable getterbuilder=null;
		IAddGetSetable setterbuilder=null;
		BeanAnalyzer analyzer = new  BeanAnalyzer(beanClass);
		Map<String, GetSetMethodNode> fieldGSetterMap = analyzer.getFieldGetSetMethodMap();
		GetSetMethodNode gSNode = fieldGSetterMap.get(field.getName());
		if(gSNode==null){
			getterbuilder=setterbuilder=new UnsafeAddGetSetable(newClassName, classWriter, field);
		}else{
			if(gSNode.getGetter()!=null && gSNode.getSetter() !=null){
				getterbuilder =setterbuilder= new AnalyzerGSAddGetSetable(beanClass, field, classWriter, gSNode);
			}else if(gSNode.getGetter() !=null){
				getterbuilder = new AnalyzerGSAddGetSetable(beanClass, field, classWriter, gSNode);
				setterbuilder=new UnsafeAddGetSetable(newClassName, classWriter, field);
			}else{
				setterbuilder= new AnalyzerGSAddGetSetable(beanClass, field, classWriter, gSNode);
				getterbuilder=new UnsafeAddGetSetable(newClassName, classWriter, field);
			}
		}
		getterbuilder.addGetters();
		setterbuilder.addSetters();
		classWriter.visitEnd();
		byte[] bytes = classWriter.toByteArray();
		return AsmUtils.asmNewInstance(beanClass,KevvyField.class, newClassName, bytes);
	}

}
