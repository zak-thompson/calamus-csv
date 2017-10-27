package com.thompson.calamus.exception;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class CalamusCSVException extends Exception {

	public CalamusCSVException(){

	}

	public CalamusCSVException(String message){
		super(message);
	}

	public CalamusCSVException(Throwable throwable) {
		super(throwable);
	}

	public CalamusCSVException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
