package webdriver;

import java.util.Date;
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

public class Topic_17_Wait_Part6_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;
	WebElement element;
	String projectPath = System.getProperty("user.dir");


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		
	}

	public void TC_01_Element_Found_implicit_explicit() {
		
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait (driver, 3);

		System.out.println("Start Implicit: " + getDateTimeNow());
		driver.findElement(By.cssSelector("input#email"));
		System.out.println("End Implicit: " + getDateTimeNow());
		
		System.out.println("Start Explicit: " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));
		System.out.println("End Explicit: " + getDateTimeNow());
	}
	public void TC_02_1_Element_Not_Found_implicit() {
		
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println("Start Implicit: " + getDateTimeNow());
		try {
			driver.findElement(By.cssSelector("input#testing"));
		} finally {

			System.out.println("End Implicit: " + getDateTimeNow());

		}
	}
	public void TC_02_2_Element_Not_Found_explicit() {
		driver.get("https://www.facebook.com");

		explicitWait = new WebDriverWait (driver, 3);
		System.out.println("Start Explicit: " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#testing")));
		}finally {
			System.out.println("End Explicit: " + getDateTimeNow());
		}
	}
	public void TC_02_3_Element_Not_Found_explicit_implicit() {
		//implicit >explicit (implicit ch??? ???nh h?????ng cho findElement/ explicit ???nh h?????ng cho tr???ng th??i c???a element)
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait (driver, 3);

		System.out.println("Start Implicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i implicit
		driver.findElement(By.cssSelector("input#tiki"));
		}catch (Exception e1) {
		}
		System.out.println("End Implicit: " + getDateTimeNow());
		
		System.out.println("Start Explicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i c??? implicit v?? explicit v?? trong h??m visibilityofelementlocated c?? h??m findelement
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("End Explicit: " + getDateTimeNow());
		
		//implicit = explicit (implicit ch??? ???nh h?????ng cho findElement/ explicit ???nh h?????ng cho tr???ng th??i c???a element)
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait (driver, 5);


		System.out.println("Start Implicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i implicit
		driver.findElement(By.cssSelector("input#tiki"));
		}catch (Exception e1) {
		}
		System.out.println("End Implicit: " + getDateTimeNow());
		
		System.out.println("Start Explicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i c??? implicit v?? explicit v?? trong h??m visibilityofelementlocated c?? h??m findelement
		element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("End Explicit: " + getDateTimeNow());
		
		
		//implicit < explicit (implicit ch??? ???nh h?????ng cho findElement/ explicit ???nh h?????ng cho tr???ng th??i c???a element)
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    explicitWait = new WebDriverWait (driver, 8);


		System.out.println("Start Implicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i implicit
		driver.findElement(By.cssSelector("input#tiki"));
		}catch (Exception e1) {
		}
		System.out.println("End Implicit: " + getDateTimeNow());
		
		System.out.println("Start Explicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i c??? implicit v?? explicit v?? trong h??m visibilityofelementlocated c?? h??m findelement
		element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("End Explicit: " + getDateTimeNow());
	}
	public void TC_02_4_Element_Not_Found_explicit_implicit() {
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait (driver, 3);

		System.out.println("Start Implicit: " + getDateTimeNow());
		try {
		//???nh h?????ng b???i implicit
			driver.findElement(By.cssSelector("input#tiki"));
		}catch (Exception e1) {
			
		}
			System.out.println("End Implicit: " + getDateTimeNow());
		
			System.out.println("Start Explicit: " + getDateTimeNow());
		try {
			//???nh h?????ng b???i c??? implicit v?? explicit v?? trong h??m visibilityofelementlocated c?? h??m findelement
			// nh???n tham s??? l?? By
			// nh???n tham s??? c???a c??? 2 c?? trong h??m visibilityOfElementLocated
			// throw ra exception: TimeoutException (explicit)
			element = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tiki")));
			//nh???n tham s??? l?? webElement
			// ch???y t??? trong ra: findElement tr?????c > nh???n timeout c???a implicit
			// run h???t 5s > fail test case
			// throw ra exception: NoSuchElement (implicit)
			element = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("input#tiki"))));
		}catch (Exception e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		System.out.println("End Explicit: " + getDateTimeNow());
		
	}

	@AfterClass
	public void afterClass() {
		//driver.quit();
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