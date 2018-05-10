package com.junit5.demo.DependencyInjection;

/**
 * http://www.baeldung.com/mockito-junit-5-extension
 */

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import com.junit5.demo.app.Calculator;

@ExtendWith(MockitoExtension.class)

class CustomParameterResolverDemo {

	@BeforeEach
	void init(@Mock Calculator calculator) {
		when(calculator.add(10, 20)).thenReturn(60);
	}

	@Test
	void simpleTestWithInjectedMock(@Mock Calculator calculator) {
		assertEquals(30, calculator.add(10, 20));
	}

}
