package testng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import listernerConfig.TestListener;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

@Listeners(TestListener.class)	 
public class Topic_07_Depend {
	//theo luồng, case trên phụ thuộc case dưới
	
	  @Test
	  public void Create_New_Account() {

	  }
	  
	  @Test(dependsOnMethods = "Create_New_Account")
	  public void View_Account() {

	  }
	  
	  @Test(dependsOnMethods = "View_Account")
	  public void Edit_Account() {

	  }
	  
	  @Test(dependsOnMethods = "Create_New_Account")
	  public void Move_Account() {
		  Assert.assertTrue(false);

	  }
	  
	  @Test(dependsOnMethods = "Create_New_Account")
	  public void Delete_Account() {
		  
	  }

}
  