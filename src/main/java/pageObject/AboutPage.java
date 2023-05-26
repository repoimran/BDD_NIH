package pageObject;

import static common.CommonAction.click;
import static common.CommonAction.getInnerHTML;
import static common.CommonAction.screenshotElement;
import static common.CommonAction.textToFile;
import static common.CommonAction.saveOcrImage;
import static common.CommonAction.readTextFile;
import static common.CommonAction.getHref;
import static org.openqa.selenium.support.PageFactory.initElements;
import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import util.Configuration;
import util.Key;

public class AboutPage {
	WebDriver driver;

	public AboutPage(WebDriver driver) {
		this.driver = driver;
		initElements(driver, this);
	}
	Configuration config = new Configuration();
	static File screenshotFile;
	String textHtmlDirPath = config.readProp(Key.HTMLTextDirPath.name());
	String textHtmlFilePath;
	String textHtml;
	String textScreenshotPath;
	static String textScreenshot;

	@FindBy(xpath = "//span[normalize-space()='About NIH']")
	WebElement aboutPageLink;
	@FindBy(xpath = "//a[@class='facebook-monochrome']")
	WebElement fbLink;

	@FindBy(xpath = "//a[@class='contactus-monochrome']")
	WebElement contactUsLink;

	@FindBy(xpath = "//h3[normalize-space()='Mail & Phone']")
	WebElement mailAndPhoneElement;

	@FindBy(xpath = "//h3[normalize-space()='Mail & Phone']//following-sibling::p")
	WebElement contactParagraph;

	public void navigateToAboutPage() {
		click(aboutPageLink);
	}

	public void checkAboutPageUrlAdress(String url) {
		Assert.assertEquals(url, driver.getCurrentUrl());
	}

	public void checkSocialMediaPage(String url) {
		String actualUrl = getHref(fbLink);
		Assert.assertEquals(url, actualUrl);
	}

	public void clickContactLink() {
		click(contactUsLink, driver);
	}

	public void screenshotMailAndPhoneInfo() throws Exception {
		screenshotFile = screenshotElement(driver, "contact", mailAndPhoneElement);
	}

	public static void generateTextFromScreenshotText() throws IOException {
		textScreenshot = saveOcrImage(screenshotFile);
		System.out.println("from   generateTextFromScreenshotText");
	}

	public void checkMailPhonePageInfoHtml() throws IOException {
		System.out.println("from" + "checkMailPhonePageInfo");
		String t1 = getInnerHTML(mailAndPhoneElement);
		textHtmlFilePath = textToFile(t1, "contactHTML", textHtmlDirPath);

	}

	public void compareHTMLTextToScreenshotText() throws IOException {

		// we have path of Html text File;
		String s1 = readTextFile(textHtmlFilePath);
		// we have textofScreenshot
		String s2 = textScreenshot;
		System.out.println("from compareHTMLTextToScreenshotText  s1");
		System.out.println(s1);
		System.out.println("from compareHTMLTextToScreenshotText  s2 (ocr)");
		System.out.println(s2);
		// Assert.assertEquals(s1, s2);

	}
}
