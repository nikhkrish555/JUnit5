package com.junit5.demo.Tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

class TagsAndCustomTagsDemo {

	// --include-tag fast
	// --exclude-tag fast

	@Tag("fast")
	@Test
	//@IntergrationTest
	void fastTest() {
	}

	@Test
	@Tag("normal")
	//@IntergrationTest
	void normalTest() {
	}
}
