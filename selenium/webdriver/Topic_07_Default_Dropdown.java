package webdriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class Topic_07_Default_Dropdown {
	WebDriver driver;
	Select select;
	JavascriptExecutor jsExecutor; 
	List <String> expectedItemText;
	String firstName, lastName, emailAddress, companyName, day, month, year;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		firstName = "Automation";
		lastName = "FC";
		emailAddress = "autofc" + generateEmail();
		companyName = "vietnam";
		day = "10";
		month = "August";
		year = "1994";
		
		expectedItemText = new ArrayList<>(
				Arrays.asList("Month",
						"January",
						"February",
						"March",
						"April",
						"May",
						"June",
						"July",
						"August",
						"September",
						"October",
						"November",
						"December")
				);
		
	}

	//@Test
	public void TC_01_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		sleepInSecond(2);
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		
		//Chon item trong dropdown
		select = new Select (driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(day);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
		//Dung de verify xem trong dropdow nay co tong cong bao nhieu item
		//Assert.assertEquals(select.getOptions().size(),32);
		
		//Verify dropdown co cho chon nhieu item cung luc
		//Assert.assertFalse (select.isMultiple());
		
		select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(month);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
		
		select = new Select (driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(year);
		Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
		
		driver.findElement(By.id("Email")).sendKeys(emailAddress);
		driver.findElement(By.id("Company")).sendKeys(companyName);
		
		driver.findElement(By.id("Password")).sendKeys("123456");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");
		
		sleepInSecond(2);
		
		clickByJS(By.id("register-button"));
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),"Your registration completed");
		
		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Company")).getAttribute("value"), companyName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), emailAddress);
		
		select = new Select (driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),day);
		
		select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),month);
		
		select = new Select (driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(),year);
	}

	@Test
	public void TC_02() {
		driver.get("https://demo.nopcommerce.com/");
		
		driver.findElement(By.className("ico-register")).click();
		select = new Select (driver.findElement(By.name("DateOfBirthMonth")));
		
		List <WebElement> allItems = select.getOptions(); 
		List <String> allItemsText = new ArrayList<>();
		
		//Duyet qua all items co trong list
		
		for (WebElement item: allItems) {
			//System.out.println(item.getText());
			allItemsText.add(item.getText());
		}
		
		Assert.assertEquals(expectedItemText, allItemsText);

	}

	@Test
	public void TC_03() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) +"@mail.vn";
	}
	
	public void clickByJS(By by) {
		jsExecutor.executeScript("arguments[0].click();", driver.findElement(by));
	}
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}