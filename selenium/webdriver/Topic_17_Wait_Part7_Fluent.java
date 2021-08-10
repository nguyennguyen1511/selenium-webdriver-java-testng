package webdriver;

import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_Wait_Part7_Fluent {
	WebDriver driver;
	WebDriverWait explicitWait;
	WebElement element;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	@Test
	public void TC_01_Fluent_TC09() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		//driver.findElement(By.cssSelector("div#start>button")).click();
		clickToElement(By.cssSelector("div#start>button"));
		//explicitWait =  new WebDriverWait (driver, 15);
		//explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
		//Assert.assertEquals(driver.findElement(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")).getText(), "Hello World!");

		
		Assert.assertTrue(waitForElementAndDisplayed(By.xpath("//div[@id='finish']/h4[text()='Hello World!']")));
		
	}
	public void TC_01_Fluent_TC08() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement coutdowntime = driver.findElement(By.id("javascript_countdown_time"));
		
		FluentWait <WebElement> fluentWait = new FluentWait <WebElement> (coutdowntime);
		fluentWait.withTimeout(Duration.ofSeconds(13))
			.pollingEvery(Duration.ofMillis(100))
			.ignoring(NoSuchElementException.class)
			.until(new Function<WebElement, Boolean>(){
				public Boolean apply(WebElement countdown) {
					return countdown.getText().endsWith("00");
				}
			});
	
		}
		
	public WebElement getWebElement (By locator) {
		//khai bao va khoi tao fluent wait
		FluentWait<WebDriver> wait = new FluentWait <WebDriver>(driver)
				// tong thoi gian cho la bao nhieu giay
				.withTimeout(Duration.ofSeconds(15))
				// thoi gian de lap lai la bao nhieu s
				.pollingEvery(Duration.ofSeconds(1))
				// neu sau moi lan lap ma gap exception thi se ignore
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
				}
		});
		return element;
	}
	
	public void clickToElement (By locator) {
		FluentWait<WebDriver> wait = new FluentWait <WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofSeconds(1))
				.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver,WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
				}
		});
		element.click();;
	}
	
	public boolean waitForElementAndDisplayed (By locator) {
		WebElement element = getWebElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
				.withTimeout(Duration.ofSeconds(15))
				.pollingEvery(Duration.ofMillis(100))
				.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement,Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
				}
		});	
		return isDisplayed;
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