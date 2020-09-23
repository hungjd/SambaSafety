package sambasafety.TDD;

import org.testng.Assert;
import org.testng.annotations.Test;

//Example of TDD:
//A condition for Password acceptance:
//The password should be between 5 to 10 characters.

//First, we write the code that fulfills all the above requirements.
// We will run above class TestPassword ();

public class TestPassword {
	
	@Test 
	public void TestPasswordLength() {
		
		
		PasswordValidator pv = new PasswordValidator();
		//Assert.assertEquals(actual, expected);
		Assert.assertEquals(true, pv.isValid("123abc"));
	}
	

}
