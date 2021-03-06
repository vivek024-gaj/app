/**
 * 
 */
package in.cropdata.app.constant;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
public class SqlQueries {

	public static final String SELECT_FROM = "SELECT * FROM ";

	/**
	 * 
	 * <pre>
	 * 		Users Queries
	 * </pre>
	 * 
	 */

	/**
	 * 
	 * get user by email id for authenticate user
	 * 
	 */
	public static final String GET_USER_BY_EMAIL_ID = "SELECT u.ID,u.RoleID,u.Name,u.Email,u.Password,u.Status,u.CreatedAt,u.UpdatedAt,u.Designation,r.Name as Role FROM app_users u "
			+ "INNER JOIN app_roles r ON r.ID = u.RoleID where u.Email = ?1";

	/**
	 * 
	 * get all users list
	 * 
	 */
	public static final String GET_ALL_USERS_LIST = "SELECT u.ID,u.RoleID,u.Name,u.Email,u.Password,u.Status,u.CreatedAt,u.UpdatedAt,r.Name as Role FROM app_users u "
			+ "INNER JOIN app_roles r ON r.ID = u.RoleID";

	/**
	 * 
	 * get user by id
	 * 
	 */
	public static final String GET_USER_BY_ID = "SELECT u.ID,u.RoleID,u.Name,u.Email,u.Password,u.Status,u.CreatedAt,u.UpdatedAt,r.Name as Role FROM app_users u "
			+ "INNER JOIN app_roles r ON r.ID = u.RoleID where u.ID = ?1";

	/**
	 * 
	 * 
	 * get menus for user by role id
	 * 
	 */
//	public static final String GET_MENUS_BY_ROLE_ID = "SELECT res.ResourceURL,res.ResourceName, resg.ResourceGroupName,rtcn.ID as RestrictedResourceID FROM app_resource as res "
//			+ "INNER JOIN app_resource_groups as resg ON resg.ID = res.ResourceGroupID LEFT JOIN app_restrictions as rtcn ON (rtcn.ResourceID = res.ID AND rtcn.RoleID = ?1) "
//			+ "WHERE res.ResourceGroupID is not NULL HAVING RestrictedResourceID IS NULL ORDER BY resg.MenuPlacement, res.MenuPlacement";

//	public static final String GET_MENUS_BY_ROLE_ID = "SELECT res.ResourceURL,res.ResourceName, resg.ResourceGroupName,rtcn.ID as RestrictedResourceID,resg.Icon as ResourceIcon,res.Icon as ResIcon FROM app_resource as res "
//			+ "INNER JOIN app_resource_groups as resg ON resg.ID = res.ResourceGroupID "
//			+ "LEFT JOIN app_restrictions as rtcn ON (rtcn.ResourceID = res.ID AND rtcn.RoleID = ?1) "
//			+ "WHERE res.ResourceGroupID is not NULL HAVING RestrictedResourceID IS NULL ORDER BY resg.ResourceGroupName asc";
//	public static final String GET_MENUS_BY_ROLE_ID = "SELECT res.ResourceURL,res.ResourceName, resg.ResourceGroupName,rtcn.ID as RestrictedResourceID,resg.Icon as ResourceIcon,res.Icon as ResIcon\n"
//			+ "FROM app_resource as res\n" + "INNER JOIN app_resource_groups as resg ON resg.ID = res.ResourceGroupID\n"
//			+ "LEFT JOIN app_restrictions as rtcn ON (rtcn.ResourceID = res.ID OR rtcn.ResourceGroupID = resg.ID or res.ID = rtcn.GroupID AND rtcn.RoleID = ?1)\n"
//			+ "WHERE res.ResourceGroupID is not NULL HAVING RestrictedResourceID IS NULL ORDER BY resg.ResourceGroupName asc;";

	public static final String GET_MENUS_BY_ROLE_ID = "SELECT res.ResourceURL,res.ResourceName,resg.ResourceGroupName,rtcn.ID AS RestrictedResourceID, "
			+ "    resg.Icon as ResourceIcon,res.Icon as ResIcon FROM  "
			+ "    app_resource AS res INNER JOIN app_resource_groups AS resg ON resg.ID = res.ResourceGroupID LEFT JOIN  "
			+ "    app_restrictions AS rtcn ON (rtcn.ResourceID = res.ID OR rtcn.ResourceGroupID = resg.ID OR rtcn.SubResourceID = res.ID)  "
			+ "    AND (rtcn.RoleID = ?1) WHERE res.ResourceGroupID IS NOT NULL HAVING RestrictedResourceID IS NULL ORDER BY resg.ResourceGroupName ASC";

	/**
	 * 
	 * <pre>
	 * 		Resource Queries
	 * </pre>
	 * 
	 */

	public static final String GET_ALL_PARENT_RESOURCE = "SELECT * FROM app_resource where ParentID=0";

	public static final String GET_ALL_SUB_RESOURCE_BY_PARENT_ID = "SELECT * FROM app_resource where ParentID=?1";

	/**
	 * 
	 * <pre>
	 * 		Restrictions Queries
	 * </pre>
	 * 
	 */
	public static final String GET_RESOURCE_URI_BY_RESOURCE_ID = "SELECT ResourceURL FROM app_resource WHERE ID=?1";

	public static final String GET_GROUP_NAME_BY_ID = "SELECT Name FROM app_groups WHERE ID=?1";

	public static final String GET_ALL_DATA = "SELECT app_restrictions.ID,app_restrictions.RoleID,app_restrictions.ResourceID,app_restrictions.GroupID, "
			+ "app_roles.Name as RoleName,app_resource.ResourceName,app_resource.ResourceURL,app_groups.Name as GroupName FROM app_restrictions  "
			+ "JOIN app_roles ON (app_roles.ID = app_restrictions.RoleID) JOIN app_resource ON (app_resource.ID = app_restrictions.ResourceID)  "
			+ "JOIN app_groups ON (app_groups.ID = app_restrictions.GroupID);";

//	public static final String GET_DATA_BY_ROLE_ID = "SELECT app_restrictions.ID,app_restrictions.RoleID,app_restrictions.ResourceID,app_restrictions.GroupID, "
//			+ "app_roles.Name as RoleName,app_resource.ResourceName,app_resource.ResourceURL,app_groups.Name as GroupName FROM app_restrictions  "
//			+ "LEFT JOIN app_roles ON (app_roles.ID = app_restrictions.RoleID)  LEFT JOIN app_resource ON (app_resource.ID = app_restrictions.ResourceID)  "
//			+ "LEFT JOIN app_groups ON (app_groups.ID = app_restrictions.GroupID) where app_restrictions.RoleID =?1";

	public static final String GET_DATA_BY_ROLE_ID = "SELECT app_restrictions.ID,app_restrictions.RoleID,app_restrictions.ResourceID,app_restrictions.GroupID, \n"
			+ "app_roles.Name as RoleName,app_resource.ResourceName,app_resource.ResourceURL, \n"
			+ "app_groups.Name as GroupName,resgrp.ResourceGroupName,res.ResourceName as SubResources,res.ResourceURL as SubResourceURL \n"
			+ ",app_restrictions.ResourceGroupID,app_restrictions.SubResourceID  FROM app_restrictions \n"
			+ "LEFT JOIN app_roles ON (app_roles.ID = app_restrictions.RoleID) \n"
			+ "LEFT JOIN app_resource ON (app_resource.ID = app_restrictions.ResourceID) \n"
			+ "LEFT JOIN app_groups ON (app_groups.ID = app_restrictions.GroupID) \n"
			+ "left join app_resource_groups resgrp on resgrp.ID = app_restrictions.ResourceGroupID \n"
			+ "left join app_resource res on res.ID = app_restrictions.SubResourceID \n"
			+ "where app_restrictions.RoleID = ?1";
}
