package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Method {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
	}

	@Test
	public void TC_01_Web_Element() {
		// tìm 1 element
		driver.findElement(By.id(""));
		
		//tìm nhiều elements
		driver.findElements(By.id(""));
		
		//nếu thao tác 1 lần thì ko cần khai báo biến
		driver.findElement(By.id("small-searchterms")).sendKeys("Apple");
		
		// nếu thao tác nhiều lần nên khai báo biến
		WebElement searchTextbox = driver.findElement(By.id("small-searchterms"));
		searchTextbox.clear();
		searchTextbox.sendKeys("Apple");
		searchTextbox.getAttribute("value");
		
		// Đếm xem có bao nhiêu element thỏa mãn điều kiện
		//verify số lượng element trả về như mong đợi
		//thao tác với tất cả các loại element giống nhau trong 1 màn hình
		List <WebElement> checkboxes =driver.findElements(By.xpath("//div[@class='input']/input[not(@type='checkbox')]"));
		
		// Lấy ra số lượng
		checkboxes.size();
		
		Assert.assertEquals(checkboxes.size(), 6);
		
		//Textbox Textarea / Editable dropdown
		//Dữ liệu được toàn vẹn
		searchTextbox.clear();
		searchTextbox.sendKeys("Apple");
		
		//button, link, radio, checkbox, custom dropdow
		searchTextbox.click();
		
		// các hàm có tiền tố bắt đầu bằng get luôn luôn trả về dữ liệu
		searchTextbox.getAttribute("");
		
		// Lấy ra giá trị của các thuộc tính CSS thường dùng để test giao diện
		//Font, size, color, background...
		
		searchTextbox.getCssValue("backgroud-color");
		
		//Lấy ra tọa độ của element so với page hiện tại (get ở ngoài)
		searchTextbox.getLocation();
		
		//Lấy ra kích thước rộng cao (bên trong )
		searchTextbox.getSize();
		
		//location+size
		searchTextbox.getRect();
		
		
		//chụp hình lỗi
		searchTextbox.getScreenshotAs(OutputType.FILE);
		
		searchTextbox.getTagName();
		
		// các hàm có tiền tố là is thì trả về true/false
		//true hiển thị
		//false ko hiển thị
		searchTextbox.isDisplayed();

	}

	@Test
	public void TC_02() {

	}

	@Test
	public void TC_03() {

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