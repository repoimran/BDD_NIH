package pageObject;

import static common.CommonAction.click;
import static common.CommonAction.insert;
import static common.CommonAction.mouseHover;
import static common.CommonAction.screenshotElement;
import static common.CommonAction.screenshotPage;
import static org.openqa.selenium.support.PageFactory.initElements;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Configuration;
import util.Key;

public class HomePage {
	WebDriver driver;
	Configuration config = new Configuration();

	public HomePage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}

	@FindBy(xpath = "//span[normalize-space()='Health Information']")
	WebElement healthInformationLink;
	@FindBy(xpath = "//input[@id='query']")
	WebElement searchBox;
	@FindBy(xpath = "//a[normalize-space()='Subscribe']")
	WebElement SubscribeTab;
	@FindBy(xpath = "//a[normalize-space()='COVID-19 Treatment Guidelines']")
	WebElement covidGuidelinesLink;

	public void getPageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
	}

	public void insertIntoSearchBox() {
		insert(searchBox, "Senior Health");
		try {
			screenshotElement(driver, "SearchBox", searchBox);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void mouseHoverHealthInformationLinkSubMenuCheck() throws InterruptedException, IOException {

		mouseHover(driver, healthInformationLink);
		screenshotPage(driver, "SubMenuLinks");
		TimeUnit.MILLISECONDS.sleep(config.readPropNum(Key.pause.name()));
	}

	public void checkCovid19GuideLineLink() {
		// TODO:
		click(covidGuidelinesLink);
		String redir = driver.getCurrentUrl();
		Assert.assertEquals(redir, "https://www.covid19treatmentguidelines.nih.gov/");
	}

}
