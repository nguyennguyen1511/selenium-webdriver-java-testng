package webdriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Window_Tab {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	Alert alert;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath +"/browser/geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void TC_01_Windows() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//Get ra ID của active window/tab > 1 địa chỉ
		String homeID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		//switchToWindowsByID(homeID);
		
		//driver.findElement(By.cssSelector("#email")).sendKeys("abc");
		//sleepInSecond(2);
		//String childID = driver.getWindowHandle();
		//switchToWindowsByID(childID);
		//sleepInSecond(1);
		//Assert.assertTrue(driver.findElement(By.xpath("//a[text()='FACEBOOK']")).isDisplayed());
		
		switchToWindowsByTitle("Facebook - Đăng nhập hoặc đăng ký");
		driver.findElement(By.cssSelector("#email")).sendKeys("abc");
		sleepInSecond(1);
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@id='name']")).sendKeys("AutoAbc");
		
		
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		sleepInSecond(2);
		switchToWindowsByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		driver.findElement(By.xpath("//input[@data-view-id='main_search_form_input']")).sendKeys("Samsung Note Utral 20");
		driver.findElement(By.xpath("//button[@data-view-id='main_search_form_button']")).click();
		
		switchToWindowsByTitle("SELENIUM WEBDRIVER FORM DEMO");
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//a[text()='LAZADA']")).click();
		sleepInSecond(2);
		switchToWindowsByTitle("Shopping online - Buy online on Lazada.vn");
		driver.findElement(By.xpath("//input[@id='q']")).sendKeys("Samsung Note Utral 20");
		driver.findElement(By.xpath("//button[text()='SEARCH']")).click();
		
		closeAllWindowWithoutParent(homeID);


	}


	public void TC_02_Window_Tab_2() {
		driver.get("https://kyna.vn/");
		scrollToBottomPage();
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='facebook']")).click();
		sleepInSecond(2);
		switchToWindowsByTitle("Kyna.vn - Trang chủ | Facebook");
		driver.findElement(By.xpath("//table[@role='presentation']//input[@id='email']")).sendKeys("abcd");
		
		switchToWindowsByTitle("Kyna.vn - Học online cùng chuyên gia");
		
		driver.findElement(By.xpath("//div[@class='hotline']//img[@alt='youtube']")).click();
		sleepInSecond(2);
		switchToWindowsByTitle("Kyna.vn - YouTube");
		
		driver.findElement(By.xpath("//input[@id='search']")).sendKeys("khoa hoc");
		driver.findElement(By.xpath("//button[@id='search-icon-legacy']")).click();
		
        closeAllWindowWithoutParent(parentID);
		
	}

	@Test
	public void TC_03_Window_Tab_3() {
		driver.get("http://live.demoguru99.com/index.php/");
		String parentID = driver.getWindowHandle();
		
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		sleepInSecond(2);
		
		driver.findElement(By.cssSelector(".products-grid>li:first-child>div>div>ul>li>.link-compare")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product Sony Xperia has been added to comparison list.");
		sleepInSecond(1);
		driver.findElement(By.cssSelector(".products-grid>li:nth-child(2)>div>div>ul>li>.link-compare")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(), "The product IPhone has been added to comparison list.");
		sleepInSecond(1);
		driver.findElement(By.xpath("//button[@title='Compare']")).click();
		sleepInSecond(2);
		switchToWindowsByID(parentID);
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
		closeAllWindowWithoutParent(parentID);
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		//đợi alert + switch qua
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(5);
		alert.accept();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']")).getText(),"The comparison list was cleared.");

	}
	//2 windows/tab
	public void switchToWindowsByID(String parentID) {
		Set <String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
				if (!id.equals(parentID)) {
						driver.switchTo().window(id);
					}
				}
				
	}
	//nhiều hơn 2
	public void switchToWindowsByTitle(String expectedTitle) {
		Set <String> allIDs = driver.getWindowHandles();

		for (String id : allIDs) {
			driver.switchTo().window(id);
			String windowTitle = driver.getTitle();
				if (windowTitle.equals(expectedTitle)) {
						break;
					}
				}
				
	}
	public void closeAllWindowWithoutParent (String parentID) {
		Set <String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {		
				if (!id.equals(parentID)) {
					driver.switchTo().window(id);
					driver.close();
					}
				}
		driver.switchTo().window(parentID);
		sleepInSecond(1);
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