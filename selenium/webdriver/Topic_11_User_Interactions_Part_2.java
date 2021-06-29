package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;

public class Topic_11_User_Interactions_Part_2 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;
	JavascriptExecutor jsExecutor;
	Alert alert;
	String jsHelper =projectPath + "/DragAndDrop/drag_and_drop_helper.js";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		driver = new ChromeDriver();
		//System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		//driver = new FirefoxDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[text()='Double click me']")));
		//Hover chuột vào textbox
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()= 'Hello Automation Guys!']")).isDisplayed());

	}
	//@Test
	public void TC_02_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		sleepInSecond(1);
		
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-cut"))).perform();
		sleepInSecond(1);
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-cut.context-menu-visible.context-menu-hover")).isDisplayed());
		
		action.click(driver.findElement(By.cssSelector(".context-menu-icon-cut.context-menu-visible.context-menu-hover"))).perform();
		sleepInSecond(1);
		
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: cut");
		sleepInSecond(1);
		
		driver.switchTo().alert().accept();
		
		
	}
	//@Test
	public void TC_03_Drag_Drop_HTML4() {
		
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		
		action.dragAndDrop(smallCircle, bigCircle).perform();
		
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		
		System.out.println(bigCircle.getCssValue("background-color"));
		System.out.println(Color.fromString(bigCircle.getCssValue("background-color")).asHex());
		Assert.assertEquals(Color.fromString(bigCircle.getCssValue("background-color")).asHex(), "#03a9f4");
		
	}
	//@Test
	public void TC_04_Drag_Drop_HTML5_JS_JQuery_Css_Selector() throws IOException{
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
	
		jsHelper = readFile (jsHelper) + "$('#column-a').simulateDragDrop({ dropTarget: '#column-b'});";
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		jsExecutor.executeScript(jsHelper);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}
	@Test
	public void TC_04_Drag_Drop_HTML5_Robot_Xpath_Css() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		
		drag_the_and_drop_html5_by_xpath("//div[@id='column-a']", "//div[@id='column-b']");
		sleepInSecond(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='A']")).isDisplayed());
		
		
	}
	
	public String readFile(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}