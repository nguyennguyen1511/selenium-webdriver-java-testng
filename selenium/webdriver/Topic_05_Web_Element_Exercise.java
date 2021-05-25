package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element_Exercise {
	WebDriver driver;
	String firstName, lastName, emailAddress, password, fullName;
	By mailTextbox = By.id("mail");
	By educationText = By.id("edu");
	By radioUnder18 = By.id("under_18");
	By javaCheckbox = By.id("java");
	By passwordTextbox = By.id("password");
	By disableCheckbox = By.id("check-disbaled");
	By disableButton = By.id("button-disabled");

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "abcd";
		lastName ="abcd";
		emailAddress="abcd" + generateEmail();
		password="123456";
		fullName = firstName + " " +lastName;
	}

	//@Test
	public void TC_01_Create_New_Account() {
		driver.get("http://live.demoguru99.com/");
		sleepInSecond(3);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();

		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(emailAddress);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
				
		
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(), "Thank you for registering with Main Website Store.");
		
		//Dung ham isDisplay de kiem tra
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + fullName + "')]")).isDisplayed());
		
		Assert.assertTrue(driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p[contains(string(),'" + emailAddress + "')]")).isDisplayed());
		//Dung ham getText va verify contain
		
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
		System.out.println(contactInfo);
		
		Assert.assertTrue(contactInfo.contains(firstName));
		Assert.assertTrue(contactInfo.contains(emailAddress));
		
		driver.findElement(By.cssSelector(".skip-account")).click();
		driver.findElement(By.cssSelector("a[title ='Log Out']")).click();
		
	}

	//@Test
	public void TC_02_Login() {
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account'] ")).click();
		driver.findElement(By.cssSelector("#email ")).sendKeys(emailAddress);
		driver.findElement(By.cssSelector("#pass")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(), "Hello, " + fullName +"!");
	}

	//@Test
	public void TC_03_Display() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		
		if(isElementDisplayed(mailTextbox)) {
			senKeyToElement(mailTextbox, "Automation FC");
		}
		if(isElementDisplayed(educationText)) {
			senKeyToElement(educationText, "Automation FC");
		}
		if(isElementDisplayed(radioUnder18)) {
			clickToElement(radioUnder18);
		}
		
		//if(driver.findElement(By.xpath("//input[@id='mail']")).isDisplayed()){
		//	driver.findElement(By.xpath("//input[@id='mail']")).sendKeys("Automation FC");
		//	System.out.println("Mail textbox is displayed");
		//}else
		//{
		//	System.out.println("Mail textbox is not displayed");
		//}

	}
	//@Test
	public void TC_04_Selected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(radioUnder18);
		clickToElement(javaCheckbox);
		
		Assert.assertTrue(isElementSelected(radioUnder18));
		Assert.assertTrue(isElementSelected(javaCheckbox));
		
		clickToElement(radioUnder18);
		clickToElement(javaCheckbox);
		
		Assert.assertFalse(isElementSelected(javaCheckbox));
		Assert.assertTrue(isElementSelected(radioUnder18));
	}
	//@Test
	public void TC_05_Enabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(mailTextbox));
		Assert.assertTrue(isElementEnabled(radioUnder18));
		Assert.assertTrue(isElementEnabled(educationText));
		Assert.assertTrue(isElementEnabled(javaCheckbox));
		
		Assert.assertFalse(isElementEnabled(passwordTextbox));
		Assert.assertFalse(isElementEnabled(disableCheckbox));
		Assert.assertFalse(isElementEnabled(disableButton));
	}
	
	@Test
	public void TC_06_Register_Validate() {
		By passwordTextbox = By.id("new_password");
		By signUpButton = By.cssSelector("#create-account");
		By upperCaseCompleted = By.cssSelector(".uppercase-char.completed");
		By lowerCaseCompleted = By.cssSelector(".lowercase-char.completed");
		By numberCaseCompleted = By.cssSelector(".number-char.completed");
		By specialCaseCompleted = By.cssSelector(".special-char.completed");
		By morethan8CaseCompleted = By.cssSelector("li[class='8-char completed']");
		By newsletterCheckbox = By.id("marketing_newsletter");
		//maichimp: gui email cho hang loat tai khoan
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("automation@hotmail.net");
		driver.findElement(By.id("new_username")).sendKeys("automation");
		
		driver.findElement(passwordTextbox).sendKeys("AUTO");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("auto");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("123");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(numberCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("!@");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(specialCaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("automation");
		sleepInSecond(2);
		Assert.assertTrue(isElementDisplayed(lowerCaseCompleted));
		Assert.assertTrue(isElementDisplayed(morethan8CaseCompleted));
		Assert.assertFalse(isElementEnabled(signUpButton));
		
		driver.findElement(passwordTextbox).clear();
		driver.findElement(passwordTextbox).sendKeys("Automation@123");
		sleepInSecond(2);
		Assert.assertFalse(isElementDisplayed(upperCaseCompleted));
		Assert.assertFalse(isElementDisplayed(lowerCaseCompleted));
		Assert.assertFalse(isElementDisplayed(numberCaseCompleted));
		Assert.assertFalse(isElementDisplayed(specialCaseCompleted));
		Assert.assertFalse(isElementDisplayed(morethan8CaseCompleted));
		
		Assert.assertTrue(isElementEnabled(signUpButton));
		clickToElement(newsletterCheckbox);
		sleepInSecond(2);
		Assert.assertTrue(isElementSelected(newsletterCheckbox));
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	public boolean isElementDisplayed (By by) {
		
		if (driver.findElement(by).isDisplayed()) {
			System.out.println(by +" is displayed");
			return true;
		}else {
			System.out.println(by + " is not displayed");
			return false;
		}
	}
    public boolean isElementSelected (By by) {
		
		if (driver.findElement(by).isSelected()) {
			System.out.println(by +" is selected");
			return true;
		}else {
			System.out.println(by + " is not selected");
			return false;
		}
	}
    public boolean isElementEnabled (By by) {
		
		if (driver.findElement(by).isEnabled()) {
			System.out.println(by +" is enabled");
			return true;
		}else {
			System.out.println(by + " is disbled");
			return false;
		}
	}
	public void senKeyToElement(By by, String value) {
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(value);
		
	}
	public void clickToElement(By by) {
		driver.findElement(by).click();
		
	}
	public String generateEmail() {
		Random rand = new Random();
		return rand.nextInt(9999) +"@mail.vn";
	}
	
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}