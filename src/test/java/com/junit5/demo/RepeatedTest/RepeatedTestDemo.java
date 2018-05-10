package com.junit5.demo.RepeatedTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

public class RepeatedTestDemo {
	
	@DisplayName("FlakyTest")
	@RepeatedTest(10)
	void test() {
		assertEquals(4,2+2,()->"Not 100% sure if it returns expected value when repeated n times");
	}

}
