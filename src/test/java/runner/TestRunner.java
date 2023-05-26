package runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/java/features", glue = { "base", "stepdefenition" }, plugin = { "pretty",
		"html:target/cucumber-report.html", "json:target/cucumber-json.json" })
public class TestRunner {
}
