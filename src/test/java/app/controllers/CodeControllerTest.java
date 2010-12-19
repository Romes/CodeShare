package app.controllers;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

public class CodeControllerTest {

	@Test public void fakeTest() {
		assertNotNull("put something real.", new CodeController(null, null,null,null));
 	}
}
