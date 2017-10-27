package com.thompson.calamus.test.model.unit;

import com.thompson.calamus.annotation.ColumnName;
import lombok.Data;

/**
 * Created by Zak Thompson on 10/4/2017.
 */
@Data
public class TestColumnNameAnnotationModel {

	private Boolean aBoolean;

	@ColumnName(name = "test col annotation")
	private String testColAnnotation;

	@ColumnName(name = "non existent column")
	private String nonExistent;
}
