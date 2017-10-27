package com.thompson.calamus.util;

import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.exception.NoSuchSetterException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public final class ReflectionUtil {

	public static Method getSetterForField(Field field, Class clazz) throws CalamusCSVException{
		Method[] methods = clazz.getMethods();

		List<Method> resultList =  Arrays.stream(methods)
				.filter(method -> isMethodSetterForField(method, field))
				.collect(Collectors.toList());

		if (resultList.size() == 1) {
			return resultList.get(0);
		} else {
			throw new NoSuchSetterException(field);
		}
	}

	private static boolean isMethodSetterForField(Method method, Field field){
		return method.getName().equals("set" + StringUtil.upperCaseFirstLetter(field.getName()))
				&& method.getParameterCount() == 1;
	}
}
