package com.junit5.demo.JUnit4vsJUnit5;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A special test case")
class TestLifeCycleCallbacksDemo {

	@BeforeAll // similar to @BeforeClass in JUnit4
	static void initAll() {
	}

	@BeforeEach // similar to @Before in JUnit4
	void init() {
	}

	@Test
	@DisplayName("JUnit 5 supports emojis!!!😊😭🤗😇😱")
	void succeedingTest() {
	}
	
	@Test
	void failingTest() {
		fail("a failing test");
	}

	@Test
	@Disabled
	@DisplayName("Ignored is now replaced with Disabled")
	void skippedTest() {
		// not executed
	}

	@AfterEach
	void tearDown() {
	}

	@AfterAll
	static void tearDownAll() {
	}
}
