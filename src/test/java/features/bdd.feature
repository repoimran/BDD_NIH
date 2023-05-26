#Scenario: BDD NIH Home Page
#Given User is on NIH HomePage, can see title of HomePage in console
#And wants to search for Senior Health into the search box
#And wants to hover his mouse to the Health Information Link
#Then User wants to check COVID19 Treatment Guidelines link is directed to the correct page
#Scenario: Accessing Grants and Funding information, Opening PDF, and Saving OCR text
#Given User is on the Home Page and clicks on the Grants & Funding link, is redirected to the Grants and Funding page
#Given User is on the Grants and Funding page,clicks on the How to Apply link, opens the pdf in browser and  screenshot of the PDF should be taken and saved.
#When User have opened a PDF and taken a screenshot text is generated from the image using Optical character recognition tecnology
#Scenario: Accessing News and Events and Recording the Screen
#Given User is on the Home Page and clicks on the News & Events Page link, is redirected to the News & Events Page and title is printed in console
#Given User is on the News and Events page, presses and holds the Control key, and clicks the Social Media Link, a new window should open, the window should be switched to the new window, and the total process should be recorded and saved in test-output/iMovies.
Feature: BDD Testing of NIH Website

  Scenario: Accessing About NIH Page and Checking Social Media Links
    Given the user can navigate to the About page from the Home page,
    Then they can verify the correct URL address as "https://www.nih.gov/about-nih".
    And they can verify the Facebook footer href value as "https://www.facebook.com/nih.gov".
    When the user clicks on the Mail & Phone Information section,
    Then they can see the information and save a screenshot.
    And they can perform OCR on the saved screenshot and save it as text.
    Then they can extract the HTML text of the same element from the site and save it as a text file.
    And they can compare the HTML with the OCR text for comparison.
