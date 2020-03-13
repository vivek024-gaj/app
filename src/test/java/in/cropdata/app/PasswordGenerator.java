/**
 * 
 */
package in.cropdata.app;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 * 14-Feb-2020
 */
public class PasswordGenerator {
	
	public static void main(String args[]) {
		PasswordEncoder pass = new BCryptPasswordEncoder();
		
		String encode = pass.encode("pass");
		
		System.out.println("generatedpassword : " + encode);
	}

}
