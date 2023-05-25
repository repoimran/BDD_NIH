package auto;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import base.TestBase;

public class AutoHomePageTest extends TestBase {

	@Test
	public void testHomePage() throws InterruptedException, IOException {
		hp.getPageTitle();
		hp.insertIntoSearchBox();
		hp.mouseHoverHealthInformationLinkSubMenuCheck();
	}

}
