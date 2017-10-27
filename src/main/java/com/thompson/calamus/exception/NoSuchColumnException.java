package com.thompson.calamus.exception;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class NoSuchColumnException extends CalamusCSVException {
	public NoSuchColumnException(String columnName){
		super("No column exists in the CSV with the name: " + columnName);
	}
}
