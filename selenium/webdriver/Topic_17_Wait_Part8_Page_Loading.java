package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Wait_Part8_Page_Loading {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait (driver,30);
		jsExecutor = (JavascriptExecutor) driver;
	
	}
	@Test
	public void TC_01() {
		driver.get("https://opensource-demo.orangehrmlive.com/");
		
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		driver.findElement(By.cssSelector("input#btnLogin")).click();
		
		Assert.assertTrue(isJQueryAndPageLoadedSuccess(driver));
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='3 month(s)']")).isDisplayed());
		
		driver.findElement(By.cssSelector("a#menu_pim_viewPimModule")).click();
		Assert.assertTrue(isJQueryAndPageLoadedSuccess(driver));
		
		driver.findElement(By.cssSelector("input#empsearch_employee_name_empName")).sendKeys("Peter Mac");
		
		driver.findElement(By.cssSelector("input#searchBtn")).click();
		
		Assert.assertTrue(isJQueryAndPageLoadedSuccess(driver));
		
		Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='odd']//a[text()='Peter Mac']")).isDisplayed());
	}
	
	public boolean isJQueryLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return(window.jQury!=null)&(jQuery.active===0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	public boolean isJQueryAndPageLoadedSuccess(WebDriver driver) {
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return((Long) jsExecutor.executeScript("return jQuery.active")==0);
				} catch (Exception e) {
					return true;
				}
			}
	};
	ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
		@Override
		public Boolean apply(WebDriver driver) {
			return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
		}
	};
	
	return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
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