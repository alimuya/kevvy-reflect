package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Field;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.tree.MethodNode;

import com.alimuya.kevvy.reflect.analyzer.GetSetMethodNode;

/**
 * @author ov_alimuya
 *
 */
class AnalyzerGSMethodBuilder extends AbstractAsmBulder implements IGSMethodBuilder{
	private Field field;
	private ClassWriter cw;
	private Class<?> beanClass;
	private GetSetMethodNode node;
	
	AnalyzerGSMethodBuilder(Class<?> beanClass,Field field,ClassWriter cw,GetSetMethodNode node){
		this.field=field;
		this.cw=cw;
		this.beanClass=beanClass;
		this.node=node;
	}
	
	@Override
	public void addGetters() {
		createNormalGetValueMethod();
		this.createGetValueMethod("getInt", int.class);
		this.createGetValueMethod("getChar", char.class);
		this.createGetValueMethod("getShort", short.class);
		this.createGetValueMethod("getByte", byte.class);
//		this.createGetValueMethod("getObject", Object.class);
		this.createGetValueMethod("getLong", long.class);
		this.createGetValueMethod("getFloat", float.class);
		this.createGetValueMethod("getDouble", double.class);
		this.createGetValueMethod("getBoolean", boolean.class);
	}

	private void createGetValueMethod(String methodName, Class<?> returnClass) {
		MethodVisitor mv = this.createGetMethodVisitor(cw,methodName, returnClass);
		if(AsmUtils.canThrowException(returnClass,field.getType())){
			this.createThrowException(mv);
			mv.visitMaxs(3, 2);
		}else{
			String beanAsmName = AsmUtils.toAsmCls(beanClass);
			MethodNode getter = node.getGetter();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitTypeInsn(CHECKCAST, beanAsmName);
			mv.visitMethodInsn(INVOKEVIRTUAL, beanAsmName, getter.name, 
					AsmUtils.getMethodDesc(field.getType()), false);
			mv.visitInsn(AsmUtils.getReturnTag(returnClass));
			if(AsmUtils.needMoreStack(returnClass)){
				mv.visitMaxs(2, 2);
			}else{
				mv.visitMaxs(1, 2);
			}
		}
		mv.visitEnd();
	}
	

	private void createNormalGetValueMethod(){
		MethodVisitor mv = this.createGetMethodVisitor(cw,"get", Object.class);
		Class<?> fieldClass = field.getType();
		String beanAsmName = AsmUtils.toAsmCls(beanClass);
		MethodNode getter = node.getGetter();
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, beanAsmName);
		mv.visitMethodInsn(INVOKEVIRTUAL, beanAsmName, getter.name, 
				AsmUtils.getMethodDesc(fieldClass), false);
		if(fieldClass.isPrimitive()){
			Class<?> wrapperClass = AsmUtils.convert2WrapperClass(fieldClass);
			mv.visitMethodInsn(INVOKESTATIC, AsmUtils.toAsmCls(wrapperClass),
					"valueOf", AsmUtils.getMethodDesc(wrapperClass,fieldClass), false);
		}
		mv.visitInsn(AsmUtils.getReturnTag(Object.class));
		if(AsmUtils.needMoreStack(fieldClass)){
			mv.visitMaxs(2, 2);
		}else{
			mv.visitMaxs(1, 2);
		}
		mv.visitEnd();
	}
	
	@Override
	public void addSetters() {
		this.createSetValueMethod("setInt", int.class);
		this.createSetValueMethod("setChar", char.class);
		this.createSetValueMethod("setShort", short.class);
		this.createSetValueMethod("setByte", byte.class);
		this.createSetValueMethod("setObject", Object.class);
		this.createSetValueMethod("setLong", long.class);
		this.createSetValueMethod("setFloat", float.class);
		this.createSetValueMethod("setDouble", double.class);
		this.createSetValueMethod("setBoolean", boolean.class);
		
	}

	private void createSetValueMethod(String methodName, Class<?> argClass) {
		MethodVisitor mv = this.createSetMethodVisitor(cw,methodName, argClass);
		Class<?> fieldClass = field.getType();
		if(AsmUtils.canThrowException(argClass,fieldClass)){
			this.createThrowException(mv);
			if(AsmUtils.needMoreStack(argClass)){
				mv.visitMaxs(3, 4);
			}else{
				mv.visitMaxs(3, 3);
			}
		}else{
			String beanAsmName = AsmUtils.toAsmCls(beanClass);
			MethodNode setter = node.getSetter();
			mv.visitVarInsn(ALOAD, 1);
			mv.visitTypeInsn(CHECKCAST, beanAsmName);
			mv.visitVarInsn(AsmUtils.getLoadTag(argClass), 2);
			if(fieldClass!=argClass){
				mv.visitTypeInsn(CHECKCAST, AsmUtils.toAsmCls(fieldClass));
			}
			mv.visitMethodInsn(INVOKEVIRTUAL, beanAsmName, setter.name, AsmUtils.getMethodDesc(void.class, fieldClass), false);
			mv.visitInsn(RETURN);
			if(AsmUtils.needMoreStack(argClass)){
				mv.visitMaxs(3, 4);
			}else{
				mv.visitMaxs(2, 3);
			}
		}
		mv.visitEnd();
		
	}
}
