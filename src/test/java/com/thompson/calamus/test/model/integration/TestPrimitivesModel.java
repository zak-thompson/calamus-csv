package com.thompson.calamus.test.model.integration;

import lombok.Data;

/**
 * Created by Zak Thompson on 10/7/2017.
 */
@Data
public class TestPrimitivesModel {

	public TestPrimitivesModel(){

	}

	public TestPrimitivesModel(boolean aBoolean, byte aByte, char aCharacter, double aDouble, float aFloat, int aInteger,
							   long aLong, short aShort) {
		this.aBoolean = aBoolean;
		this.aByte = aByte;
		this.aCharacter = aCharacter;
		this.aDouble = aDouble;
		this.aFloat = aFloat;
		this.aInteger = aInteger;
		this.aLong = aLong;
		this.aShort = aShort;
	}

	private boolean aBoolean;
	private byte aByte;
	private char aCharacter;
	private double aDouble;
	private float aFloat;
	private int aInteger;
	private long aLong;
	private short aShort;
}
