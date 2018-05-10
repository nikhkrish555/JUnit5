package com.junit5.demo.DynamicTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class DynamicTestDemo {

	/**
	 * all test cases you saw before were static in the sense that they are fully
	 * specified at compile time, and their behavior cannot be changed by anything
	 * happening at runtime.
	 * 
	 * Dynamic Test are generated at run time by factory method
	 * called @TestFactory. @TestFactory is not a test case but a factory for test
	 * cases. Dynamic test is the product of the factory.
	 * 
	 * @TestFactory method must return a Stream, Collection, Iterable, or Iterator
	 *              of DynamicNode
	 */
	@TestFactory
	@DisplayName("Test should return dynamic type, list of string is not one among them.")
	List<String> dynamicTestsWithInvalidReturnType() {
		return Arrays.asList("Hello");
	}

	@TestFactory
	Collection<DynamicTest> dynamicTestsFromCollection() {
		return Arrays.asList(dynamicTest("1st dynamic test", () -> assertTrue(true)),
				dynamicTest("2nd dynamic test", () -> assertEquals(4, 2 * 2)));
	}

	@TestFactory
	Iterable<DynamicTest> dynamicTestsFromIterable() {
		return Arrays.asList(dynamicTest("3rd dynamic test", () -> assertTrue(true)),
				dynamicTest("4th dynamic test", () -> assertEquals(4, 2 * 2)));
	}

	@TestFactory
	Iterator<DynamicTest> dynamicTestsFromIterator() {
		return Arrays.asList(dynamicTest("5th dynamic test", () -> assertTrue(true)),
				dynamicTest("6th dynamic test", () -> assertEquals(4, 2 * 2))).iterator();
	}

	@TestFactory
	Stream<DynamicTest> dynamicTestsFromStream() {
		return Stream.of("A", "B", "C").map(str -> dynamicTest("test" + str, () -> {
			/* ... */ }));
	}

	@TestFactory
	Stream<DynamicNode> testFactoryName() throws Exception {
		return Stream.of("A", "B").map(input -> dynamicContainer("Container " + input, // Dynamic containers used for
																						// nesting of dynamic tests
				Stream.of(4, 2, 1).map(number -> dynamicTest("dynamic test for " + number, () -> isEven(number)))));
	}

	private void isEven(int i) {
		assertTrue(i % 2 == 0, () -> i + " is not an even number.");
	}

	@TestFactory
	Stream<DynamicNode> dynamicTestsWithContainers() {
		return Stream.of("A", "B", "C")
				.map(input -> dynamicContainer("Container " + input,
						Stream.of(dynamicTest("not null", () -> assertNotNull(input)),
								dynamicContainer("properties",
										Stream.of(dynamicTest("length > 0", () -> assertTrue(input.length() >1 )),
												dynamicTest("not empty", () -> assertFalse(input.isEmpty())))))));
		
	}
	
	 @TestFactory
	    Stream<DynamicTest> generateRandomNumberOfTests() {

	        // Generates random positive integers between 0 and 100 until
	        // a number evenly divisible by 7 is encountered.
	        Iterator<Integer> inputGenerator = new Iterator<Integer>() {

	            Random random = new Random();
	            int current;

	            @Override
	            public boolean hasNext() {
	                current = random.nextInt(100);
	                return current % 7 != 0;
	            }

	            @Override
	            public Integer next() {
	                return current;
	            }
	        };

	        // Generates display names like: input:5, input:37, input:85, etc.
	        Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

	        // Executes tests based on the current input value.
	        ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

	        // Returns a stream of dynamic tests.
	        return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
	    }

}
