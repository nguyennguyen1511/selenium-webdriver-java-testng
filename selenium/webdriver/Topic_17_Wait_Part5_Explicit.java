package webdriver;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_17_Wait_Part5_Explicit {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	By startButton = By.xpath("//div[@id='start']//button");
	By loadingIcon = By.xpath("//div[@id='loading']");
	By textHello = By.xpath("//div[@id='finish']");
	String image1 ="grapefruit-slice-332-332.jpg";
	
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	
	String image1FilePath = uploadFilePath + image1;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		
		
	}
	public void TC_01_Less_Than() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		explicitWait =  new WebDriverWait (driver, 15);
		//trước khi click thì wait cho tới khi button click được chua
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		//Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
		explicitWait =  new WebDriverWait (driver, 3);
		
		//wait cho loading icon biến mất (loading icon biến mất thì hello word dc hiển thị > case fail vì loading biến mất trong 5s mà wait set có 3s)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(textHello).isDisplayed());

	}
	public void TC_02_Enough() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		explicitWait =  new WebDriverWait (driver, 15);
		//trước khi click thì wait cho tới khi button click được chua
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		//Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
		explicitWait =  new WebDriverWait (driver, 5);
		
		//wait cho loading icon biến mất (loading icon biến mất thì hello word dc hiển thị > case fail vì loading biến mất trong 5s mà wait set có 3s)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(textHello).isDisplayed());


	}
	public void TC_03_Greater_Than() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		
		explicitWait =  new WebDriverWait (driver, 15);
		//trước khi click thì wait cho tới khi button click được chua
		explicitWait.until(ExpectedConditions.elementToBeClickable(startButton));
		driver.findElement(startButton).click();
		//Assert.assertEquals(driver.findElement(textHello).getText(), "Hello World!");
		explicitWait =  new WebDriverWait (driver, 15);
		
		//wait cho loading icon biến mất (loading icon biến mất thì hello word dc hiển thị > case fail vì loading biến mất trong 5s mà wait set có 3s)
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(loadingIcon));
		Assert.assertTrue(driver.findElement(textHello).isDisplayed());

	}
	public void TC_04_Ajax_Loading() {
		explicitWait =  new WebDriverWait (driver, 15);
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		WebElement textnotSeleted = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']"));
		Assert.assertEquals(textnotSeleted.getText(),"No Selected Dates to display.");
		
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()=1]")));
		
		driver.findElement(By.xpath("//a[text()=1]")).click();
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none;')]/div[@class='raDiv']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceholder1_Label1']")).getText(),"Sunday, August 1, 2021");
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a[text()=1]")));
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()=1]")).isDisplayed());
		
	}
	@Test
	public void TC_05_upload_File() {
		explicitWait = new WebDriverWait(driver, 15);
		driver.get("https://gofile.io/uploadFiles");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.uploadButton i.fa-upload")));
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1FilePath);
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='callout callout-success']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='callout callout-success']")).getText(),"Your files have been successfully uploaded");
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='rowUploadSuccess-showFiles']")));
		driver.findElement(By.xpath("//button[@id='rowUploadSuccess-showFiles']")).click();
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName' and text()='"+image1+"']")));
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='contentName' and text()='"+image1+"']")).isDisplayed());
		
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