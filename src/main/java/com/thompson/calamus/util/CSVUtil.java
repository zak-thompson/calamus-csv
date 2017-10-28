package com.thompson.calamus.util;

import com.thompson.calamus.annotation.ColumnName;
import com.thompson.calamus.annotation.DateMetadata;
import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.exception.DateException;
import com.thompson.calamus.exception.NoSuchColumnException;
import lombok.NonNull;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public final class CSVUtil {

	public static Object parseFieldValueFromRow(@NonNull Field field, @NonNull CSVRecord row) throws CalamusCSVException {

		String fieldName = field.getName();
		Class fieldType = field.getType();

		final String value;

		if (field.isAnnotationPresent(ColumnName.class)){

			final String columnName = field.getDeclaredAnnotation(ColumnName.class).name();

			try {
				value = row.get(columnName);
			} catch (IllegalArgumentException e) {
				throw new NoSuchColumnException(columnName);
			}

		} else {

			String processedFieldName = StringUtil.upperCaseFirstLetter(fieldName);

			try {
				value = row.get(processedFieldName);
			} catch (IllegalArgumentException e){
				throw new NoSuchColumnException(processedFieldName);
			}

		}

		if (value == null){
			return null;
		} else if (fieldType.equals(Boolean.class) || fieldType.equals(boolean.class)) {
			return Boolean.valueOf(value);
		} else if (fieldType.equals(Byte.class) || fieldType.equals(byte.class)) {
			return Byte.valueOf(value);
		} else if (fieldType.equals(Character.class) || fieldType.equals(char.class)) {
			return value.charAt(0);
		} else if (fieldType.equals(Double.class) || fieldType.equals(double.class)) {
			return Double.valueOf(value);
		} else if (fieldType.equals(Float.class) || fieldType.equals(float.class)) {
			return Float.valueOf(value);
		} else if (fieldType.equals(Integer.class) || fieldType.equals(int.class)) {
			return Integer.valueOf(value);
		} else if (fieldType.equals(Long.class) || fieldType.equals(long.class)) {
			return Long.valueOf(value);
		} else if (fieldType.equals(Short.class) || fieldType.equals(short.class)) {
			return Short.valueOf(value);
		} else if (fieldType.equals(Date.class)) {
			return getDateFromField(field, value);
		} else {
			return value;
		}
	}

	private static Date getDateFromField(Field field, String value) throws DateException {

		if (!field.isAnnotationPresent(DateMetadata.class)) {
			throw new DateException(field);
		}

		try {

			String formatString = field.getDeclaredAnnotation(DateMetadata.class).dateTimeFormatterPattern();
			DateFormat dateFormat = new SimpleDateFormat(formatString);
			return dateFormat.parse(value);

		} catch (ParseException | IllegalArgumentException e) {
			throw new DateException(e);
		}
	}

}
