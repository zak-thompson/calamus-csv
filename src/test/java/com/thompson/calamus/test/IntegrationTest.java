package com.thompson.calamus.test;

import com.thompson.calamus.CalamusCSV;
import com.thompson.calamus.exception.CalamusCSVException;
import com.thompson.calamus.test.model.integration.TestObjectsModel;
import com.thompson.calamus.test.model.integration.TestPrimitivesModel;
import com.thompson.calamus.test.util.TestUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Zak Thompson on 10/5/2017.
 */
public class IntegrationTest {

	public IntegrationTest() throws ParseException {
		objResult1 = new TestObjectsModel(true, Byte.parseByte("120"), 'v', 12.78758696700, 45.67F, -91, 1928475658393940000L,
				(short)34, TestUtil.parseDateFromString("EEE, MMM dd, yyyy", "Fri, Oct 27, 2017"));
		objResult2 = new TestObjectsModel(false, Byte.parseByte("56"), ':', -56.7234523400, -911F, 34456, 2234852347522000L,
				(short)-3, TestUtil.parseDateFromString("EEE, MMM dd, yyyy", "Sun, Oct 22, 2017"));
		objResult3 = new TestObjectsModel(true, Byte.parseByte("127"), 'S', 0.23865865943, 6.789F, 2345, 29347527635942L, (short)1,
				TestUtil.parseDateFromString("EEE, MMM dd, yyyy", "Wed, Nov 01, 2017"));
		primResult1 = new TestPrimitivesModel(true, Byte.parseByte("120"), 'v', 12.78758696700,
				45.67F, -91, 1928475658393940000L, (short)34);
		primResult2 = new TestPrimitivesModel(false, Byte.parseByte("56"), ':', -56.72345234000,
				-911F, 34456, 2234852347522000L, (short)-3);
		primResult3 = new TestPrimitivesModel(true, Byte.parseByte("127"), 'S', 0.23865865943,
				6.789F, 2345, 29347527635942L, (short)1);
	}

	@Test
	public void testObjectsIntegration() throws IOException, CalamusCSVException {
		File file = new File(getClass().getClassLoader().getResource("model-integration.csv").getFile());
		List<TestObjectsModel> result = CalamusCSV.parse(file, TestObjectsModel.class);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(objResult1, result.get(0));
		Assert.assertEquals(objResult2, result.get(1));
		Assert.assertEquals(objResult3, result.get(2));
	}

	@Test
	public void testPrimitivesIntegration() throws IOException, CalamusCSVException {
		File file = new File(getClass().getClassLoader().getResource("model-integration.csv").getFile());
		List<TestPrimitivesModel> result = CalamusCSV.parse(file, TestPrimitivesModel.class);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(primResult1, result.get(0));
		Assert.assertEquals(primResult2, result.get(1));
		Assert.assertEquals(primResult3, result.get(2));
	}

	@Test
	public void testParallelIntegration() throws IOException, CalamusCSVException {
		File file = new File(getClass().getClassLoader().getResource("model-integration.csv").getFile());
		List<TestPrimitivesModel> result = CalamusCSV.parse(file, TestPrimitivesModel.class, true);
		Assert.assertEquals(3, result.size());
		Assert.assertEquals(primResult1, result.get(0));
		Assert.assertEquals(primResult2, result.get(1));
		Assert.assertEquals(primResult3, result.get(2));
	}

	private final TestObjectsModel objResult1;
	private final TestObjectsModel objResult2;
	private final TestObjectsModel objResult3;
	private final TestPrimitivesModel primResult1;
	private final TestPrimitivesModel primResult2;
	private final TestPrimitivesModel primResult3;
}
