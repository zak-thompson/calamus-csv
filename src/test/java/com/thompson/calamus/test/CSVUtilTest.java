package com.thompson.calamus.test;

import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.test.model.unit.TestAnnotationsModel;
import com.thompson.calamus.test.model.unit.TestModel;
import com.thompson.calamus.test.util.TestUtil;
import com.thompson.calamus.util.CSVUtil;
import org.apache.commons.csv.CSVRecord;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class CSVUtilTest {

	@Test
	public void testExistingField() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();

		Field booleanField = clazz.getDeclaredField("aBoolean");
		Assert.assertEquals(true, CSVUtil.parseFieldValueFromRow(booleanField, row));

		Field primitiveBooleanField = clazz.getDeclaredField("aPrimitiveBoolean");
		Assert.assertEquals(true, CSVUtil.parseFieldValueFromRow(primitiveBooleanField, row));

		Field integerField = clazz.getDeclaredField("aInteger");
		Assert.assertEquals(-109, CSVUtil.parseFieldValueFromRow(integerField, row));

		Field stringField = clazz.getDeclaredField("aString");
		Assert.assertEquals("test string", CSVUtil.parseFieldValueFromRow(stringField, row));
	}

	@Test(expected = CalamusCSVException.class)
	public void testNonExistentField() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();
		Field field = clazz.getDeclaredField("notCSVField");
		CSVUtil.parseFieldValueFromRow(field, row);
	}

	@Test
	public void testColumnNameAnnotation() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestAnnotationsModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();
		Field columnField = clazz.getDeclaredField("testColAnnotation");
		Assert.assertEquals("str", CSVUtil.parseFieldValueFromRow(columnField, row));
	}

	@Test(expected = CalamusCSVException.class)
	public void testNonExistentColumnAnnotation() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestAnnotationsModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();

		Field field = clazz.getDeclaredField("nonExistent");
		CSVUtil.parseFieldValueFromRow(field, row);
	}

	@Test
	public void testDateParsing() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestAnnotationsModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();
		Field field = clazz.getDeclaredField("validFormatValidValue");
		Date date = (Date)CSVUtil.parseFieldValueFromRow(field, row);
		Assert.assertNotNull(date);
	}

	@Test(expected = CalamusCSVException.class)
	public void testInvalidDateFormat() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestAnnotationsModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();
		Field invalidDateFormat = clazz.getDeclaredField("invalidDateFormat");
		CSVUtil.parseFieldValueFromRow(invalidDateFormat, row);
	}

	@Test(expected = CalamusCSVException.class)
	public void testValidDateFormatThatDoesNotMatchRowData() throws NoSuchFieldException, CalamusCSVException {
		Class clazz = TestAnnotationsModel.class;
		CSVRecord row = TestUtil.getCSVRecordForTesting();
		Field validFormatButInvalidValue = clazz.getDeclaredField("validFormatButInvalidValue");
		CSVUtil.parseFieldValueFromRow(validFormatButInvalidValue, row);
	}
}
