package com.alimuya.kevvy.reflect.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.LineNumberNode;
import org.objectweb.asm.tree.MethodNode;

/**
 * @author ov_alimuya
 *
 */
public class BeanAnalyzer implements Opcodes{
	private Class<?> claz;
	
	public BeanAnalyzer(Class<?> claz){
		this.claz=claz;
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,GetSetMethodNode> getFieldGetSetMethodMap(){
		Map<String,GetSetMethodNode> map=new HashMap<String, GetSetMethodNode>();
		try {
			ClassNode cn = new ClassNode();
			ClassReader cr = new ClassReader(this.claz.getCanonicalName());
			cr.accept(cn, 0);
			List<MethodNode> methods = cn.methods;
			for (int i = 0; i < methods.size(); i++) {
				MethodNode method = methods.get(i);
				if( isPublic(method)&& noConstructor(method) && isSmallMethod(method)){
					ArrayList<AbstractInsnNode> list = getInstructions(method);
					String fieldName;
					fieldName=isGetterMethodAndGetFieldName(method,list, claz);
					if(fieldName!=null){
						setMethod2Map(map, fieldName, method, true);
						continue;
					}
					fieldName=isSetterMethodAndGetFieldName(method,list, claz);
					if(fieldName!=null){
						setMethod2Map(map, fieldName, method, false);
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private void setMethod2Map(Map<String,GetSetMethodNode> map,String fieldName,MethodNode method,boolean isGetter){
		GetSetMethodNode gsnode = map.get(fieldName);
		if(gsnode==null){
			gsnode=new GetSetMethodNode();
			map.put(fieldName, gsnode);
		}
		if(isGetter){
			gsnode.setGetter(method);
		}else{
			gsnode.setSetter(method);
		}
	}
	
	@SuppressWarnings("unchecked")
	private ArrayList<AbstractInsnNode> getInstructions(MethodNode method){
		InsnList insts = method.instructions;
		ListIterator <AbstractInsnNode>it = insts.iterator();
		ArrayList<AbstractInsnNode> list = new ArrayList<AbstractInsnNode>();
		while (it.hasNext()) {
			AbstractInsnNode node = it.next();
			if (!isIgnoreNode(node)) {
				list.add(node);
			}
		}
		return list;
	}
	
	private String isSetterMethodAndGetFieldName(MethodNode method,ArrayList<AbstractInsnNode> list, Class<?> claz){
		if (list.size() == 4) {
			if (AnalyzerUtils.isThisPointerLoadNode(list.get(0))
					&& AnalyzerUtils.isDataLoadNode(list.get(1))
					&& AnalyzerUtils.isReturnNode(list.get(3), true)) {
				AbstractInsnNode thirdNode = list.get(2);
				if (AnalyzerUtils.isThisFieldSet(thirdNode, claz,method.desc)) {
					return ((FieldInsnNode) thirdNode).name;
				}
			}
		}
		return null;
	}
	
	private String isGetterMethodAndGetFieldName(MethodNode method,ArrayList<AbstractInsnNode> list, Class<?> claz) {
		if (list.size() == 3) {
			if (AnalyzerUtils.isThisPointerLoadNode(list.get(0))
					&& AnalyzerUtils.isReturnNode(list.get(2), false)) {
				AbstractInsnNode secendNode = list.get(1);
				if (AnalyzerUtils.isThisFieldGet(secendNode, claz,method.desc)) {
					return ((FieldInsnNode) secendNode).name;
				}
			}
		}
		return null;
	}
	
	private  boolean isPublic(MethodNode method){
		boolean publicTag = ((method.access&ACC_PUBLIC)==ACC_PUBLIC);
		boolean noStaticTag=((method.access&ACC_STATIC)!=ACC_STATIC); 
		return publicTag&&noStaticTag;
	}
	
	private  boolean noConstructor(MethodNode method){
		return !"<init>".equals(method.name);
	}
	
	private  boolean isIgnoreNode(AbstractInsnNode node){
		return (node instanceof LabelNode) || (node instanceof LineNumberNode);
	}
	
	private  boolean isSmallMethod(MethodNode method){
		return method.instructions!=null && method.instructions.size()<10;
	}
}
