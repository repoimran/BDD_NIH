package stepdefenition;

import java.awt.AWTException;
import java.io.IOException;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AutoSteps extends TestBase {
	// ******* Home Page
	@Given("User is on NIH HomePage, can see title of HomePage in console")
	public void user_is_on_nih_home_page_can_see_title_of_home_page_in_console() {
		hp.getPageTitle();
	}

	@Given("wants to search for Senior Health into the search box")
	public void wants_to_search_for_into_the_search_box() {
		hp.insertIntoSearchBox();
	}

	@Given("wants to hover his mouse to the Health Information Link")
	public void wants_to_hover_his_mouse_to_the_health_information_link() throws InterruptedException, IOException {
		hp.mouseHoverHealthInformationLinkSubMenuCheck();
	}

	@Then("User wants to check COVID19 Treatment Guidelines link is directed to the correct page")
	public void user_wants_to_check_covid_treatment_guidelines_link_is_directed_to_the_correct_page() {
		hp.checkCovid19GuideLineLink();
	}

	// *******Grants & Funding Page
	@Given("User is on the Home Page and clicks on the Grants & Funding link, is redirected to the Grants and Funding page")
	public void user_is_on_the_home_page_and_clicks_on_the_grants_funding_link_is_redirected_to_the_grants_and_funding_page() {
		gp.clickGrantsAndFundingLink();
	}

	@Given("User is on the Grants and Funding page,clicks on the How to Apply link, opens the pdf in browser and  screenshot of the PDF should be taken and saved.")
	public void user_is_on_the_grants_and_funding_page_clicks_on_the_how_to_apply_link_opens_the_pdf_in_browser_and_screenshot_of_the_pdf_should_be_taken_and_saved()
			throws AWTException, InterruptedException, IOException {
		gp.openPdfAndTakeScreenshot();
	}

	@When("User have opened a PDF and taken a screenshot text is generated from the image using Optical character recognition tecnology")
	public void user_have_opened_a_pdf_and_taken_a_screenshot_text_is_generated_from_the_image_using_optical_character_recognition_tecnology()
			throws Exception {
		gp.saveOcrImageToText();
	}

	// *******News & Events Page
	@Given("User is on the Home Page and clicks on the News & Events Page link, is redirected to the News & Events Page and title is printed in console")
	public void user_is_on_the_home_page_and_clicks_on_the_news_events_page_link_is_redirected_to_the_news_events_page_and_title_is_printed_in_console() {
		np.clickNewsAndEventsLinkAndCheckTitle();
	}

	@Given("User is on the News and Events page, presses and holds the Control key, and clicks the Social Media Link, a new window should open, the window should be switched to the new window, and the total process should be recorded and saved in test-output\\/iMovies.")
	public void user_is_on_the_news_and_events_page_presses_and_holds_the_control_key_and_clicks_the_social_media_link_a_new_window_should_open_the_window_should_be_switched_to_the_new_window_and_the_total_process_should_be_recorded_and_saved_in_test_output_i_movies()
			throws Exception {
		np.controlClickSocialMediaLinkWithScreenRoder();
	}

	// *******About Page

	@Given("the user can navigate to the About page from the Home page,")
	public void avigate_to_home_page() {
		ap.navigateToAboutPage();
	}

	@Then("they can verify the correct URL address as {string}.")
	public void verify_correct_url(String url) {
		ap.checkAboutPageUrlAdress(url);
	}

	@Then("they can verify the Facebook footer href value as {string}.")
	public void verify_facebook_href(String href) {
		ap.checkSocialMediaPage(href);
	}

	@When("the user clicks on the Mail & Phone Information section,")
	public void click_mailPhone_link() {
		ap.clickContactLink();
	}

	@Then("they can see the information and save a screenshot.")
	public void save_a_screenshot() throws Exception {
		// TODO:
		ap.screenshotMailAndPhoneInfo();
	}

	@Then("they can perform OCR on the saved screenshot and save it as text.")
	public void screenshot_to_text() throws Exception {
		ap.generateTextFromScreenshotText();
	}

	@Then("they can extract the HTML text of the same element from the site and save it as a text file.")
	public void get_HTML_text() throws Exception {
		ap.checkMailPhonePageInfoHtml();
	}

	@Then("they can compare the HTML with the OCR text for comparison.")
	public void compare_html_with_OCR() throws IOException {
		ap.compareHTMLTextToScreenshotText();
	}

	// ************************************************************************************************

}
