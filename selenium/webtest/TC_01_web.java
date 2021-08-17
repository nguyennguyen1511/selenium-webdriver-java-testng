package webtest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC_01_web {
	  @BeforeClass (alwaysRun = true)
	  public void beforeClass() {
		  // đảm bảo trong trường hợp, init browser/ init driver/init web ở before dc run
		  //nếu before fail > test case và after sẽ ko bao giờ run > always run để after lúc nào cũng dc run 
	
	  }
		

	  @Test(groups ={"web", "regression"})
	  public void TC_01() {
	  }
	  @Test (groups ={"web", "regression"})
	  public void TC_02() {
	  }
	  @Test(groups ={"web", "regression"})
	  public void TC_03() {
	  }
	  
	  @AfterClass(alwaysRun = true)
	  public void afterClass() {
	  }

}
