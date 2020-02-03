/**
 * 
 */
package in.cropdata.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.cropdata.app.model.AppRole;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 * 03-Feb-2020
 */
@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer>{

}
