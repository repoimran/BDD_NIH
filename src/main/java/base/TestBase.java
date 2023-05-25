package base;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import pageObject.AboutPage;
import pageObject.GrantsAndFundingPage;
import pageObject.HomePage;
import pageObject.InstitutesAtNihPage;
import pageObject.NewsAndEventPage;
import pageObject.ResearchAndTrainingPage;
import util.Configuration;
import util.Key;

public class TestBase {

	public static WebDriver driver;
	protected HomePage hp;
	protected GrantsAndFundingPage gp;
	protected AboutPage ap;
	protected InstitutesAtNihPage ip;
	protected NewsAndEventPage np;
	protected ResearchAndTrainingPage rp;

	Configuration conf = new Configuration();

	public void beforeEachTest(String url) {

		initiatDriver(conf.readProp(Key.browser.name()));
		initObject();
		driver.manage().window().maximize();
		int pageLoadTime = conf.readPropNum(Key.pageLoad.name());
		int implicitLoadTime = conf.readPropNum(Key.implicitLoad.name());
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageLoadTime));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitLoadTime));

		driver.get(url);
	}

	public void initiatDriver(String browserKey) {

		switch (browserKey) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		case "headless":
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--headless");
			driver = new ChromeDriver(options);
			break;
		default:
			System.out.println("invalid broserkey, default Browser >>>  Chrome  <<<<");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		}
	}

	protected void initObject() {
		hp = new HomePage(driver);
		gp = new GrantsAndFundingPage(driver);
		ap = new AboutPage(driver);
		ip = new InstitutesAtNihPage(driver);
		np = new NewsAndEventPage(driver);
		rp = new ResearchAndTrainingPage(driver);
	}

	public void tearDown() {
		driver.quit();
	}

}
