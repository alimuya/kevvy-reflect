package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Field;
import java.lang.reflect.Member;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

import com.alimuya.kevvy.reflect.IReflectObjectBuilder;
import com.alimuya.kevvy.reflect.KevvyField;
import com.alimuya.kevvy.reflect.utils.AsmUtils;
import com.alimuya.kevvy.reflect.utils.ReflectUtils;

/**
 * @author ov_alimuya
 *
 */
class DirectFieldAsmBuilder extends AbstractAsmFiledBulder 
implements IAddGetSetable,IReflectObjectBuilder<KevvyField>{
	
	private ClassWriter cw;
	private Field field;
	private Class<?> beanClass;

	@Override
	public boolean isSuitable(Member member) {
		return ReflectUtils.isNotPrivate(member) && !ReflectUtils.isStatic(member);
	}


	@Override
	public KevvyField build(Class<?> beanClass, Member member) throws Exception {
		this.field =(Field)member;
		String newClassName=this.createNewClassName(beanClass,field);
		this.cw = AsmUtils.buildClass(newClassName,KevvyField.class);
		this.beanClass=beanClass;
		addGetters();
		addSetters();
		cw.visitEnd();
		byte[] bytes = cw.toByteArray();
		return AsmUtils.asmNewInstance(beanClass,KevvyField.class, newClassName, bytes);
	}
	
	
	@Override
	public void addGetters() {
		this.createNormalGetValueMethod(cw, beanClass, field);
		this.createGetValueMethod(cw, beanClass, "getInt", int.class, field);
		this.createGetValueMethod(cw, beanClass, "getChar", char.class, field);
		this.createGetValueMethod(cw, beanClass, "getShort", short.class, field);
		this.createGetValueMethod(cw, beanClass, "getByte", byte.class, field);
//		this.createGetValueMethod(cw, clazz, "getObject", Object.class, field);
		this.createGetValueMethod(cw, beanClass, "getLong", long.class, field);
		this.createGetValueMethod(cw, beanClass, "getFloat", float.class, field);
		this.createGetValueMethod(cw, beanClass, "getDouble", double.class, field);
		this.createGetValueMethod(cw, beanClass, "getBoolean", boolean.class, field);
		
	}

	@Override
	public void addSetters() {
		this.createSetValueMethod(cw, beanClass, "setInt", int.class, field);
		this.createSetValueMethod(cw, beanClass, "setChar", char.class, field);
		this.createSetValueMethod(cw, beanClass, "setShort", short.class, field);
		this.createSetValueMethod(cw, beanClass, "setByte", byte.class, field);
		this.createSetValueMethod(cw, beanClass, "_setObject", Object.class, field);
		this.createSetValueMethod(cw, beanClass, "setLong", long.class, field);
		this.createSetValueMethod(cw, beanClass, "setFloat", float.class, field);
		this.createSetValueMethod(cw, beanClass, "setDouble", double.class, field);
		this.createSetValueMethod(cw, beanClass, "setBoolean", boolean.class, field);
		
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
