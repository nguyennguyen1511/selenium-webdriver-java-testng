package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	

	@Test
	public void TC_01_Run_On_Firefox() {
		//older firefox version (47.0.2 -- chi can new firefox)
		driver = new FirefoxDriver();
		
		//(>=48) lastest version 
		//System.setProperty("webdriver.gecko.driver","//geckodriver_path");
		//driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://demo.guru99.com/v4/");
		driver.quit();

	}

	@Test
	public void TC_02_Run_On_Chrome() {
		System.setProperty("webdriver.chrome.driver", projectPath + "/browser/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.quit();

	}

	@Test
	public void TC_03_Run_On_Edge_Chromium() {
		System.setProperty("webdriver.edge.driver", projectPath + "/browser/msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.quit();

	}

	@AfterClass
	public void afterClass() {
		
	}
	
    public void sleepInSecond(long timeoutInSecond){
    	try{
    		Thread.sleep(timeoutInSecond *1000);
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
}