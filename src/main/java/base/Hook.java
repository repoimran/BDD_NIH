package base;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import util.Key;

public class Hook {

	static TestBase testBase;

	@BeforeAll
	public static void beforeAllHook() {
		testBase = new TestBase();
	}

	@Before
	public static void beforeHook() {
		testBase.beforeEachTest(Key.chrome.name());
	}

	@AfterAll
	public static void afterAllHook() {
		System.gc();
	}

	@After
	public static void afterHook() {
		testBase.tearDown();
	}

}
