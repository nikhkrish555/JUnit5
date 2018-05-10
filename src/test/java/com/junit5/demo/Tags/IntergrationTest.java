package com.junit5.demo.Tags;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * JUnit Jupiter annotations can be used as meta-annotations. 
 * That means that you can define your own composed annotation that will automatically inherit
 * the semantics of its meta-annotations.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
//@Tag("normal")
@Tag("fast")
@Test
@interface IntergrationTest {
	
}
