package com.alimuya.kevvy.reflect.utils;

import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

/**
 * @author ov_alimuya
 *
 */
public class ReflectUtils {
	
	public static boolean isNotPrivate(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.PRIVATE) !=Modifier.PRIVATE;
	}
	
	public static boolean isStatic(Member member){
		int modifiers = member.getModifiers();
		return (modifiers & Modifier.STATIC) ==Modifier.STATIC;
	}
	
}
