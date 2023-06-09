package common;

//import org.openqa.selenium.io.FileHandler;
import static common.CommonWait.waitUntilClickable;
import static common.CommonWait.waitUntilVisible;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import report.Log;
import util.Configuration;
import util.Key;
import util.Misc;

public class CommonAction {
	static Configuration config = new Configuration();

	static String test_output_path = config.readProp(Key.test_output_path.name());
	static String screenshotPath = config.readProp(Key.screenshotPath.name());

	public static String getInnerHTML(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getAttribute("innerHTML");
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	public static String getText(WebElement element) {
		waitUntilVisible(element);
		String innerText = element.getText();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< Inner Text : " + innerText);
		return innerText;
	}

	// regular click
	public static void click(WebElement element) {
		waitUntilClickable(element);
		element.click();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked");
	}

	// overloaded click with 2 args
	public static void click(WebElement element, WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked with JavascriptExec");
	}

	// overloaded click with 3 args
	public static void click(WebElement element, WebDriver driver, String key) {
		Actions newTab = new Actions(driver);
		newTab.keyDown(Keys.CONTROL).click(element).keyUp(Keys.CONTROL).build().perform();
		Log.log(Misc.getClassName(Thread.currentThread()), element + " Has been clicked with controlClick");
	}

	public static void insert(WebElement element, String text) {
		element.sendKeys(text);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void insert(WebElement element, char text) {
		String temp = String.valueOf(text);
		element.sendKeys(temp);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< value inserted : " + text);
	}

	public static void dropdown(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByVisibleText(value);
		Log.log(Misc.getClassName(Thread.currentThread()), element + " <<< selected : " + value);
	}

	public static void switchWindow(String root, Set<String> allWindows, WebDriver driver) {
		for (String handle : allWindows) {
			if (!handle.equals(root)) {
				driver.switchTo().window(handle);
			}
		}
	}

	public static File screenshotElement(WebDriver driver, String name, WebElement element) throws Exception {
		waitUntilVisible(element);
		File source = element.getScreenshotAs(OutputType.FILE);
		String dir = createDirectory(screenshotPath);
		File screenShotTargetFile = new File(dir + name + ".png");
		FileUtils.copyFile(source, screenShotTargetFile);
		return screenShotTargetFile;
		// We can use either FileHandler or FileUtils
		// FileHandler.copy(src, new File(screenshotPath + name + ".png"));

	}

	public static File screenshotPage(WebDriver driver, String name) throws IOException {
		File screenShotTargetFile;
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dir = createDirectory(screenshotPath);
		screenShotTargetFile = new File(dir + name + ".png");
		FileUtils.copyFile(source, screenShotTargetFile);
		return screenShotTargetFile;
	}

	public static String createDirectory(String dirPath) {
		String dir = test_output_path + dirPath;
		// check if directory exists
		File directory = new File(dir);
		if (!directory.exists()) {
			directory.mkdirs();// if not create directory
		}
		return dir;
	}

	public static String saveOcrImage(File screenshot) {
		ITesseract image = new Tesseract();
		String ocrString = "";
		try {
			ocrString = image.doOCR(screenshot);
			System.out.println("******************** OCR TEXT START ********************");
			System.out.println(ocrString);
			System.out.println("******************** OCR TEXT END ********************");
		} catch (TesseractException e) {
			e.printStackTrace();
		}
		return ocrString;
	}

	public static String textToFile(String text, String name, String path, boolean addTimeStamp) throws IOException {
		String dir = createDirectory(path);
		// Create the empty text file name ocr.txt
		String filePath = dir + File.separator + name + ".txt"; // Build the file path

		// if addTimeStamp is true only then add time stamp
		if (addTimeStamp)
			text = getTimestamp() + "\n" + text; // adding timestamp and new line

		FileWriter fileWriter = new FileWriter(filePath);
		fileWriter.write(text);// write the text to the file, not yet saved
		fileWriter.close();// save the file
		return filePath;
	}

	public static void mouseHover(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		waitUntilVisible(element);
		actions.moveToElement(element).build().perform();
	}

	public static void roboKey(String key, WebElement element) {
		String k = key.toLowerCase();
		try {
			Robot robot = new Robot();
			if (k.equals("enter")) {
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

			} else if (k.equals("tab")) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
		} catch (AWTException e) {
			System.out.println(e.getMessage());
		}

	}

	public static String getTimestamp() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
		String timestamp = format.format(date);
		return timestamp;
	}

	public static String readTextFile(String filePath) throws IOException {

		String content = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		return content;

	}

	public static String getHref(WebElement element) {
		return element.getAttribute("href");
	}

	public static void printArray(String[] arr) {
		for (String s : arr) {
			System.out.print(s.trim() + " ");
		}
		System.out.print("\n");
	}

	public static String trimArrayElemments(String[] arr) {
		List<String> tempArray = new ArrayList<String>();
		for (String s : arr) {
			s = s.trim();
			tempArray.add(s);
		}
		String finalString = String.join(" ", tempArray);
		return finalString;

	}

}
