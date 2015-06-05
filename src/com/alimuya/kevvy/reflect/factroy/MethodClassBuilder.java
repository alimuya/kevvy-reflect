package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

import com.alimuya.kevvy.reflect.KevvyMethod;
import com.alimuya.kevvy.reflect.exception.InvokeTargetException;
import com.alimuya.kevvy.reflect.exception.MethodReflectException;

/**
 * @author ov_alimuya
 *
 */
public class MethodClassBuilder implements Opcodes{
	private Class<?> beanClass;
	private static AtomicInteger id=new AtomicInteger(0);
	
	public MethodClassBuilder (Class<?> beanClass){
		this.beanClass=beanClass;
	}
	
	private String createNewClassName(Method method){
		String packageName = beanClass.getPackage().getName();
		String className=packageName+"."+beanClass.getSimpleName()+"_autoMethod_"+method.getName()+"_"+id.getAndIncrement();
		return className;
	}
	
	
	public KevvyMethod build(Method method) throws Exception{
		KevvyMethod result;
		if(!this.isStaticMethod(method) && isPublicMethod(method)){
			String newClassName=this.createNewClassName(method);
			ClassWriter classWriter = AsmClassBuilder.build(newClassName,KevvyMethod.class);
			createInvokeMethod(classWriter,beanClass,method);
			classWriter.visitEnd();
			byte[] bytes = classWriter.toByteArray();
			result = AsmUtils.asmNewInstance(KevvyMethod.class, newClassName, bytes);
		}else{
			result=new JavaOriginalReflectMethod();
		}
		return result;
	}
	
	
	public int countExtraVmax(Class<?>[] argsClass,Class<?> returnClass){
		int count=0;
		for (int i = 0; i < argsClass.length; i++) {
			Class<?> claz = argsClass[i];
			if(claz==long.class || claz==double.class){
				count++;
			}
		}
		if(count>=1){
			return count;
		}else{
			if(returnClass==long.class || returnClass==double.class){
				count++;
			}
		}
		return count;
	}
//	
	public void createInvokeMethod(ClassWriter cw,Class<?> beanClass, Method method){
		String name4asm=AsmUtils.toAsmCls(beanClass);
		Class<?> returnClass = method.getReturnType();
		Class<?>[] argsClass = method.getParameterTypes();
		String methodDescriptor=AsmUtils.getMethodDesc(returnClass, argsClass);
		int vMax=1+countExtraVmax(argsClass,returnClass);
		int oMax=4;
		MethodVisitor mv = cw.visitMethod(ACC_PROTECTED + ACC_VARARGS, "_invoke", 
				"(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", null,
				new String []{AsmUtils.toAsmCls(InvokeTargetException.class),AsmUtils.toAsmCls(MethodReflectException.class)});
		mv.visitCode();
		this.addConvertBeanClassType(mv, name4asm);
		for (int i = 0; i < argsClass.length; i++) {
			Class<?> requestClass = argsClass[i];
			if(requestClass.isPrimitive()){
				requestClass=AsmUtils.convert2WrapperClass(requestClass);
			}
			addConvertArgsType(mv, requestClass, i);
		}
		mv.visitVarInsn(ALOAD, 3);
		for (int i = 0; i < argsClass.length; i++) {
			mv.visitVarInsn(ALOAD, i+4);
			vMax++;
			oMax++;
			Class<?> requestClass = argsClass[i];
			if(requestClass.isPrimitive()){
				Class<?> wrapperClass = AsmUtils.convert2WrapperClass(requestClass);
				mv.visitMethodInsn(INVOKEVIRTUAL, AsmUtils.toAsmCls(wrapperClass), 
						requestClass.getSimpleName()+"Value", 
						"()"+Type.getDescriptor(requestClass), false);
			}
		}
		mv.visitMethodInsn(INVOKEVIRTUAL, name4asm, method.getName(), methodDescriptor, false);
		if(returnClass==void.class){
			mv.visitInsn(ACONST_NULL);
		}else if(returnClass.isPrimitive()){
			Class<?> wrapperClass = AsmUtils.convert2WrapperClass(returnClass);
			mv.visitMethodInsn(INVOKESTATIC, AsmUtils.toAsmCls(wrapperClass), "valueOf",
					"("+Type.getDescriptor(returnClass)+")"+Type.getDescriptor(wrapperClass), false);
		}
		mv.visitInsn(ARETURN);
		mv.visitMaxs(vMax, oMax);
		mv.visitEnd();
	}
	
	private void addConvertArgsType(MethodVisitor mv,Class<?> requestClass, int index){
		mv.visitVarInsn(ALOAD, 2);
		if(index<=5){
			mv.visitInsn(AsmUtils.getArrayIndexIConstOpcode(index));
		}else{
			mv.visitIntInsn(BIPUSH, index);
		}
		mv.visitInsn(AALOAD);
		if(requestClass!=Object.class){
			mv.visitTypeInsn(CHECKCAST, AsmUtils.toAsmCls(requestClass));
		}
		mv.visitVarInsn(ASTORE, index+4);
	}
	
	
	private void addConvertBeanClassType(MethodVisitor mv,String className4asm){
		mv.visitVarInsn(ALOAD, 1);
		mv.visitTypeInsn(CHECKCAST, className4asm);
		mv.visitVarInsn(ASTORE, 3);
	}
	
	
	private  boolean isStaticMethod(Method method){
		return (method.getModifiers() & Modifier.STATIC)==Modifier.STATIC;
	}
	
	private  boolean isPublicMethod(Method method){
		return (method.getModifiers() & Modifier.PUBLIC)==Modifier.PUBLIC;
	}
	
}
