package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Web_Browser_Method {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	
	}

	@Test
	public void TC_01_Browser() {
		//bien driver tuong tac vs browser
		
		//mo page
		driver.get("https://vi-vn.facebook.com/");
		
		//lay URL hien tai
		String localPageUrl = driver.getCurrentUrl();
		
		driver.getTitle();
		
		//html code
		driver.getPageSource();
		
		//xu ly tab/window
		driver.getWindowHandle();
		driver.getWindowHandles();
		
		
		//
		//driver.manage().addCookie(arg0);
		
		driver.manage().timeouts().implicitlyWait(300, TimeUnit.SECONDS);
		
		driver.navigate().back();
		
		driver.quit();
		driver.close();
		
		driver.switchTo().alert();
		driver.switchTo().frame(1);
		driver.switchTo().window("");
		
		driver.manage().window().fullscreen();
		driver.manage().window().maximize();
		driver.manage().window().getPosition();
		
		driver.manage().window().getSize();

	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

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