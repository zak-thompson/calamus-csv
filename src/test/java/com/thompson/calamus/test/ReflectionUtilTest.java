package com.thompson.calamus.test;

import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.test.model.unit.TestModel;
import com.thompson.calamus.test.model.unit.TestModelNoAccessors;
import com.thompson.calamus.test.model.unit.TestMultipleConstructors;
import com.thompson.calamus.test.model.unit.TestMultipleConstructorsZeroNoArgument;
import com.thompson.calamus.util.ReflectionUtil;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class ReflectionUtilTest {

	@Test
	public void testGetFieldsSetter() throws NoSuchMethodException, NoSuchFieldException, CalamusCSVException {
		Field field = TestModel.class.getDeclaredField("aBoolean");
		Method setter = TestModel.class.getDeclaredMethod("setABoolean", Boolean.class);
		Assert.assertEquals(setter, ReflectionUtil.getSetterForField(field, TestModel.class));
	}

	@Test(expected = CalamusCSVException.class)
	public void testGetFieldsNoSuchSetter() throws NoSuchMethodException, NoSuchFieldException, CalamusCSVException {
		Field field = TestModelNoAccessors.class.getDeclaredField("aBoolean");
		ReflectionUtil.getSetterForField(field, TestModelNoAccessors.class);
	}

	@Test
	public void testConstructorInvoker() throws CalamusCSVException {
		TestModel testModel1 = ReflectionUtil.getInstanceOfClassByNoArgumentConstructor(TestModel.class);
		Assert.assertNotNull(testModel1);

		TestMultipleConstructors testModel2 = ReflectionUtil.getInstanceOfClassByNoArgumentConstructor(
				TestMultipleConstructors.class);
		Assert.assertNotNull(testModel2);
	}

	@Test(expected = CalamusCSVException.class)
	public void testConstructorInvokerShouldFail() throws CalamusCSVException {
		TestMultipleConstructorsZeroNoArgument testModel = ReflectionUtil.getInstanceOfClassByNoArgumentConstructor(
				TestMultipleConstructorsZeroNoArgument.class);
		Assert.assertNotNull(testModel);
	}
}
