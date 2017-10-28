package com.thompson.calamus.exception;

import java.lang.reflect.Field;

/**
 * Created by Zak Thompson on 10/27/2017.
 */
public class DateException extends CalamusCSVException {
	public DateException(Field field) {
		super("Date field " + field.getName() + " is missing the required DateMetadata annotation");
	}

	public DateException(Throwable throwable) {
		super(throwable);
	}
}
