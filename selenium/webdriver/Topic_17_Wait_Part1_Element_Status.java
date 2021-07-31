package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
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

public class Topic_17_Wait_Part1_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	
	By confirmEmailTextbox = By.xpath("//input[@name='reg_email_confirmation__']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait (driver, 15);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
	}

	public void TC_01_Visible() {

		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		driver.findElement(By.name("reg_email__")).sendKeys("abcd@gmail.com");
		
		//Cho cho element dc hien thi trong UI va Dom
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));
	}

	public void TC_02_Invisible_01() {
		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//Case 1: Ko hiển thị trên UI nhưng vẫn có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));

	}

	public void TC_02_Invisible_02() {
		driver.navigate().refresh();
		//Case 2: Ko hiển thị trên UI và ko có trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(confirmEmailTextbox));
	}
	
	public void TC_03_Presence() {

		driver.findElement(By.xpath("//a[text()='Tạo tài khoản mới']")).click();
		
		//presence - co trong Dom - ko co UI
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(confirmEmailTextbox));
		
		//presence - co trong Dom - co UI
		
		driver.findElement(By.name("reg_email__")).sendKeys("abcd@gmail.com");
	
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(confirmEmailTextbox));

	}
	@Test
	public void TC_04_Staleness() {
		
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		driver.findElement(By.id("SubmitCreate")).click();
		
		WebElement errorMessage = driver.findElement(By.id("create_account_error"));
		
		driver.navigate().refresh();
		
		explicitWait.until(ExpectedConditions.stalenessOf(errorMessage));

	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
	
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}