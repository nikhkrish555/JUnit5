package com.junit5.demo.AssertionsAndAssumptions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

class AssumptionsDemo {
	private final int a = 2;
	private final int b = 1;

	@Test
	void testOnlyOnMac() {
		assumeTrue("Mac OS ".equals(System.getProperty("os.name")), () -> {
			return "Aborting Test, mac OS X not found, your OS isc" + System.getProperty("os.name");
		});
		assertTrue(a > b, () -> a + " is not greater than " + b);
	}

	@Test
	void testOnAllOS() {
		// test all all systems
		assertTrue(b > a, () -> a + " is not greater than " + b);

		// test only on specified system
		assumingThat("Windows 10".equals(System.getProperty("os.name")), () -> {
			assertTrue('a' > 'b', "ASCII Comparator");
		});

		// test on all systems
		assertTrue(2 + 2 == 4);
	}
}
