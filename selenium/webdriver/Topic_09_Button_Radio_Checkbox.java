package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Radio_Checkbox {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	boolean status;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	//@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertFalse(status);
		
		driver.findElement(By.cssSelector("#login_username")).sendKeys("0987666999");
		driver.findElement(By.cssSelector("#login_password")).sendKeys("123456");
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status);
		
		driver.navigate().refresh();
		
		driver.findElement(By.cssSelector(".popup-login-tab-login")).click();
		
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')",driver.findElement(By.cssSelector(".fhs-btn-login")));
		status = driver.findElement(By.cssSelector(".fhs-btn-login")).isEnabled();
		System.out.println("Button status = " + status);
		Assert.assertTrue(status);
		
		driver.findElement(By.cssSelector(".fhs-btn-login")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Số điện thoại/Email']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='popup-login-content']//label[text()='Mật khẩu']/following-sibling::div[@class='fhs-input-alert']")).getText(),"Thông tin này không thể để trống");
		
		//driver.navigate().refresh();
		//jsExecutor.executeScript("arguments[0].click();",driver.findElement(By.cssSelector("fhs-btn-login")));

	}

	//@Test
	public void TC_02_Default_Radio_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By rearCheckbox = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		
		checkToCheckbox_Radio(rearCheckbox);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(rearCheckbox).isSelected());
		
		uncheckToCheckbox(rearCheckbox);
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(rearCheckbox).isSelected());
		
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By radiotest = By.xpath("//label[text()='1.4 Petrol, 92kW']/preceding-sibling::input");
		checkToCheckbox_Radio(radiotest);
		
		Assert.assertTrue(driver.findElement(radiotest).isSelected());
	
	}
	//@Test
	public void TC_02_Default_Checkbox_SelectAll() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List <WebElement> checkboxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
		
		for (WebElement checkbox: checkboxes) {
			if (!checkbox.isSelected()) {
				checkbox.click();
				sleepInSecond(1);
			}
		}
		for (WebElement checkbox: checkboxes) {
			Assert.assertTrue(checkbox.isSelected());
		}
		
	}
	//@Test
	public void TC_04_Custom_Checkbox_Radiobutton() {
		driver.get("https://material.angular.io/components/radio/examples");
		By winterRadio = By.xpath("//input[@value='Winter']");
		
		//1. thẻ input bị ẩn không thể click được + có thể verify được
		//checkToCheckbox_Radio(winterRadio);
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//2. thẻ span có thể click được + không verify được do span ko the check selected
		By winterSpan = By.xpath("//span[contains(text(),'Winter')]");
		//driver.findElement(winterSpan).click();
		//Assert.assertTrue(driver.findElement(winterSpan).isSelected());
		
		//3. dùng span để click và input để verify => maintain nhieu chỗ locator
		//driver.findElement(winterSpan).click();
		//sleepInSecond(2);
		//Assert.assertTrue(driver.findElement(winterRadio).isSelected());
		
		//4. dùng js để click
		clickElementToJs(winterRadio);
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(winterRadio).isSelected());

	}
	
	@Test
	public void TC_05_Custom_Radio_Checkbox() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='false']")).isDisplayed());
		driver.findElement(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]")).click();
		//clickElementToJs(By.xpath("//div[@aria-label='Quảng Nam']/div[contains(@class,'exportInnerBox')]"));
		sleepInSecond(2);
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Quảng Nam' and @aria-checked='true']")).isDisplayed());

	}
	
	
	public void clickElementToJs(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	public void checkToCheckbox_Radio (By by) {
		WebElement checkbox = driver.findElement(by);
		if (!checkbox.isSelected()) {
			checkbox.click();
		}
	}
	
	public void uncheckToCheckbox (By by) {
		WebElement checkbox = driver.findElement(by);
		if (checkbox.isSelected()) {
			checkbox.click();
		}
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