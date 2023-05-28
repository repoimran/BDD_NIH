package base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import util.Configuration;
import util.Key;

public class Hook {

	static TestBase testBase;
	static Configuration config = new Configuration();

	@Before
	public static void beforeHook() {
		testBase = new TestBase();
		testBase.beforeEachTest(config.readProp(Key.url.name()));
	}

	@After
	public static void afterHook() {
		testBase.tearDown();
	}

}
