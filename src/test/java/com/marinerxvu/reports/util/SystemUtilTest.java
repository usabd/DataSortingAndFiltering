package com.marinerxvu.reports.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import javax.management.DescriptorKey;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class SystemUtilTest {
	SystemUtil systemutil;

	@BeforeEach
	void init() {
		systemutil = new SystemUtil();
	}

	@Nested
	@DisplayName("Validating passing String")
	class ValidatingString {

		@Test
		@DisplayName("passing wrong string , expected Class cast Exception")
		void testValidateString() {
			assertThrows(ClassCastException.class, () -> systemutil.validateString(0));

		}

		@Test
		@DisplayName("passing correct string ")
		void testValidateStringCorrect() {
			assertEquals("Hi", systemutil.validateString("Hi"));

		}
	}

	@Nested
	@DisplayName("Validating passing Long")
	class ValidatingLong {

		@Test
		@DisplayName("passing wrong Long,expected class cast expection ")
		void testValidateLongExp() {
			assertThrows(ClassCastException.class, () -> systemutil.validateLong(""));

		}

		@Test
		@DisplayName("passing correct long ")
		void testValidateLong() {
			assertEquals(new Long(0L), systemutil.validateLong(0L));

		}
	}

	/**
	 * 
	 */
	@Test
	@DisplayName("Check Reading Files")
	void testReadFile() {
		assertThrows(FileNotFoundException.class, () -> systemutil.readFile(""));
	}

}
