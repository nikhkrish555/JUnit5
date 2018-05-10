package com.junit5.demo.ParameterizedTest;

/**
 * @ValueSource(ints = {1,2,3})
 * @EnumSource(TimeUnit.class)
 * @MethodSource(names ='myProviderMethod')
 * @CsvSource(value = { "foo , Zm9v", "bar , YmFy" })
 * @CSVFileSource(resources = "/two-column.csv")
*/
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

class ParameterizedDemo {

	@ParameterizedTest
	@CsvSource(value = { "foo , Zm9v", "bar , YmFy" })
	void encodingReturnsExpectedOutput(String input, String base64Output) {
		Base64.Encoder encoder = Base64.getEncoder();
		assertEquals(base64Output, encoder.encodeToString(input.getBytes()));
	}

	@ParameterizedTest
	@ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
	void palindromes(String candidate) {
		assertTrue(isPalindrome(candidate));
	}

	private boolean isPalindrome(String s) {
		int n = s.length();
		for (int i = 0; i < (n / 2); ++i) {
			if (s.charAt(i) != s.charAt(n - i - 1)) {
				return false;
			}
		}

		return true;
	}

	@ParameterizedTest
	@EnumSource(value = TimeUnit.class, names = { "DAYS", "HOURS" })
	void testWithEnumSourceInclude(TimeUnit timeUnit) {
		assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
	}

	@ParameterizedTest
	@MethodSource("stringProvider")
	void testWithSimpleMethodSource(String argument) {
		assertNotNull(argument);
	}

	static Stream<String> stringProvider() {
		return Stream.of("foo", "bar");
	}

}
