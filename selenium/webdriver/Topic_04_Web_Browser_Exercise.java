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

public class Topic_04_Web_Browser_Exercise {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com");
	}

	@Test
	public void TC_01_Verify_URL() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify_Title() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");


	}

	@Test
	public void TC_03_Verify_Navigation() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.navigate().back();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Customer Login");
		
		driver.navigate().forward();
		sleepInSecond(3);
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
		

	}
	
	@Test
	public void TC_04_Verify_PageSource() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();
		
		String currentPageSource = driver.getPageSource();
		Assert.assertTrue(currentPageSource.contains("Login or Create an Account"));
		
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
	//khởi tạo lại giá trị mới
		currentPageSource = driver.getPageSource();
		Assert.assertTrue(currentPageSource.contains("Create an Account"));
		

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