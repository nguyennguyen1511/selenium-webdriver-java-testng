package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		//driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Fix_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		
		Assert.assertTrue(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());
		
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automationfc");
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automationfc");
		sleepInSecond(1);
		
		driver.findElement(By.cssSelector("button.btn-login-v1")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(),"Tài khoản không tồn tại!");
		
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
		Assert.assertFalse(driver.findElement(By.cssSelector("div#modal-login-v1>div")).isDisplayed());

	}

	//@Test
	public void TC_02_Random_Popup_In_Dom() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(5);
		
		WebElement popup = driver.findElement(By.cssSelector("div.mailch-wrap"));
		
		if (popup.isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		sleepInSecond(10);
		
		List <WebElement> articalText = driver.findElements(By.cssSelector("h3.post-title>a"));
		for (WebElement artical : articalText) {
			Assert.assertTrue(artical.getText().contains("Selenium"));
			
		}

	}

	@Test
	public void TC_03_Popup_Not_In_Dom() {
		driver.get("https://shopee.vn/");
		sleepInSecond(5);
		// Nếu không tìm thấy elements thì nó sẽ trả về list = empty
		// ko bị fail test case và ko bị throw exception
		List <WebElement> popup = driver.findElements(By.cssSelector("div.shopee-popup img"));
		if (popup.size()>0 && popup.get(0).isDisplayed()) {
			driver.findElement(By.cssSelector("div.shopee-popup__close-btn")).click();
		}
		
		driver.findElement(By.cssSelector("input.shopee-searchbar-input__input")).sendKeys("macbook");
		driver.findElement(By.cssSelector("button.btn-solid-primary")).click();
		sleepInSecond(10);
		
		List <WebElement> articalText = driver.findElements(By.cssSelector("div.yQmmFK"));
		for (WebElement artical : articalText) {
			System.out.println(artical.getText());
			Assert.assertTrue(artical.getText().contains("macbook"));
			Assert.assertTrue(artical.getText().toLowerCase().contains("MacBook"));
			Assert.assertTrue(artical.getText().toLowerCase().matches("(.*)MacBook(.*)"));
			
		}

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