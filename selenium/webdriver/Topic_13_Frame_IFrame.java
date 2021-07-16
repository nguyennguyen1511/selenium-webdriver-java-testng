package webdriver;

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

public class Topic_13_Frame_IFrame {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		//driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
	}


	public void TC_01_IFrame() {
		driver.get("https://kyna.vn/");
		scrollToBottomPage();
		// index (thay đổi vị trí)
		//driver.switchTo().frame(0);
		
		// Name or ID
		
		// element
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.fanpage iframe")));
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']/parent::div/following-sibling::div")).getText(), "169K lượt thích");
		// trở về parent
		driver.switchTo().defaultContent();
		
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));
		
		driver.findElement(By.cssSelector("div.button_bar")).click();
		
		
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSecond(2);
		//driver.findElement(By.cssSelector("input.input_name")).sendKeys("Nguyen");
		Assert.assertEquals(driver.findElement(By.cssSelector("input.input_name+div.error_message")).getText(), "Tên của bạn chưa được nhập");
		Assert.assertEquals(driver.findElement(By.cssSelector("select#serviceSelect+div.error_message")).getText(), "Bạn chưa chọn dịch vụ hỗ trợ");
		
		driver.switchTo().defaultContent();
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("Excel");
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSecond(2);
		List <WebElement> resultSearch = driver.findElements(By.cssSelector("section div.content>h4"));
		
		Assert.assertEquals(resultSearch.size(), 9);
		for (WebElement course : resultSearch) {
			//Assert.assertTrue(course.getText().contains("Excel"));
			Assert.assertTrue(course.getText().toLowerCase().contains("excel"));
			Assert.assertTrue(course.getText().toLowerCase().matches("(.*)excel(.*)"));
		}
		
	}

	@Test
	public void TC_02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		driver.switchTo().frame("login_page");
		
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("automationfc");
		driver.findElement(By.cssSelector("table.lForm img[alt='continue']")).click();
	
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("input[name='fldPassword']")).isDisplayed());
		
		driver.switchTo().defaultContent();
		driver.switchTo().frame("footer");
		
		driver.findElement(By.xpath("//p[@class='footer']//a[text()='Terms and Conditions']")).click();

	}

	@Test
	public void TC_03() {
		
	}
	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0, document.body.scrollHeight)");
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