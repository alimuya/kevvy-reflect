package com.alimuya.kevvy.reflect;

import java.lang.reflect.Member;

/**
 * @author ov_alimuya
 * @param <Y>
 *
 */
public interface IReflectObjectBuilder<T extends AbstractReflectObject<?>> {
	public boolean isSuitable(Member member);
	
	public T build(Class<?> beanClass,Member member) throws Exception;
	
}
