package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
	
	String[] firstMonth = {"February", "April", "May"};
	String[] secondMonth = {"February", "April", "May", "July"};
	String[] allMonth = {"Month",
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
			"December"};

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		driver = new ChromeDriver();
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
	//@Test
	public void TC_04_Angular() {
		//System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		//driver = new ChromeDriver();
		//driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		//sleepInSecond(3);
		
		//driver.findElement(By.xpath("//span[@aria-owns='games_options']")).click();
		//driver.findElement(By.xpath("//ul[@id='games_options']/li[text()='Basketball']")).click();
		//selectItemInCustomDropdow("//span[@aria-owns='games_options']","//ul[@id='games_options']/li]", "Football");
		//sleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]")).isDisplayed());
		
		//selectItemInCustomDropdow("//span[@aria-owns='games_options']","//ul[@id='games_options']/li]", "Hockey");
		//sleepInSecond(3);
		//Assert.assertTrue(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]")).isDisplayed());
		driver.get("https://valor-software.com/ng2-select/");
		selectItemInCustomDropdow("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//a[@class='dropdown-item']//div", "Amsterdam");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']//following-sibling::ng-select")).getText(), "Amsterdam");
		
	}
	
	//@Test
	public void TC_05_Editable_01() {
		driver.get("https://valor-software.com/ng2-select/");
		enterAndSelectItemInCustomDropdow("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input","//tab[@heading='Single']//a[@class='dropdown-item']//div", "Amsterdam");
		sleepInSecond(2);
		//Assert.assertEquals(driver.findElement(By.xpath("//h3[text()='Select a single city']//following-sibling::ng-select")).getText(), "Amsterdam");
		enterAndSelectItemInCustomDropdow("//tab[@heading='Single']//i[@class='caret pull-right']", "//tab[@heading='Single']//input","//tab[@heading='Single']//a[@class='dropdown-item']//div", "Berlin");
		
	}
	//@Test
	public void TC_05_Editable_02() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		enterAndTabItemInCustomDropdow("//input[@class='search']", "Afghanistan");
		sleepInSecond(2);
		enterAndTabItemInCustomDropdow("//input[@class='search']", "Andorra");
		sleepInSecond(2);
		
		
		
	}
	
	@Test
	public void TC_06_Multiple() {
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div [@class='ms-drop bottom'])[1]//li//span", firstMonth);
		sleepInSecond(1);
		Assert.assertTrue(areItemSelected(firstMonth));
		driver.navigate().refresh();
		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div [@class='ms-drop bottom'])[1]//li//span", secondMonth);
		sleepInSecond(1);
		Assert.assertTrue(areItemSelected(secondMonth));
		driver.navigate().refresh();
		selectMultipleItemInDropdown("(//button[@class='ms-choice'])[1]", "(//div [@class='ms-drop bottom'])[1]//li//span", allMonth);
		sleepInSecond(1);
		Assert.assertTrue(areItemSelected(allMonth));
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

	public void enterAndSelectItemInCustomDropdow (String parentXpath, String textboxXpath, String childXpath, String expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSecond(1);
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		List <WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		
		//4. duyệt qua từng item
		for (WebElement item : allItems ) {//trim loại bỏ kí tự khoảng trắng xuống dòng
			if (item.getText().trim().equals(expectedItem)){
				if (item.isDisplayed()) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					sleepInSecond(1);
				item.click();
				break;
				}
				}
	}
	}
	
	public void enterAndTabItemInCustomDropdow (String textboxXpath, String expectedItem) {
		
		driver.findElement(By.xpath(textboxXpath)).sendKeys(expectedItem);
		sleepInSecond(1);
		driver.findElement(By.xpath(textboxXpath)).sendKeys(Keys.TAB);
		sleepInSecond(1);
		
	}
	public void selectMultipleItemInDropdown(String parentXpath, String childXpath, String[] expectedItem) {
		driver.findElement(By.xpath(parentXpath)).click();
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childXpath)));
		List <WebElement> allItems = driver.findElements(By.xpath(childXpath));
		
		for (WebElement childElement: allItems) {
			for (String item: expectedItem) {
				if (childElement.getText().equals(item)) {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", childElement);
					sleepInSecond(1);
					
					childElement.click();
					sleepInSecond(1);
					
					List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " +itemSelected.size());
					if(expectedItem.length == itemSelected.size()) {
						break;
					}
				}
			}
		}
		
	}
	
	public boolean areItemSelected(String[] months) {
		List <WebElement> itemSelected = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberItemSelected = itemSelected.size();
		
		String itemSelectedText = driver.findElement(By.xpath("(//button[@class='ms-choice']//span)[1]")).getText();
		System.out.println("Text da chon = " + itemSelectedText);
		
		if (numberItemSelected <=3 && numberItemSelected >0) {
			boolean status = true;
			for (String item: months) {
				if (!itemSelectedText.contains(item)){
					status = false;
					return status;
				}
			} 
			return status;
		}else if (numberItemSelected == 12) {
			
			return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='All selected'][1]")).isDisplayed();
			
			
		}else if (numberItemSelected > 3 && numberItemSelected < 12) {
			
			return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='" + numberItemSelected + " of 12 selected']")).isDisplayed();
		
			
		}else {
			return false;
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