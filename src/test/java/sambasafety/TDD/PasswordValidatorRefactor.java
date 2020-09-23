package sambasafety.TDD;


//Scenario 3: After refactoring the output shows failed status (see image below)
//this is because we have removed the instance. So there is no reference to non –static method isValid ().

public class PasswordValidatorRefactor {

	public static boolean isValid(String Password) {
		
		if (Password.length() >= 5 && Password.length()<=10)
		{
			return true;
		} else
			return false;
	}
	
 


 

}
