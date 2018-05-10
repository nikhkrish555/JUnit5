package com.junit5.demo.DependencyInjection;

import java.util.HashMap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestReporter;

/**
 * Dependency Injection - Test Constructors and Methods are allowed to have
 * parameters. This enables dependency injection via these parameters.
 * ParameterResolver is the extension api that dynamically resolve the
 * parameters at run time. If a test constructor or
 * a @Test, @TestFactory, @BeforeEach, @AfterEach, @BeforeAll, or @AfterAll
 * method accepts a parameter, the parameter must be resolved at runtime by a
 * registered ParameterResolver. 3 Built-in ParameterResolver:
 * TestInfoParameterResolver - Method parameter - TestInfo,
 * TestReporterParameterResolver - Method parameter - TestReporter
 * RepetitionInfoParameterResolver Method parameter - RepetitionInfo
 */

class DependecyInjectionDemo {

	@DisplayName("Fast Test")
	@Tag("fast")
	@Test
	void testInfo(TestInfo testInfo) {
		System.err.println(testInfo.getDisplayName());
		// assertEquals("TestInfo Demo", testInfo.getDisplayName());
	}

	@Test
	void reportSingleValue(TestReporter testReporter) {
		testReporter.publishEntry("a key", "a value");
	}

	@Test
	void reportSeveralValues(TestReporter testReporter) {
		HashMap<String, String> values = new HashMap<>();
		values.put("user name", "dk38");
		values.put("award year", "1974");
	}

}
