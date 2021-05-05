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

public class Topic_02_Xpath_Css_Part_1_Locator {
	//Biến driver đại diện cho selenium WebDriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");
	}

	@Test
	public void TC_01_ID() {
		driver.findElement(By.id("FirstName")).sendKeys ("Automation");
		sleepInSecond (3);
		driver.findElement(By.id("gender-male")).click();
		sleepInSecond (3);

	}

	@Test
	public void TC_02_Class() {
		driver.navigate().refresh();
		driver.findElement(By.className("search-box-text")).sendKeys("Macbook");
		sleepInSecond (3);
		driver.findElement(By.className("search-box-button")).click();
		sleepInSecond (3);

	}

	@Test
	public void TC_03_Name() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");
		driver.findElement(By.name("Email")).sendKeys("dam@gmail.com");
		sleepInSecond (3);
		driver.findElement(By.name("Newsletter")).click();
		sleepInSecond (3);

	}
	@Test
	public void TC_04_Tagname() {
		//bao nhiêu thẻ a (đường link) dùng findElements - có s vì lưu trữ nhiều 
		System.out.println("Sum link = "+driver.findElements(By.tagName("a")).size());
		System.out.println("Sum link = "+driver.findElements(By.tagName("input")).size());

	}
	@Test
	public void TC_05_LinkText() {
		driver.findElement(By.linkText("Log in")).click();
		sleepInSecond (3);

	}
	@Test
	public void TC_06_Partial_LinkText() {
		driver.findElement(By.partialLinkText("viewed products")).click();

	}
	@Test
	public void TC_07_Css() {
		//css ko work dc vs text
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");
		driver.findElement(By.cssSelector("input[id='FirstName']")).sendKeys("Automation");
		sleepInSecond (3);
		driver.findElement(By.cssSelector("input[class='search-box-text ui-autocomplete-input']")).sendKeys("Macbook");
		sleepInSecond (3);
		driver.findElement(By.cssSelector("input[name='Email']")).sendKeys("dam@gmail.com");
		sleepInSecond (3);
		driver.findElement(By.cssSelector("a[href*='login']")).click();
		sleepInSecond (3);

	}
	@Test
	public void TC_08_Xpath() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2Fregister");
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		sleepInSecond (3);
		driver.findElement(By.xpath("//input[contains(@class,'search-box-text')]")).sendKeys("Macbook");
		sleepInSecond (3);
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys("dam@gmail.com");
		sleepInSecond (3);
		driver.findElement(By.xpath("//a[text()='Log in']")).click();
		sleepInSecond (3);
		driver.findElement(By.xpath("//a[contains(text(),'Recently viewed')]")).click();
		sleepInSecond (3);


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