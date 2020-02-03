/**
 * 
 */
package in.cropdata.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         01-Feb-2020
 */
@RestController
public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@GetMapping("/testApp")
	public String testApp() {
		logger.info("Test App is Working");
		return "{Application UP}";
	}
}
