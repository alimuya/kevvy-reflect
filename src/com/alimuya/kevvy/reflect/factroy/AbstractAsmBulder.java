package com.alimuya.kevvy.reflect.factroy;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alimuya.kevvy.reflect.exception.FieldReflectException;

/**
 * @author ov_alimuya
 *
 */
public abstract class AbstractAsmBulder implements Opcodes{
	
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
}
