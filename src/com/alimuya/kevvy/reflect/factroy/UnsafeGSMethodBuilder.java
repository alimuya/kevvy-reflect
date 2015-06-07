package com.alimuya.kevvy.reflect.factroy;

import java.lang.reflect.Field;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

/**
 * @author ov_alimuya
 *
 */
class UnsafeGSMethodBuilder extends AbstractAsmBulder implements IGSMethodBuilder{
	private ClassWriter cw;
	private Field field;
	private String className;
	private long offset;
	UnsafeGSMethodBuilder(String className,ClassWriter cw,Field field){
		this.cw=cw;
		this.field=field;
		this.className=className;
		this.offset=UnsafeFactory.getUnsafe().objectFieldOffset(field);
		addUnsafeStaticField();
	}
	
	private void addUnsafeStaticField(){
		FieldVisitor fv = cw.visitField(ACC_PRIVATE + ACC_STATIC, "unsafe",
				"Lsun/misc/Unsafe;", null, null);
		fv.visitEnd();
		MethodVisitor mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);
		mv.visitCode();
		mv.visitMethodInsn(INVOKESTATIC, AsmUtils.toAsmCls(UnsafeFactory.class),
				"getUnsafe", "()Lsun/misc/Unsafe;", false);
		mv.visitFieldInsn(PUTSTATIC, AsmUtils.toAsmCls(className), "unsafe","Lsun/misc/Unsafe;");
		mv.visitInsn(RETURN);
		mv.visitMaxs(1, 0);
		mv.visitEnd();
	}
	
	@Override
	public void addGetters() {
		this.createNormalGetValueMethod();
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

	@Override
	public void addSetters() {
		this.createSetValueMethod("setInt", int.class);
		this.createSetValueMethod("setChar", char.class);
		this.createSetValueMethod("setShort", short.class);
		this.createSetValueMethod("setByte", byte.class);
		this.createSetValueMethod("_setObject", Object.class);
		this.createSetValueMethod("setLong", long.class);
		this.createSetValueMethod("setFloat", float.class);
		this.createSetValueMethod("setDouble", double.class);
		this.createSetValueMethod("setBoolean", boolean.class);
		
	}
	
	private void createSetValueMethod(String methodName,Class<?> argClass){
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
			String unsafeMethodName;
			if(argClass==Object.class){
				unsafeMethodName = "put"+methodName.substring(4);
			}else{
				unsafeMethodName = "put"+methodName.substring(3);
			}
			mv.visitFieldInsn(GETSTATIC, AsmUtils.toAsmCls(className), "unsafe", "Lsun/misc/Unsafe;");
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn(new Long(offset));
			mv.visitVarInsn(AsmUtils.getLoadTag(argClass), 2);
			String unsafeDescriptor=AsmUtils.getMethodDesc(void.class,Object.class,long.class,argClass);
			if(argClass==Object.class && fieldClass!=argClass){
				mv.visitTypeInsn(CHECKCAST, AsmUtils.toAsmCls(fieldClass));
			}
			mv.visitMethodInsn(INVOKEVIRTUAL, "sun/misc/Unsafe", unsafeMethodName, unsafeDescriptor, false);
			mv.visitInsn(RETURN);
			if(AsmUtils.needMoreStack(argClass)){
				mv.visitMaxs(6, 4);
			}else{
				mv.visitMaxs(5, 3);
			}
		}
		mv.visitEnd();
	}
	
	private void createGetValueMethod(String methodName,Class<?> returnClass){
		MethodVisitor mv = this.createGetMethodVisitor(cw,methodName, returnClass);
		if(AsmUtils.canThrowException(returnClass,field.getType())){
			this.createThrowException(mv);
			mv.visitMaxs(3, 2);
		}else{
			mv.visitFieldInsn(GETSTATIC, AsmUtils.toAsmCls(className), "unsafe", "Lsun/misc/Unsafe;");
			mv.visitVarInsn(ALOAD, 1);
			mv.visitLdcInsn(new Long(offset));
			String unsafeDescriptor=AsmUtils.getMethodDesc(returnClass, Object.class,long.class);
			mv.visitMethodInsn(INVOKEVIRTUAL, "sun/misc/Unsafe", methodName, unsafeDescriptor, false);
			mv.visitInsn(AsmUtils.getReturnTag(returnClass));
			mv.visitMaxs(4, 2);
		}
		mv.visitEnd();
	}
	
	
	private void createNormalGetValueMethod(){
		MethodVisitor mv = this.createGetMethodVisitor(cw,"get", Object.class);
		mv.visitFieldInsn(GETSTATIC, AsmUtils.toAsmCls(className), "unsafe", "Lsun/misc/Unsafe;");
		mv.visitVarInsn(ALOAD, 1);
		mv.visitLdcInsn(new Long(offset));
		Class<?> fieldClass = field.getType();
		if(fieldClass.isPrimitive()){
			Class<?> wrapper=AsmUtils.convert2WrapperClass(fieldClass);
			String methodName="get"+wrapper.getSimpleName();
			String unsafeDescriptor=AsmUtils.getMethodDesc(fieldClass, Object.class,long.class);
			mv.visitMethodInsn(INVOKEVIRTUAL, "sun/misc/Unsafe", methodName, unsafeDescriptor, false);
			mv.visitMethodInsn(INVOKESTATIC, AsmUtils.toAsmCls(wrapper), "valueOf", 
					AsmUtils.getMethodDesc(wrapper, fieldClass), false);
		}else{
			String unsafeDescriptor=AsmUtils.getMethodDesc(Object.class, Object.class,long.class);
			mv.visitMethodInsn(INVOKEVIRTUAL, "sun/misc/Unsafe", "getObject", unsafeDescriptor, false);
		}
		mv.visitInsn(AsmUtils.getReturnTag(Object.class));
		mv.visitMaxs(4, 2);
	}

}
