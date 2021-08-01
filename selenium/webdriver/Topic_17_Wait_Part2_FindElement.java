package webdriver;

import java.util.Date;
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


public class Topic_17_Wait_Part2_FindElement {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.facebook.com");
	}
	/* - chiu anh huong timeout boi thang implicityWait
	 * - 30s la giay toi da cho element co trong Dom
	 * - 3s tim thay, thi ko can cho nua, di tiep step tiep theo
	 * - 30s ko tim thay: tuy vao findElement/Elements
	 * - trong thoi gian wait 0.5s tim 1 lan
	 */

	@Test
	public void TC_01_Find_Element() {
		//WebElement emailTexbox = driver.findElement(By.xpath(""));
		//1. tim thay 1 node
		driver.findElement(By.id("email")).sendKeys("abcd@gmail.com");
		
		//2.ko tim thay > tim het timeout > throw exception > fail testcase > ko run step tiep theo
		driver.findElement(By.id("tiki")).isDisplayed();
		
		//3. hon 1 node > thao tac vs node dau tien (ko quan tam all node sau)
		driver.findElement(By.cssSelector("div#pageFooter a")).click();

	}

	@Test
	public void TC_02_Find_Elements() {
		List <WebElement> elements;
		//1. tim thay 1 node > tra ve list co size =1
		elements = driver.findElements(By.id("email"));
		
		//2.ko tim thay > tim het timeout > ko fail testcase > run step tiep theo > tra ve list co size = 0
		elements = driver.findElements(By.id("tiki"));
				
		//3. hon 1 node > thao tac vs node dau tien (ko quan tam all node sau) > tra ve list co size = n
		elements = driver.findElements(By.cssSelector("div#pageFooter a"));

	}

	@Test
	public void TC_03() {

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