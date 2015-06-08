package com.alimuya.kevvy.reflect.utils;

import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

/**
 * @author ov_alimuya
 *
 */
public final class AsmUtils extends ClassLoader implements Opcodes{
	private static AtomicInteger id=new AtomicInteger(0);
	private static Method classLoaderMethod;
//	private static class AsmClassLoader extends ClassLoader {
//		public Class<?> selfDefineClass(String className,byte[] bytes){
//			return this.defineClass(className, bytes, 0, bytes.length);
//		}
//		
//	};
	
	static{
		try {
			classLoaderMethod=ClassLoader.class.getDeclaredMethod("defineClass", String.class,byte[].class,int.class,int.class);
			classLoaderMethod.setAccessible(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public static  int getArrayIndexIConstOpcode(int index){
		switch (index) {
		case 0:
			return ICONST_0;
		case 1:
			return ICONST_1;
		case 2:
			return ICONST_2;
		case 3:
			return ICONST_3;
		case 4:
			return ICONST_4;
		case 5:
			return ICONST_5;
		default:
			return 0;
		}
	}
	
	public static  Class<?> convert2WrapperClass(Class <?>claz){
		if(claz==boolean.class){
			return Boolean.class;
		}else if(claz==byte.class){
			return Byte.class;
		}else if(claz==char.class){
			return Character.class;
		}else if(claz==short.class){
			return Short.class;
		}else if(claz==int.class){
			return Integer.class;
		}else if(claz==long.class){
			return Long.class;
		}else if(claz==float.class){
			return Float.class;
		}else if(claz==double.class){
			return Double.class;
		}else{
			return Void.class;
		}
	}
	
//	private static AsmClassLoader classLoader=new AsmClassLoader();
	public static String getNewClassNameByFieldName(Class<?> srcClaz,String fieldName){
		String packageName = srcClaz.getPackage().getName();
		String className=packageName+"."+srcClaz.getSimpleName()+"_"+fieldName+"_asm_"+id.getAndIncrement();
		return className;
	}
	
	public static int getJDKVersionTag(){
		String version = System.getProperty("java.version");
		if(version.startsWith("1.7")){
			return V1_7;
		}else if(version.startsWith("1.8")){
			return V1_8;
		}else if(version.startsWith("1.6")){
			return V1_6;
		}else if(version.startsWith("1.5")){
			return V1_5;
		}else{
			return V1_7;
		}
	}
	
	public static ClassWriter buildClass(String newClassName,Class<?> superClass){
		return buildClass(newClassName, superClass, null);
	}
	
	public static ClassWriter buildClass(String newClassName,Class<?> superClass,String signature){
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
	
	/**
	 * @param cw
	 * @param methodName
	 * @param argsClass    empty == null or empty array
	 * @param returnClass  void ==null
	 * @param exceptionsClass   empty == null or empty array
	 * @return 
	 */
	public static  MethodVisitor createMethodVisitor(ClassWriter cw,String methodName,Class<?>[] argsClass,Class<?> returnClass, Class<?>[] exceptionsClass){
		String methodDescriptor = getMethodDesc(returnClass,argsClass);
		String[] exceptions=null;
		if(exceptionsClass!=null && exceptionsClass.length>0){
			exceptions=new String [exceptionsClass.length];
			for (int i = 0; i < exceptions.length; i++) {
				exceptions[i]=AsmUtils.toAsmCls(exceptionsClass[i]);
			}
		}
		MethodVisitor mv = cw.visitMethod(ACC_PUBLIC, methodName, methodDescriptor,null, exceptions);
		mv.visitCode();
		return mv;
	}
	
	
	
	public static String getNewClassName(Class<?> srcClaz){
		String packageName = srcClaz.getPackage().getName();
		String className=packageName+"."+srcClaz.getSimpleName()+"_asm_"+id.getAndIncrement();
		return className;
	}
	
	public static boolean needMoreStack(Class<?> claz){
		return claz==double.class || claz==long.class;
	}
	

	public static int getLoadTag(Class<?> claz){
		if(claz==double.class){
			return DLOAD;
		}else if(claz==Object.class){
			return ALOAD;
		}else if(claz==float.class){
			return FLOAD;
		}else if(claz==long.class){
			return LLOAD;
		}else{
			return ILOAD;
		}
	}
	
	public static boolean canThrowException (Class<?> expectClass, Class<?> fieldClass){
		if(fieldClass==expectClass){
			return false;
		}
		if(expectClass==Object.class && !fieldClass.isPrimitive()){
			return false;
		}
		return true;
	}
	
	public static int getReturnTag(Class<?> claz){
		if(claz.isPrimitive()){
			if(claz==double.class){
				return DRETURN;
			}else if(claz==float.class){
				return FRETURN;
			}else if(claz==long.class){
				return LRETURN;
			}else if(claz==void.class){
				return RETURN;
			}else{
				return IRETURN;
			}
		}else{
			return ARETURN;
		}
	}
	
	public static String toAsmCls(String className){
		return className.replaceAll("\\.", "/");
	}
	
	public static String toAsmCls(Class<?> claz){
		return Type.getInternalName(claz);
	}
	
	@SuppressWarnings("unchecked")
	public static<T> T asmNewInstance(Class<?> beanClass,Class<T> superClaz,String className,byte[] bytes) throws Exception{
		try {
//			Class<T> newClass = (Class<T>)classLoader.selfDefineClass(className, bytes);
			Class<T> newClass = (Class<T>)classLoaderMethod.invoke(beanClass.getClassLoader(), className, bytes, 0, bytes.length);
			return  (T) newClass.newInstance();
			
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	public static String getMethodDesc(Class<?> returnClass, Class<?> ...argsClass){
		StringBuilder sb=new StringBuilder();
		if(argsClass==null || argsClass.length==0){
			sb.append("()");
		}else{
			sb.append('(');
			for (int i = 0; i < argsClass.length; i++) {
				sb.append(Type.getDescriptor(argsClass[i]));
			}
			sb.append(')');
		}
		if(returnClass==null){
			sb.append(Type.getDescriptor(void.class));
		}else{
			sb.append(Type.getDescriptor(returnClass));
		}
		return sb.toString();
	}
}
