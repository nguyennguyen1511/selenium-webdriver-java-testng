package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class Topic_05_Parameter {
 
 WebDriver driver;
 String projectPath = System.getProperty("user.dir");
	By emailTextbox = By.xpath("//*[@id='email']");
	By passwordTextbox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	

  @Parameters({"browser", "url"})
  @BeforeClass
  public void beforClass(String browserName, String urlValue) {
	  	if (browserName.equals("firefox")){
			System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
			driver = new FirefoxDriver();
	  	}else if (browserName.equals("chrome"))  {
			System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
			driver = new ChromeDriver();
	  	}else if  (browserName.equals("edge"))  {
			System.setProperty("webdriver.edge.driver", projectPath + "/browser/msedgedriver.exe");
			driver = new EdgeDriver();
	  	}else {
	  		throw new RuntimeException("Please input correct the browser name");
	  	}
	  	  
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.get(urlValue);

  }
  
  @Test()
  public void TC_01_LoginToSystem(){

	  
	  driver.findElement(emailTextbox).sendKeys("selenium_11_01@gmail.com");
	  driver.findElement(passwordTextbox).sendKeys("111111");
	  driver.findElement(loginButton).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains("selenium_11_01@gmail.com"));
	  driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
	  driver.findElement(By.xpath("//a[text()='Log Out']")).click();

  }
  

  
  @AfterClass
  public void afterClass() {
	  driver.quit();
  }
}
  