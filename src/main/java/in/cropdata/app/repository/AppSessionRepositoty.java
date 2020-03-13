/**
 * 
 */
package in.cropdata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.cropdata.app.dto.interfaces.AppSessionI;
import in.cropdata.app.model.AppSession;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         07-Mar-2020
 */
public interface AppSessionRepositoty extends JpaRepository<AppSession, Integer> {

	@Query(value = "select s.STATUS=1 as STATUS,u.Name from app_session s "
			+ "inner join app_users u on u.ID = s.USER_ID where u.Email=?1 and s.JWT_TOKEN=?2", nativeQuery = true)
	public AppSessionI checkedUser(String email, String token);

}
