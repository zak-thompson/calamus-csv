package com.thompson.calamus.test.model.unit;

import lombok.Data;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
@Data
public class TestModel {
	private Boolean aBoolean;
	private boolean aPrimitiveBoolean;
	private Byte aByte;
	private byte aPrimitiveByte;
	private Character aCharacter;
	private char aPrimitiveCharacter;
	private Double aDouble;
	private double aPrimitiveDouble;
	private Float aFloat;
	private float aPrimitiveFloat;
	private Integer aInteger;
	private int aPrimitiveInteger;
	private Long aLong;
	private long aPrimitiveLong;
	private Short aShort;
	private short aPrimitiveShort;
	private String aString;
	private String notCSVField;
}
