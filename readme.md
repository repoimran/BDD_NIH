# BDD_NIH

Project Structure & Workflow
 src/main/java
    -base
        Hook--> the starting point of junit execution(@Before & After)
              We are instantiating our Base Class(TestBase) and calling beforeEachTest method, passing our URL(From properties file) from here
        TestBase --> 
                initiateDriver() --> we are initializing our browser of choice from here(default given as chrome, from Properties file), using webdrivermanager for all required webdrivers. 
                initObject() --> we are booting up out page objects with WebDriver interface
                after pre-condition(pageload, timeouts, maximize browser) we get our url, driver.get();
    
    -common--> our wait and all repeatdly used Helper methods are kept here
   
   -pageObject --> all locators and functional methods are kept in seperate Page Object classes. 
    
    -util --> our frequiently used Kye constants are kept in Key.java(enum), Configuration file contaians all require property file handlers, and Misc.java
              has the getClassName(), which we use along with our logs to get corresponding class and method neames(where the tests re running from), we use the current Thread as a parameter
              and get the stackTrace[2] as it holds the current caller's information
   
 
 src/test/java
      -features
          -->Feature file is evaluated by "Feature:" keyword and its "Scenario:" and corresponding StepDefenions(methods) are called.
      -runner
          -->As soon as we run JUnit --> Test Execution starts using @RunWith(Cucumber.class) and we provide our Feature file location, glue out Hook class using @CucumberOptions , next the features are triggered.
      -stepdefenition
          --> methods in stepdefenitions are then called one by one to trigger our Coresponding PageObjec classes according to our Test strategy(feature file)
              our pageObjects contain all Locators and its functionality testing methods
  
  
  test-output(generated on runtime)
      all paths are from properties file and kept as a Key contstant to minimize the typing error. 
      
      -htmlText
          --> we generate this directory in runtime whenever we call our createDirectory() in common.CommonAction.java pass our desired directory path as argument
              we use screenshot to capture the image, perform OCR on that image using ITesseract library, and save the extracted Texts from the image to a text file
              using textToFile() in our common.CommonAction.java passing the text to save, name of the text file and path form properties file
     -logs
          --> we generate this directory in runtime whenever we call our log() in report.Log.java, we are removing all console logs using removeConsole(), and saving as a text file with a timestamp
      -screenshot
          --> we generate this directory in runtime whenever we call our screenshotElement() or screenshotPage() in common.CommonAction.java
              for element screenshot we pass the current driver, name of the image file and locator of the webElement
              for entire page screenshot we just pass current driver and name of the image file, both uses ".png" format
 
 
