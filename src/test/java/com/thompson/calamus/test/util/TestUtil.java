package com.thompson.calamus.test.util;

import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class TestUtil {

	public static Date parseDateFromString(String formatString, String dateValue) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat(formatString);
		return dateFormat.parse(dateValue);
	}

	/*
		since apache csv is a bitch and declared CSVRecord a final class with a non-public constructor,
		we have a util method for generating a csv record for testing
	*/
	public static CSVRecord getCSVRecordForTesting() {
		try {
			Class clazz = CSVRecord.class;

			Constructor constructor = clazz.getDeclaredConstructors()[0];
			constructor.setAccessible(true);

			return (CSVRecord)constructor.newInstance(VALUES, MAPPING, "", RECORD_NUMBER, 0);

		} catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	private static String[] VALUES = {
			"1",
			"test string",
			"09/24/2017",
			"-109",
			"true",
			"true",
			"str",
			"",
			"01 01 1990",
			"Wed, Jul 4, '06" };
	private static Map<String, Integer> MAPPING;
	private static long RECORD_NUMBER = 235345;

	static {
		MAPPING = new HashMap<>();
		MAPPING.put("ALong", 0);
		MAPPING.put("AString", 1);
		MAPPING.put("ADate", 2);
		MAPPING.put("AInteger", 3);
		MAPPING.put("ABoolean", 4);
		MAPPING.put("APrimitiveBoolean", 5);
		MAPPING.put("test col annotation", 6);
		MAPPING.put("InvalidDateFormat", 7);
		MAPPING.put("ValidFormatButInvalidValue", 8);
		MAPPING.put("ValidFormatValidValue", 9);
	}
}
