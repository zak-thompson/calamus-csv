package com.thompson.calamus.util;

import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.exception.NoSuchSetterException;
import com.thompson.calamus.exception.NoValidConstructorException;
import com.thompson.calamus.exception.ReflectionException;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public final class ReflectionUtil {

	@SuppressWarnings("unchecked")
	public static <T> T getInstanceOfClassByNoArgumentConstructor(@NonNull Class<T> clazz) throws CalamusCSVException {
		try {

			Predicate<Constructor> isNoArgumentConstructor = constructor -> constructor.getParameterCount() == 0;

			return (T) Arrays.stream(clazz.getDeclaredConstructors())
					.filter(isNoArgumentConstructor)
					.collect(singletonCollector())
					.newInstance();

		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionException(ReflectionException.ReflectionError.INSTANTIATION_ERROR, clazz.getName(), e);
		}
	}

	public static Method getSetterForField(@NonNull Field field, @NonNull Class clazz) throws CalamusCSVException{
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

	private static <T> Collector<T, List<T>, T> singletonCollector(){
		return Collector.of(
				ArrayList::new,
				List::add,
				(left, right) -> {
					left.addAll(right);
					return left;
				},
				new Function<List<T>, T>() {
					@SneakyThrows(CalamusCSVException.class)
					@Override
					public T apply(List<T> list) {
						if (list.size() != 1){
							throw new NoValidConstructorException();
						}
						return list.get(0);
					}
				}
		);
	}
}
