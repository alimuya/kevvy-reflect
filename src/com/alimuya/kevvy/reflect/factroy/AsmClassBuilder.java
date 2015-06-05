package com.alimuya.kevvy.reflect.factroy;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * @author ov_alimuya
 *
 */
public class AsmClassBuilder implements Opcodes{
	
	public static ClassWriter build(String newClassName,Class<?> superClass){
		return AsmClassBuilder.build(newClassName, superClass, null);
	}
	
	public static ClassWriter build(String newClassName,Class<?> superClass,String signature){
		String superName4Asm = AsmUtils.toAsmCls(superClass);
		ClassWriter cw = new ClassWriter(0);
		MethodVisitor mv;
		cw.visit(AsmUtils.getJDKVersionTag(), ACC_PUBLIC + ACC_SUPER, AsmUtils.toAsmCls(newClassName), signature, superName4Asm, null);
		{
			mv = cw.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
			mv.visitCode();
			mv.visitVarInsn(ALOAD, 0);
			mv.visitMethodInsn(INVOKESPECIAL, superName4Asm, "<init>", "()V", false);
			mv.visitInsn(RETURN);
			mv.visitMaxs(1, 1);
			mv.visitEnd();
		}
		return cw;
	}
}
