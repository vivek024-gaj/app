/**
 * 
 */
package in.cropdata.app.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         07-Mar-2020
 */
@Data
@Entity(name = "app_session")
public class AppSession {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "JWT_TOKEN")
	private String jwtToken;

	@Column(name = "STATUS")
	private Boolean status;

}
