package com.thompson.calamus.test.model.unit;

import com.thompson.calamus.annotation.ColumnName;
import com.thompson.calamus.annotation.DateMetadata;
import lombok.Data;

import java.util.Date;

/**
 * Created by Zak Thompson on 10/4/2017.
 */
@Data
public class TestAnnotationsModel {

	private Boolean aBoolean;

	@ColumnName(name = "test col annotation")
	private String testColAnnotation;

	@ColumnName(name = "non existent column")
	private String nonExistent;

	@DateMetadata(dateTimeFormatterPattern = "not a real date pattern")
	private Date invalidDateFormat;

	@DateMetadata(dateTimeFormatterPattern = "h:mm a" )
	private Date validFormatButInvalidValue;

	@DateMetadata(dateTimeFormatterPattern = "EEE, MMM d, ''yy" )
	private Date validFormatValidValue;
}
