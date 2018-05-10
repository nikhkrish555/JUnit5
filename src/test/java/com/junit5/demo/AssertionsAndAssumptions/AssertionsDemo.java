package com.junit5.demo.AssertionsAndAssumptions;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.junit5.demo.app.Calculator;

class AssertionsDemo {

	private final Calculator calculator = new Calculator();

	@Test
	void standardAssertions() {
		assertEquals(2, calculator.add(1, 1));
		assertEquals(4, calculator.multiply(2, 2), "The optional assertion message is now the last parameter.");
		//if assertion is true then lambda expression will not get evaluated
		assertTrue('a' < 'b', () -> "Assertion messages can be lazily evaluated -- "
				+ "to avoid constructing complex messages unnecessarily.");
	}

	@Test
	void groupedAssertions() {
		// In a grouped assertion all assertions are executed, and any
		// failures will be reported together.
		// Similar to soft assert in AssertJ
		assertAll("Calculator Tests", 
				() -> assertEquals(2, calculator.add(1, 2)),
				() -> assertEquals(4, calculator.substract(10, 5), "10 -5 = 5"));
	}

	@Test
	void dependentAssertions() {
		// Within a code block, if an assertion fails the
		// subsequent code in the same block will be skipped.
		assertAll("Calculator Dependents", 
			() -> {
					assertTrue(2>3);
					// Executed only if the previous assertion is valid.
					assertAll("Calculator Add and Multiply", 
							() -> assertEquals(2,calculator.add(2, 1)),
							() -> assertEquals(2,calculator.multiply(1, 2)));
			}, 
			() -> {
					// Grouped assertion, so processed independently
					// of results of above assertion assertions.
					assertTrue(4>4);
					// Executed only if the previous assertion is valid.
					assertAll("Calculator Divide and Multiplye", 
							() -> assertEquals(1,calculator.divide(1, 1)),
							() -> assertEquals(2,calculator.multiply(1, 2)));
			}
		);
	}

}
