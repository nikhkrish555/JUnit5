package com.junit5.demo.app;

public class Calculator {

	public int add(int a, int b) {
		return a + b;
	}
	
	public int substract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public int divide(int a, int b) {
		return a / b;
	}
	
	public int fibonacci(int n) {
		if (n == 0) {
			return 0;
		}
		else if (n==1) {
			return 1;
		}
		return fibonacci(n-1) + fibonacci(n-2);
	}
}

