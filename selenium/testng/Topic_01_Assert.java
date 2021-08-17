package testng;

import org.testng.annotations.Test;
import org.testng.Assert;

public class Topic_01_Assert {
	// kiểu nguyên thủy sẽ có giá trị mặc định nếu chưa khởi tạo (global)
	int number;
	Object studentAdrress = null;
	
  @Test()
  public void TC_01_Assert() {
	  String name = "Nguyen Van A";
	  
	  
	  // isDisplayed/isEnable/isSelected/isMultiple
	  
	  //verify điều kiện trả về đúng
	  Assert.assertTrue(name.contains("A"));
	  
	  //verify điều kiện trả về sai
	  Assert.assertFalse(name.contains("B"));
	  
	  //verify 2 dk = nhau
	  Assert.assertEquals(name, "Nguyen Van A");
	  Assert.assertNotEquals(name, "Nguyen Van B");
	  
	  //
	  Assert.assertNull(studentAdrress);
	  //
	  studentAdrress = "Nguyen Van Troi";
	  Assert.assertNotNull(studentAdrress);
  }
}
  