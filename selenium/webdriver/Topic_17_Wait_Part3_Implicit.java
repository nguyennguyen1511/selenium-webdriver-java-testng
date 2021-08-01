package webdriver;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_Wait_Part3_Implicit {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.xpath("//div[@id='start']//button");
	By loadingIcon = By.xpath("//div[@id='loading']");
	By textHello = By.xpath("//div[@id='finish']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}


	@Test
	public void TC_01_Less_Than() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		//Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
		
		Assert.assertTrue(driver.findElement(textHello).isDisplayed());

	}

	@Test
	public void TC_02_Enough() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");

	}

	@Test
	public void TC_03_Greater_Than() {
		driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		driver.findElement(startButton).click();
		Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}
	
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}