package sambasafety.TDD;

import org.testng.Assert;
import org.testng.annotations.Test;

//Scenario 2: Here we can see in method TestPasswordLength () 
//there is no need of creating an instance of class PasswordValidator. 
//Instance means creating an object of class to refer the members (variables/methods) of that class.

public class TestPasswordRefactor {
	
	@Test 
	public void TestPasswordLength() { 
		
//		We will remove class PasswordValidator pv = new PasswordValidator () 
//		from the code. We can call the isValid () method directly by PasswordValidator. IsValid ("Abc123"). 
		//PasswordValidator pv = new PasswordValidator();
	 
		// Refactor (change code) there is no need to create instance of class PasswordValidator
		// Assert.assertEquals(true, pv.isValid("123abc"));
		
		Assert.assertEquals(true, PasswordValidatorRefactor.isValid("123abc"));
	}
	

}
