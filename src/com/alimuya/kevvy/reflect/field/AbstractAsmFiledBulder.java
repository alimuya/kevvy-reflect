package com.alimuya.kevvy.reflect.field;

import java.lang.reflect.Field;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alimuya.kevvy.reflect.exception.FieldReflectException;
import com.alimuya.kevvy.reflect.utils.AsmUtils;

/**
 * @author ov_alimuya
 *
 */
abstract class AbstractAsmFiledBulder implements Opcodes{
	private static AtomicInteger id=new AtomicInteger(0);
	
	protected void createThrowException(MethodVisitor mv){
		String exceptionAsmName = AsmUtils.toAsmCls(FieldReflectException.class);
		mv.visitTypeInsn(NEW, exceptionAsmName);
		mv.visitInsn(DUP);
		mv.visitLdcInsn("class type error!");
		mv.visitMethodInsn(INVOKESPECIAL, exceptionAsmName, "<init>", "(Ljava/lang/String;)V", false);
		mv.visitInsn(ATHROW);
	}
	
	protected MethodVisitor createGetMethodVisitor(ClassWriter cw,String methodName,Class<?> returnClass){
		return AsmUtils.createMethodVisitor(cw, methodName, new Class<?>[]{Object.class}, returnClass, new Class<?>[]{FieldReflectException.class});
	}
	
	protected MethodVisitor createSetMethodVisitor(ClassWriter cw,String methodName,Class<?> argClass){
		return AsmUtils.createMethodVisitor(cw, methodName, new Class<?>[]{Object.class,argClass}, null, new Class<?>[]{FieldReflectException.class});
	}
	
	protected String createNewClassName(Class<?> beanClass,Field field){
		String className=beanClass.getName()+"_autofield_"+field.getName()+"_"+id.getAndIncrement();
		return className;
	}
}
