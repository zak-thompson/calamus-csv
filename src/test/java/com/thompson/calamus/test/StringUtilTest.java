package com.thompson.calamus.test;

import com.thompson.calamus.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public class StringUtilTest {

	@Test
	public void testUpperCaseFirstLetter(){
		Assert.assertEquals("Abcdefg", StringUtil.upperCaseFirstLetter("abcdefg"));
		Assert.assertEquals("XYZ", StringUtil.upperCaseFirstLetter("XYZ"));
	}
}
