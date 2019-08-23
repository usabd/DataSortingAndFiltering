package com.marinerxvu.reports.util;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * 
 * Util class to add all the common method
 * 
 * @author Usama Ismail
 *
 */
public class SystemUtil {

	/**
	 * Validate the String object
	 * 
	 * @param obj String
	 * @return String casted value
	 * @throws ClassCastException in case the object not String
	 */
	public static String validateString(Object obj) {
		if (obj instanceof String)
			return (String) obj;
		else
			throw new ClassCastException(
					"send parameter is not String , please validate the input data" + obj.toString());
	}

	/**
	 * validate the Long Object
	 * 
	 * @param obj long
	 * @return Long the casted value.
	 * @throws ClassCastException in case the object not Lang
	 */
	public static Long validateLong(Object obj) throws ClassCastException {
		if (obj instanceof Long)
			return (Long) obj;
		else
			throw new ClassCastException(
					"send parameter is not long , please validate the input data" + obj.toString());
	}

	/**
	 * Read file function to validte if the file exist
	 * 
	 * @param fileName the file nme with extension
	 * @return file object
	 * @throws FileNotFoundException in case file not exist
	 */
	public static File readFile(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		if (file.isFile())
			return file;
		else
			throw new FileNotFoundException(fileName + "  file not exist  ,please make sure it is in main folder");

	}

}
