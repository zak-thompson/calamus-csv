package com.thompson.calamus.exception;

/**
 * Created by Zak Thompson on 10/27/2017.
 */
public class NoValidConstructorException extends CalamusCSVException {
	//cant provide more specific error message due to type erasure inside of collector
	//TODO possibly get more refined solution with more detailed message
	public NoValidConstructorException(){
		super("No valid no argument constructor exists on the model class");
	}
}
