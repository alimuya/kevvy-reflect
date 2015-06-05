package com.alimuya.kevvy.reflect.factroy;
import java.lang.reflect.Field;

import sun.misc.Unsafe;
/**
 * @author ov_alimuya
 *
 */
public class UnsafeFactory {
	private static Unsafe unsafe;
	static {
		getUnsafe();
	}
	public static Unsafe getUnsafe() {
		if(unsafe==null){
			try {
	            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
	            theUnsafe.setAccessible(true);
	            unsafe = (Unsafe) theUnsafe.get(null);
	        } catch (Exception e) {
	            throw new AssertionError(e);
	        }
		}
		return unsafe;
	}
}
