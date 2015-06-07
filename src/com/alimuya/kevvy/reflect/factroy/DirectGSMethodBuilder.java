package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Field;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

/**
 * @author ov_alimuya
 *
 */
class DirectGSMethodBuilder extends AbstractAsmBulder implements IGSMethodBuilder{
	
	private ClassWriter cw;
	private Field field;
	private Class<?> clazz;

	DirectGSMethodBuilder(Class<?> beanClass,ClassWriter cw,Field field){
		this.cw=cw;
		this.field=field;
		this.clazz=beanClass;
	}
	
	@Override
	public void addGetters() {
		this.createNormalGetValueMethod(cw, clazz, field);
		this.createGetValueMethod(cw, clazz, "getInt", int.class, field);
		this.createGetValueMethod(cw, clazz, "getChar", char.class, field);
		this.createGetValueMethod(cw, clazz, "getShort", short.class, field);
		this.createGetValueMethod(cw, clazz, "getByte", byte.class, field);
//		this.createGetValueMethod(cw, clazz, "getObject", Object.class, field);
		this.createGetValueMethod(cw, clazz, "getLong", long.class, field);
		this.createGetValueMethod(cw, clazz, "getFloat", float.class, field);
		this.createGetValueMethod(cw, clazz, "getDouble", double.class, field);
		this.createGetValueMethod(cw, clazz, "getBoolean", boolean.class, field);
		
	}

	@Override
	public void addSetters() {
		this.createSetValueMethod(cw, clazz, "setInt", int.class, field);
		this.createSetValueMethod(cw, clazz, "setChar", char.class, field);
		this.createSetValueMethod(cw, clazz, "setShort", short.class, field);
		this.createSetValueMethod(cw, clazz, "setByte", byte.class, field);
		this.createSetValueMethod(cw, clazz, "_setObject", Object.class, field);
		this.createSetValueMethod(cw, clazz, "setLong", long.class, field);
		this.createSetValueMethod(cw, clazz, "setFloat", float.class, field);
		this.createSetValueMethod(cw, clazz, "setDouble", double.class, field);
		this.createSetValueMethod(cw, clazz, "setBoolean", boolean.class, field);
		
	}
	
	private void createSetValueMethod(ClassWriter cw,Class<?> beanClaz,String methodName,Class<?> argClass,Field field){
		MethodVisitor mv = this.createSetMethodVisitor(cw, methodName, argClass);
		String asmClassName=AsmUtils.toAsmCls(beanClaz);
		Class<?> fieldClass=field.getType();
		if(AsmUtils.canThrowException(argClass, fieldClass)){
			this.createThrowException(mv);
			if(AsmUtils.needMoreStack(argClass)){
				mv.visitMaxs(3, 4);
			}else{
				mv.visitMaxs(3, 3);
			}
		}else{
			mv.visitVarInsn(ALOAD, 1);
			mv.visitTypeInsn(CHECKCAST, asmClassName);
			mv.visitVarInsn(AsmUtils.getLoadTag(argClass), 2);
			if(fieldClass!=argClass){
				mv.visitTypeInsn(CHECKCAST, AsmUtils.toAsmCls(fieldClass));
			}
			mv.visitFieldInsn(PUTFIELD, asmClassName, field.getName(), Type.getDescriptor(fieldClass));
			mv.visitInsn(RETURN);
			if(AsmUtils.needMoreStack(argClass)){
				mv.visitMaxs(3, 4);
			}else{
				mv.visitMaxs(2, 3);
			}
		}
		mv.visitEnd();
	}
	
	
	private void createGetValueMethod(ClassWriter cw,Class<?> beanClaz,String methodName,Class<?> returnClass,Field field){
		MethodVisitor mv = this.createGetMethodVisitor(cw, methodName, returnClass);
		String asmClassName=AsmUtils.toAsmCls(beanClaz);
		Class<?> fieldClass=field.getType();
		if(AsmUtils.canThrowException(returnClass, fieldClass)){
			this.createThrowException(mv);
			mv.visitMaxs(3,2);
		}else{
			mv.visitVarInsn(ALOAD, 1);
			mv.visitTypeInsn(CHECKCAST, asmClassName);
			mv.visitFieldInsn(GETFIELD, asmClassName, field.getName(), Type.getDescriptor(fieldClass));
			mv.visitInsn(AsmUtils.getReturnTag(returnClass));
			if(AsmUtils.needMoreStack(returnClass)){
				mv.visitMaxs(2, 2);
			}else{
				mv.visitMaxs(1, 2);
			}
		}
		mv.visitEnd();
	}

	private void createNormalGetValueMethod(ClassWriter cw,Class<?> beanClaz,Field field){
		MethodVisitor mv = this.createGetMethodVisitor(cw, "get", Object.class);
		String asmClassName=AsmUtils.toAsmCls(beanClaz);
		Class<?> fieldClass=field.getType();
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, asmClassName);
		mv.visitFieldInsn(GETFIELD, asmClassName, field.getName(), Type.getDescriptor(fieldClass));
		if(fieldClass.isPrimitive()){
			Class<?> wrapperClass = AsmUtils.convert2WrapperClass(fieldClass);
			mv.visitMethodInsn(INVOKESTATIC, AsmUtils.toAsmCls(wrapperClass), 
					"valueOf", AsmUtils.getMethodDesc(wrapperClass, fieldClass),false);
		}
		mv.visitInsn(AsmUtils.getReturnTag(Object.class));
		if(AsmUtils.needMoreStack(fieldClass)){
			mv.visitMaxs(2, 2);
		}else{
			mv.visitMaxs(1, 2);
		}
		mv.visitEnd();
	}

}
