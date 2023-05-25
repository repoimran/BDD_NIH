package auto;

import org.junit.jupiter.api.Test;
import base.TestBase;

public class AutoGrantsAndFundingPageTest extends TestBase {

	@Test
	public void testGrantsAndFundingPage() throws Throwable {
		gp.clickGrantsAndFundingLink();
		gp.openPdfAndTakeScreenshot();
		gp.saveOcrImageToText();
	}

}
