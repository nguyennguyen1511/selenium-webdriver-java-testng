package testng;

import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_03_Priority_Enabled {
	//sort alphabet
	//0> 9 A > Z
	// nếu đặt prioty thì mắc công quản ly, và xét priority > nên đặt test case theo alphabet để khi run TC sẽ run theo thứ tự mong muốn


	
  @Test(description = "Create new account for customer")
  public void Create_New_Account() {

  }
  
  @Test(priority = 2)
  public void View_Account() {

  }
  
  @Test(priority = 3)
  public void Edit_Account() {

  }
  
  //@Test(priority = 4)
  public void Move_Account() {

  }
  
  @Test(enabled = false)
  public void Delete_Account() {

  }
}
  