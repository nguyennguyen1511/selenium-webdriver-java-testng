package webdriver;

import java.io.File;
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

public class Topic_16_Upload_File_Part_1 {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String image1 = "1.jpg";
	String image2 ="12-greenery_greeny_bug.jpg";
	String image3 ="grapefruit-slice-332-332.jpg";
	
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	
	String image1FilePath = uploadFilePath + image1;
	String image2FilePath = uploadFilePath + image2;
	String image3FilePath = uploadFilePath + image3;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	
	public void TC_01_One_File() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1FilePath);
		sleepInSecond(1);
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image2FilePath);
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image3FilePath);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image3+"']")).isDisplayed());
		
		List <WebElement> startButton = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
			
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image2+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image3+"']")).isDisplayed());

	}

	@Test
	public void TC_02_Multiple_Files() {
driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1FilePath + "\n" + image2FilePath + "\n" +image3FilePath);
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image2+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+image3+"']")).isDisplayed());
		
		List <WebElement> startButton = driver.findElements(By.cssSelector("table button[class*='start']"));
		for (WebElement start : startButton) {
			start.click();
			sleepInSecond(2);
			
		}
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image2+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+image3+"']")).isDisplayed());

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