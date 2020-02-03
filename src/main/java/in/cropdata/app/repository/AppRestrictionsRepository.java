/**
 * 
 */
package in.cropdata.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cropdata.app.constant.SqlQueries;
import in.cropdata.app.dto.interfaces.Restriction;
import in.cropdata.app.model.AppRestriction;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
@Repository
public interface AppRestrictionsRepository extends JpaRepository<AppRestriction, Integer> {

	@Query(value = SqlQueries.GET_RESOURCE_URI_BY_RESOURCE_ID, nativeQuery = true)
	String getResourceURIByResourceId(int resourceId);

	@Query(value = SqlQueries.GET_GROUP_NAME_BY_ID, nativeQuery = true)
	String getGroupNameByGroupId(int groupId);

	List<AppRestriction> findAllByRoleId(int roleId);

	@Query(value = SqlQueries.GET_ALL_DATA, nativeQuery = true)
	List<Restriction> getAllData();

	@Query(value = SqlQueries.GET_DATA_BY_ROLE_ID, nativeQuery = true)
	List<Restriction> getAllDataByRoleId(int roleId);
}
