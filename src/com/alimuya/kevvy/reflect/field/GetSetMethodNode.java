package com.alimuya.kevvy.reflect.field;

import org.objectweb.asm.tree.MethodNode;

/**
 * @author ov_alimuya
 *
 */
public final class GetSetMethodNode {
	private MethodNode getter;
	private MethodNode setter;
	
	public MethodNode getGetter() {
		return getter;
	}
	void setGetter(MethodNode getter) {
		this.getter = getter;
	}
	
	public MethodNode getSetter() {
		return setter;
	}
	
	void setSetter(MethodNode setter) {
		this.setter = setter;
	}
	
	
	
}
