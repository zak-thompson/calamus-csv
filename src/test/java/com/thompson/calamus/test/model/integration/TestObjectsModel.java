package com.thompson.calamus.test.model.integration;

import lombok.Data;

/**
 * Created by Zak Thompson on 10/5/2017.
 */
@Data
public class TestObjectsModel {

	public TestObjectsModel(){

	}

	public TestObjectsModel(Boolean aBoolean, Byte aByte, Character aCharacter, Double aDouble, Float aFloat,
							Integer aInteger, Long aLong, Short aShort) {
		this.aBoolean = aBoolean;
		this.aByte = aByte;
		this.aCharacter = aCharacter;
		this.aDouble = aDouble;
		this.aFloat = aFloat;
		this.aInteger = aInteger;
		this.aLong = aLong;
		this.aShort = aShort;
	}

	//setter with multiple args to test no reflection errors
	public void setAShort(short short1, short short2){

	}

	private Boolean aBoolean;
	private Byte aByte;
	private Character aCharacter;
	private Double aDouble;
	private Float aFloat;
	private Integer aInteger;
	private Long aLong;
	private Short aShort;
}
