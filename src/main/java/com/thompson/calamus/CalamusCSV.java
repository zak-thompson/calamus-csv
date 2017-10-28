package com.thompson.calamus;

import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.exception.ReflectionException;
import com.thompson.calamus.util.CSVUtil;
import com.thompson.calamus.util.ReflectionUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public final class CalamusCSV {

	public static <T> List<T> parse(@NonNull File csvFile, @NonNull Class<T> clazz) throws CalamusCSVException, IOException {
		return parse(csvFile, clazz, false);
	}

	public static <T> List<T> parse(@NonNull File csvFile, @NonNull Class<T> clazz, boolean isParallel)
			throws CalamusCSVException, IOException {

		Reader reader = new FileReader(csvFile);

		Iterable<CSVRecord> records = CSVFormat.RFC4180
				.withFirstRecordAsHeader()
				.parse(reader);

		return StreamSupport.stream(records.spliterator(), isParallel)
				.map(record -> processRow(record, clazz))
				.collect(Collectors.toList());
	}

	@SneakyThrows(CalamusCSVException.class)
	private static <T> T processRow(CSVRecord record, Class<T> clazz) {

		final T modelInstance = ReflectionUtil.getInstanceOfClassByNoArgumentConstructor(clazz);

		final Field[] fields = clazz.getDeclaredFields();

		Arrays.stream(fields)
				.forEach(field -> processField(field, record, modelInstance));

		return modelInstance;
	}

	@SneakyThrows(CalamusCSVException.class)
	private static <T> void processField(Field field, CSVRecord row, T model){

		Method setter = ReflectionUtil.getSetterForField(field, model.getClass());

		Object argument = CSVUtil.parseFieldValueFromRow(field, row);

		try {
			setter.invoke(model, argument);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new ReflectionException(ReflectionException.ReflectionError.INVOKE_ERROR, setter.getName(), e);
		}

	}


}
