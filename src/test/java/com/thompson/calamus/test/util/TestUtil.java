package com.thompson.calamus.test.util;

import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class TestUtil {
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

	private static String[] VALUES = {"1", "test string", "09/24/2017", "-109", "true", "true", "str"};
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
	}
}
