package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.alimuya.kevvy.reflect.KevvyConstructor;
import com.alimuya.kevvy.reflect.exception.ConstructorReflectException;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.sun.xml.internal.ws.org.objectweb.asm.Type;

/**
 * @author ov_alimuya
 *
 */
public class ConstructorBuilder implements Opcodes{
	private Class<?> beanClass;
	private static AtomicInteger id=new AtomicInteger(0);
	public ConstructorBuilder (Class<?> claz) {
		this.beanClass=claz;
	}
	
	@SuppressWarnings("unchecked")
	public <T> KevvyConstructor<T> build(Constructor<T> constructor) throws Exception{
		if((constructor.getModifiers() & Modifier.PUBLIC)!=Modifier.PUBLIC){
			return new JavaOriginalReflectConstructor<T>();
		}
		Class<?>[] pClasses = constructor.getParameterTypes();
		String newClassName=this.createNewClassName(pClasses);
		ClassWriter classWriter = AsmClassBuilder.build(newClassName,KevvyConstructor.class,getSignature());
		String beanClassAsmName = AsmUtils.toAsmCls(beanClass);
		String methodName="_newInstance";
		this.createMethod(classWriter, methodName, beanClassAsmName,pClasses);
		this.createBridgeMethod(classWriter, methodName, newClassName);
		classWriter.visitEnd();
		byte[] bytes = classWriter.toByteArray();
		KevvyConstructor<T> result = AsmUtils.asmNewInstance(KevvyConstructor.class, newClassName, bytes);
		return result;
	}

	private void createMethod(ClassWriter classWriter,String methodName,String beanClassAsmName,Class<?>[] argsClasses){
		MethodVisitor mv = classWriter.visitMethod(ACC_PROTECTED + ACC_VARARGS, methodName, 
				AsmUtils.getMethodDesc(beanClass,Object[].class), null, null);
		mv.visitCode();
		int vMax=2;
		int oMax=2;
		int length=argsClasses.length;
		for (int i = 0; i < length; i++) {
			Class<?> argClass=argsClasses[i];
			argsConvertType(mv, argClass, i);
			if(AsmUtils.needMoreStack(argClass)){
				vMax++;
			}
			vMax++;
			oMax++;
		}
		newBean(mv, beanClassAsmName);
		for (int i = 0; i < length; i++) {
			Class<?> argClass=argsClasses[i];
			popArg(mv, argClass, i);
		}
		mv.visitMethodInsn(INVOKESPECIAL, beanClassAsmName, "<init>", AsmUtils.getMethodDesc(void.class, argsClasses), false);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(vMax, oMax);
		mv.visitEnd();
	}
	
	private void newBean(MethodVisitor mv,String beanClassAsmName){
		mv.visitTypeInsn(NEW, beanClassAsmName);
		mv.visitInsn(DUP);
	}
	
	private void popArg(MethodVisitor mv,Class<?> argClass,int index){
		mv.visitVarInsn(ALOAD, index+2);
		if(argClass.isPrimitive()){
			Class<?> wrapperClass=AsmUtils.convert2WrapperClass(argClass);
			mv.visitMethodInsn(INVOKEVIRTUAL, AsmUtils.toAsmCls(wrapperClass),
					argClass.getSimpleName()+"Value", "()"+Type.getDescriptor(argClass), false);
		}
	}
	
	private void argsConvertType(MethodVisitor mv,Class<?> argClass,int index){
		Class<?> requestClass=argClass;
		if(requestClass.isPrimitive()){
			requestClass=AsmUtils.convert2WrapperClass(requestClass);
		}
		mv.visitVarInsn(ALOAD, 1);
		if(index<=5){
			mv.visitInsn(AsmUtils.getArrayIndexIConstOpcode(index));
		}else{
			mv.visitIntInsn(BIPUSH, index);
		}
		mv.visitInsn(AALOAD);
		if(requestClass!=Object.class){
			mv.visitTypeInsn(CHECKCAST, AsmUtils.toAsmCls(requestClass));
		}
		mv.visitVarInsn(ASTORE, index+2);
	}
	
	private void createBridgeMethod(ClassWriter classWriter,String methodName,String newClassName){
		MethodVisitor mv = classWriter.visitMethod(ACC_PROTECTED + ACC_BRIDGE + ACC_VARARGS + ACC_SYNTHETIC, 
				methodName,AsmUtils.getMethodDesc(Object.class,Object[].class), null,
				new String [] {AsmUtils.toAsmCls(ConstructorReflectException.class), AsmUtils.toAsmCls(InvokeTargetException.class)});
		mv.visitCode();
		mv.visitVarInsn(ALOAD, 0);
		mv.visitVarInsn(ALOAD, 1);
		mv.visitMethodInsn(INVOKEVIRTUAL, AsmUtils.toAsmCls(newClassName), methodName,
				AsmUtils.getMethodDesc(beanClass,Object[].class), false);
		mv.visitInsn(ARETURN);
		mv.visitMaxs(2, 2);
		mv.visitEnd();
	}
	
	private String getSignature(){
		return "L"+AsmUtils.toAsmCls(KevvyConstructor.class)+"<"+Type.getDescriptor(beanClass)+">;";
	}
	
	private String createNewClassName(Class<?>[] pClasses) {
		String className=beanClass.getCanonicalName()+"_autoConstructor_"+"_"+id.getAndIncrement();
		return className;
	}
}
