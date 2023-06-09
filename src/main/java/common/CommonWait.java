package common;

import static util.Key.explicitWait;
import java.time.Duration;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.TestBase;
import util.Configuration;

public class CommonWait {

	private static WebDriverWait wait;
	private static Configuration config = new Configuration();

	static {
		wait = new WebDriverWait(TestBase.driver, Duration.ofSeconds(config.readPropNum(explicitWait.name())));

	}

	public static void waitUntilVisible(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilClickable(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void waitUntilAlert() {
		wait.until(ExpectedConditions.alertIsPresent());
	}
}