package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic_06_Loop {
 
 WebDriver driver;
 String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
  @BeforeClass
  public void beforClass() {
	  
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

  }
  
  @Test(invocationCount = 3)
  public void TC_01_LoginToSystem(){
	  driver.get("http://live.demoguru99.com/index.php/customer/account/login");
	  
	  driver.findElement(emailTextbox).sendKeys("selenium_11_0" +generateEmail());
	  driver.findElement(passwordTextbox).sendKeys("111111");
	  driver.findElement(loginButton).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_0"));
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();

  }
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(3) +"@gmail.com";
	}
	
  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
  