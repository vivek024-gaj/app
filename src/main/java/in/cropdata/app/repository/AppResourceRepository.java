/**
 * 
 */
package in.cropdata.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.cropdata.app.constant.SqlQueries;
import in.cropdata.app.model.AppResource;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
@Repository
public interface AppResourceRepository extends JpaRepository<AppResource, Integer> {

	@Query(value = SqlQueries.GET_ALL_PARENT_RESOURCE, nativeQuery = true)
	List<AppResource> getAllParentResource();

	@Query(value = SqlQueries.GET_ALL_SUB_RESOURCE_BY_PARENT_ID, nativeQuery = true)
	List<AppResource> getAllSubResourceByParentId(int parentId);
	
	
}
