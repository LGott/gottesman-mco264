package schoolApp;

public class Verifier {
	
	public static boolean isValidPhoneNumber(String number){
		if (number == null){
			return false;
		}
		
		if (number.matches("^[2-9]\\d{2}[2-9]\\d{6}"))
	     {
		    return true;
	     }
	    else{
		   return false;
	      }

     }
}