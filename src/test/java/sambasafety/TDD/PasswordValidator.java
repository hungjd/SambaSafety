package sambasafety.TDD;


//Scenario 1: To run the test, we create class PasswordValidator ();

public class PasswordValidator {

	public boolean isValid(String Password) {
		
		// check the length of password
		if (Password.length() >= 5 && Password.length()<=10)
		{
			return true;
		} else
			return false;
	}
	
 


 

}
