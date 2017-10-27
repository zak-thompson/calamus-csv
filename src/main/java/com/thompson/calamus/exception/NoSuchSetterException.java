package com.thompson.calamus.exception;

import java.lang.reflect.Field;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class NoSuchSetterException extends CalamusCSVException {
	public NoSuchSetterException(Field field){
		super("No setter exists in the model class with the name: " + field.getName());
	}
}
