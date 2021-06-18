package javaTester;

public class Topic_08_String {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String actualText = "     Abcd       ";
		System.out.println(actualText.trim());
		
		String user = "admin";
		String pass = "pass";
		String herf = "https://admin:admin@the-internet.herokuapp.com/basic_auth";
		String[] heftValue = herf.split("//");

		herf = heftValue[0] + "//" + user + ":" + pass + heftValue[1];
	}

}
