package com.junit5.demo.JUnit4vsJUnit5;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import org.junit.Rule;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.migrationsupport.rules.EnableRuleMigrationSupport;
import org.junit.rules.ExpectedException;

import com.junit5.demo.app.Calculator;

//Class and Methods not required to be public
@EnableRuleMigrationSupport
class JupiterCalculatorTests {

	private final Calculator calculator = new Calculator();

	@Rule
	public final ExpectedException exception = ExpectedException.none();

//	 Timeout is not supported
	// @Rule
	// public Timeout globalTimeout = Timeout.millis(1000);

	@Disabled
	void testAdd() {
		// message are at the end in Jupiter
		assertEquals(5, calculator.add(2, 3), "2 + 3 = 5");
	}

	@Test
	void testSubstract() {
		// lambda expression
		assertEquals(2, calculator.substract(3, 1), () -> "3 - 1" + "= 2");
	}

	@Test
	void testMultiply() {
		assertEquals(10, calculator.multiply(5, 2), "5 * 2 = 10");
	}

	@Test
	void testDivide() {
		assertEquals(2, calculator.divide(6, 3), "6 / 3 = 2");
	}

	@Test
	void testDivideByZero() {
		exception.expect(ArithmeticException.class);
		exception.expectMessage("/ by zero");
		calculator.divide(1, 0);
	}

	@Test
	void testDivideByZeroInJunit5() {
		ArithmeticException exception = assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0));
		assertEquals("/ by zero", exception.getMessage());
	}

	@Test
	void testFibonacci() {
		//non preemptive timeout
		assertTimeout(ofMillis(6000), () -> calculator.fibonacci(45));
		//preemptive timeout; this will the same error we saw in JUnit4 (replacement of Timeout in JUnit 4)
		//executed in a different thread and preemptively aborted if timeout is exceeded
		assertTimeoutPreemptively(ofMillis(1000), () -> calculator.fibonacci(45));
	}

}
