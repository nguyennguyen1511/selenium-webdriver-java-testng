package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_Part_2 {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String image1 = "1.jpg";
	String image2 ="12-greenery_greeny_bug.jpg";
	String image3 ="grapefruit-slice-332-332.jpg";
	
	String uploadFilePath = projectPath + File.separator + "uploadFiles" + File.separator;
	
	String image1FilePath = uploadFilePath + image1;
	String image2FilePath = uploadFilePath + image2;
	String image3FilePath = uploadFilePath + image3;
	
	String uploadAutoITPath = projectPath + "\\autoIT\\uploadFile_autoIT.exe";
	String uploadMultiAutoITPath = projectPath + "\\autoIT\\firefoxUploadMultiple.exe";

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		driver = new ChromeDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

	}
	public void TC_01_AutoIT() throws IOException {
		//clickToElementByJS(driver.findElement(By.cssSelector("span.btn-success")));
		driver.findElement(By.cssSelector("span.btn-success")).click();
		Runtime.getRuntime().exec(new String[] {uploadAutoITPath, image1FilePath});
		sleepInSecond(2);
		
		//clickToElementByJS(driver.findElement(By.cssSelector("span.btn-success")));
		driver.findElement(By.cssSelector("span.btn-success")).click();
		Runtime.getRuntime().exec(new String[] {uploadAutoITPath, image2FilePath});
		sleepInSecond(2);
		
		//clickToElementByJS(driver.findElement(By.cssSelector("span.btn-success")));
		driver.findElement(By.cssSelector("span.btn-success")).click();
		Runtime.getRuntime().exec(new String[] {uploadAutoITPath, image3FilePath});
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
	public void TC_02_Multiple_Files() throws IOException {
		
		driver.findElement(By.cssSelector("span.btn-success")).click();
		Runtime.getRuntime().exec(new String[] {uploadMultiAutoITPath, image1FilePath, image2FilePath, image3FilePath});
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
	public void TC_03_Java_Robot() throws AWTException{
		
		//specify the file location with extension 
		StringSelection select = new StringSelection(image1FilePath);
		
		//copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);
		driver.findElement(By.cssSelector("span.btn-success")).click();
		sleepInSecond(1);
		
		Robot robot = new Robot();
		sleepInSecond(2);

		
		//Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		// nhaan xuong ctrl-v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		
		//Nhan nha trl-v
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		sleepInSecond(2);
		
		// nhaan xuong ctrl-v
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
	}
	public void clickToElementByJS(WebElement element) {
		jsExecutor.executeScript("arguments[0].click();", element);
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