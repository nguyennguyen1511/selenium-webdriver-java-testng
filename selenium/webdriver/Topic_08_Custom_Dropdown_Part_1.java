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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown_Part_1 {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor; //inject 1 javascript code
	
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		
		explicitWait = new WebDriverWait (driver, 15);
		
		//ép kiểu ngầm định (nhỏ > lớn)
		//int price = 156000;
		//float size = price;
		//lớn > nhỏ
		//short sPrice = (short) price;
		
		//ép kiểu tường minh
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
	}

	//@Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		sleepInSecond(3);
		selectItemInCustomDropdow("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']")).isDisplayed());
		selectItemInCustomDropdow("//span[@id='number-button']", "//ul[@id='number-menu']//div", "15");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());

	}

	//@Test
	public void TC_02_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		sleepInSecond(3);
		selectItemInCustomDropdow("//div[@class='ui fluid selection dropdown']", "//div[@role='option']/span", "Elliot Fu");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Elliot Fu']")).isDisplayed());
		selectItemInCustomDropdow("//div[@class='ui fluid selection dropdown']", "//div[@role='option']/span", "Christian");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='divider text' and text()='Christian']")).isDisplayed());

	}

	//@Test
	public void TC_03_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		sleepInSecond(2);
		selectItemInCustomDropdow("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li", "First Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		selectItemInCustomDropdow("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li", "Second Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
		selectItemInCustomDropdow("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']/li", "Third Option");
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]")).isDisplayed());

	}
	@Test
	public void TC_04_Angular() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		sleepInSecond(3);
		
		//driver.findElement(By.xpath("//span[@aria-owns='games_options']")).click();
		//driver.findElement(By.xpath("//ul[@id='games_options']/li[text()='Basketball']")).click();
		selectItemInCustomDropdow("//span[@aria-owns='games_options']","//ul[@id='games_options']/li]", "Football");
		//sleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		//selectItemInCustomDropdow("//span[@aria-owns='games_options']","//ul[@id='games_options']/li]", "Hockey");
		sleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		
	}
	
	
	public void selectItemInCustomDropdow (String parentXpath, String childXpath, String expectedItem) {
		//1. click vào dropdow
		driver.findElement(By.xpath(parentXpath)).click();
		
		//2. chờ load ra hết tất cả item
		//3. lấy xong lưu hết tất cả items vào list
		
		List <WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//List <WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		//4. duyệt qua từng item
		for (WebElement item : allItems ) {//trim loại bỏ kí tự khoảng trắng xuống dòng
			if (item.getText().trim().equals(expectedItem)){
				if (!item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				}
				item.click();
				break;
					
				}
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