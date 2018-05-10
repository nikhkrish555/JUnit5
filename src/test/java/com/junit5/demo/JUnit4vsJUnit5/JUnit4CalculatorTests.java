package com.junit5.demo.JUnit4vsJUnit5;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;

import com.junit5.demo.app.Calculator;

public class JUnit4CalculatorTests {
	
	private final Calculator calculator = new Calculator();
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Rule
	public Timeout globalTimeout = Timeout.millis(1000);
	
	@Test
	public void testAdd() {
		assertEquals("2 + 3 = 5", 5, calculator.add(2, 3));
	}
	
	@Test
	public void testSubstract() {
		assertEquals("3 - 1 = 2", 2, calculator.substract(3, 1));
	}
	
	@Test
	public void testMultiply() {
		assertEquals("5 * 2 = 10", 10, calculator.multiply(5, 2));
	}
	
	@Test
	public void testDivide() {
		assertEquals("6 / 3 = 2",2,calculator.divide(6, 3));
	}
	
	@Test
	public void testDivideByZero() {
		exception.expect(ArithmeticException.class);
		exception.expectMessage("/ by zero");
		calculator.divide(1, 0);
	}
	
	@Test 
	public void testFibonacci() {
		calculator.fibonacci(30);
		//Timeout is premptive in JUnit 4. There is no non-preemptive mode in JUnit4.
	}
}
