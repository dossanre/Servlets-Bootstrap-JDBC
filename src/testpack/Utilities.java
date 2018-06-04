package testpack;

public class Utilities {


	public static String isPassValid(String upass) {
		String specialChar = "![&%$#@~_^]";
		if(upass.length() == 0) {
			return "Password Invalid - Please fill the Password field";
		}
		int intSpace = upass.indexOf(" ");
		if(intSpace > 0) {
			return "Password Invalid - No Spaces is allowed into Password";
		}
		int intCap = 0;
		// check if there is Capital letters
		for (int i = 0; i < upass.length(); i++) {
		     if(Character.isUpperCase(upass.charAt(i))) {
		    	 intCap++;
		     }
		}
		if (intCap == 0) {
			return "Password Invalid - You must use Capital Letter and Specials Characters";
		}
		// password should be from 7 to 12 characters 
		if(upass.length()<7 || upass.length() > 12) {
			return "Password Invalid - You should use 7 to 12 characters";
		}
		int intSpChar=0;
		for (int i = 0; i < upass.length(); i++) {
			for (int j = 0; j < specialChar.length(); j++) { 
				if(upass.charAt(i) == specialChar.charAt(j)) {
					intSpChar++;
				}
		     }
		}
		if (intSpChar == 0) {
			return "Password Invalid - You should use Specials Characters";
		}
		
		return "";
	}
	
}
