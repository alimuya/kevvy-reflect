package com.alimuya.kevvy.reflect;

import java.lang.reflect.AccessibleObject;

/**
 * @author ov_alimuya
 *
 * @param <T>
 */
public abstract class AbstractReflectObject<T extends AccessibleObject> {
	protected T original;

	public T getOriginal() {
		return original;
	}

	public void setOriginal(T original) {
		if(!original.isAccessible()){
			original.setAccessible(true);
		}
		this.original = original;
	}

}
