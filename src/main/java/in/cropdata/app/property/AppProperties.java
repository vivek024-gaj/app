/**
 * 
 */
package in.cropdata.app.property;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 * 01-Feb-2020
 */
@Component
@Data
public class AppProperties {
	
	@Value("${jwt.secret.key}")
	private String jwtSecretKey;

	
}
