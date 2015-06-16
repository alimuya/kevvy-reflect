package com.alimuya.kevvy.reflect.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * @author ov_alimuya
 *
 */
public final class ReflectUtils {
	
	public static boolean isNotPrivate(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.PRIVATE) !=Modifier.PRIVATE;
	}
	
	public static boolean isPrivate(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.PRIVATE) ==Modifier.PRIVATE;
	}
	
	public static boolean isStatic(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.STATIC) ==Modifier.STATIC;
	}
	
	
	public static boolean isVolatile(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.VOLATILE) ==Modifier.VOLATILE;
	}
}
