package javaTester;
import org.openqa.selenium.WebDriver;


public class Topic_04_Normal_Class {
	//Thuoc tinh
	String fullName = "Nguyen Van Nam";
	String address, street;
	//Phuong thuc
	public void setfullName(String name) {
		fullName = name;
		
	}
	public void setAddress(String addressName, String streetName) {
		address = addressName;
		street = streetName;
	}
	
	public String getfullName() {
		
		return fullName;
	}
	public static void main (String[] arg){
		WebDriver driver;
		Topic_04_Normal_Class topic = new Topic_04_Normal_Class();
		
		//instance = dai dien cho kieu du lieu class/interface
	}

}
