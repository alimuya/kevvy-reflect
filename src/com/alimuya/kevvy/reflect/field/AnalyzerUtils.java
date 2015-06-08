package com.alimuya.kevvy.reflect.field;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import com.alimuya.kevvy.reflect.utils.AsmUtils;

/**
 * @author ov_alimuya
 *
 */
class AnalyzerUtils implements Opcodes{
	static boolean isReturnNode(AbstractInsnNode node,boolean isVoid){
		if(node instanceof InsnNode){
			InsnNode var = (InsnNode)node;
			int opcode = var.getOpcode();
			if(isVoid){
				return isVoidReturnOpcode(opcode);
			}else{
				return isDataReturnOpcode(opcode);
			}
		}
		return false;
	}
	static boolean isThisFieldGet(AbstractInsnNode node,Class<?> claz,String methodDesc){
		return isThisFieldOperate(node, claz, true,methodDesc);
	}
	
	static boolean isThisFieldSet(AbstractInsnNode node,Class<?> claz,String methodDesc){
		return isThisFieldOperate(node, claz, false,methodDesc);
	}
	
	private static boolean isThisFieldOperate(AbstractInsnNode node,Class<?> claz,boolean isGet,String methodDesc){
		if(node instanceof FieldInsnNode){
			FieldInsnNode feildNode=(FieldInsnNode)node;
			boolean isGetDesc = methodDesc!=null && methodDesc.equals("()"+feildNode.desc);
			boolean isSetDesc = methodDesc!=null && methodDesc.equals("("+feildNode.desc+")V");
			if((isGet && isGetDesc) || (!isGet && isSetDesc)){
				int opcode=isGet?GETFIELD:PUTFIELD;
				if(feildNode.getOpcode()==opcode){
					String owner = feildNode.owner;
					String classname = AsmUtils.toAsmCls(claz);
					return classname.equals(owner);
				}
			}
		}
		return false;
	}
	
	static boolean isThisPointerLoadNode(AbstractInsnNode node){
		if(node instanceof VarInsnNode){
			VarInsnNode var = (VarInsnNode)node;
			return var.getOpcode()==ALOAD && var.var==0;
		}
		return false;
	}
	
	static boolean isDataLoadNode(AbstractInsnNode node){
		if(node instanceof VarInsnNode){
			VarInsnNode var = (VarInsnNode)node;
			return isNormalLoadOpcode(var.getOpcode()) && var.var==1;
		}
		return false;
	}
	
	private static boolean isDataReturnOpcode(int opcode){
		return opcode==ARETURN || opcode==IRETURN || opcode==FRETURN || opcode==DRETURN || opcode==LRETURN ;
	}
	
	
	private static boolean isVoidReturnOpcode(int opcode){
		return opcode==RETURN;
	}
	
	private static boolean isNormalLoadOpcode(int opcode){
		return opcode==ILOAD || opcode==ALOAD || opcode==FLOAD || opcode==DLOAD || opcode==LLOAD;
	}
}
