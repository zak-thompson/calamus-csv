package com.thompson.calamus.util;

import lombok.NonNull;

/**
 *  Created by Zak Thompson on 9/24/2017.
 */
public final class StringUtil {

	public static String upperCaseFirstLetter(@NonNull final String string){
		return string.length() == 0 ? string : Character.toUpperCase(string.charAt(0)) + string.substring(1);
	}
}
