package auto;

import org.junit.jupiter.api.Test;
import base.TestBase;

public class AutoNewsAndEventsTest extends TestBase {

	@Test
	public void testNewsAndEventsPage() {

		np.clickNewsAndEventsLinkAndCheckTitle();
		try {
			np.controlClickSocialMediaLinkWithScreenRoder();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
