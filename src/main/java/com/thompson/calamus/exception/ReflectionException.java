package com.thompson.calamus.exception;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class ReflectionException extends CalamusCSVException {

	public ReflectionException(ReflectionError error, String target, Throwable throwable) {
		super(error + target, throwable);
	}

	public enum ReflectionError {
		INSTANTIATION_ERROR("Could not instantiate an instance of the model class: "),
		INVOKE_ERROR("Could not invoke the target method: ");

		private final String error;

		ReflectionError(String error){
			this.error = error;
		}

		public String getError() {
			return error;
		}

		@Override
		public String toString(){
			return getError();
		}
	}
}
