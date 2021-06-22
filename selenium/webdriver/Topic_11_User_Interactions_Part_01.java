package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interactions_Part_01 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Hover_Mouse_1() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		//Hover chuột vào textbox
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(5);
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(), "We ask for your age only for statistical purposes.");

	}
	//@Test
	public void TC_01_Hover_Mouse_2() {
		driver.get("https://www.myntra.com/");
		
		action.moveToElement(driver.findElement(By.xpath("//a[@class='desktop-main' and text()='Kids']"))).perform();
		sleepInSecond(5);
		
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and contains(text(),'Home & Bath')]")).click();
		sleepInSecond(3);
		
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='breadcrumbs-list']//span[contains(text(),'Kids Home Bath')]")).isDisplayed());
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.myntra.com/kids-home-bath");
	}

	//@Test
	public void TC_02_Hover_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		action.clickAndHold(rectangleNumber.get(0)).moveToElement(rectangleNumber.get(3)).release().perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

	}
	@Test
	public void TC_03_Hover_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		List <WebElement> rectangleNumber = driver.findElements(By.cssSelector("#selectable>li"));
		
		//nhấn phím ctrl xuống
		action.keyDown(Keys.CONTROL).perform();
		//chọn element
		action.click(rectangleNumber.get(0))
		.click(rectangleNumber.get(2))
		.click(rectangleNumber.get(3))
		.click(rectangleNumber.get(10)).perform();
		//nhả
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(3);
		
		Assert.assertEquals(driver.findElements(By.cssSelector("#selectable>li.ui-selected")).size(), 4);

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