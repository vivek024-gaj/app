/**
 * 
 */
package in.cropdata.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import in.cropdata.app.constant.APIConstants;
import in.cropdata.app.dto.interfaces.Restriction;
import in.cropdata.app.dto.interfaces.User;
import in.cropdata.app.exception.DoesNotExistException;
import in.cropdata.app.model.AppGroup;
import in.cropdata.app.model.AppResource;
import in.cropdata.app.model.AppResourceGroup;
import in.cropdata.app.model.AppRestriction;
import in.cropdata.app.model.AppRole;
import in.cropdata.app.model.AppUser;
import in.cropdata.app.repository.AppGroupRepository;
import in.cropdata.app.repository.AppResourceGroupRepository;
import in.cropdata.app.repository.AppResourceRepository;
import in.cropdata.app.repository.AppRestrictionsRepository;
import in.cropdata.app.repository.AppRoleRepository;
import in.cropdata.app.repository.AppUserRepository;
import in.cropdata.app.response.ResponseMessage;
import in.cropdata.app.utils.ResponseMessageUtil;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
@Service
public class AppService {

	@Autowired
	AppRestrictionsRepository appRestrictionsRepository;

	@Autowired
	ResponseMessageUtil responseMessageUtil;

	@Autowired
	AppGroupRepository appGroupRepository;

	@Autowired
	AppResourceRepository appResourceRepository;

	@Autowired
	AppResourceGroupRepository appResourceGroupRepository;

	@Autowired
	AppRoleRepository appRoleRepository;

	@Autowired
	AppUserRepository appUserRepository;

	@Autowired
	PasswordEncoder bcryptEncoder;

	public ResponseMessage addRestriction(AppRestriction restriction) {

		ResponseMessage responseMessage = new ResponseMessage();

		appRestrictionsRepository.save(restriction);

		responseMessage.setSuccess(true);
		responseMessage.setMessage("Restrictions added Successfully");
		return responseMessage;
	}// addRestriction

	public List<Restriction> getAllRestriction() {
		List<Restriction> data = this.appRestrictionsRepository.getAllData();
		return data;
	}// getAllRestriction

	public ResponseMessage updateRestrictionById(int id, AppRestriction restriction) {
		ResponseMessage responseMessage = new ResponseMessage();
		Optional<AppRestriction> foundVareity = appRestrictionsRepository.findById(id);
		if (foundVareity.isPresent()) {
			restriction.setId(id);
			appRestrictionsRepository.save(restriction);

			responseMessage.setSuccess(true);
			responseMessage.setMessage("Restriction Updated Successfully.");
		} else {
			responseMessage.setSuccess(false);
			responseMessage.setError("Update Error.");
		}
		return responseMessage;
	}// updateRestrictionById

	public ResponseMessage deleteRestrictionById(int id) {
		appRestrictionsRepository.deleteById(id);

		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setSuccess(true);
		responseMessage.setMessage("Restriction Deleted Successfully.");
		return responseMessage;
	}// deleteRestrictionById

	public AppRestriction findRestrictionById(int id) {
		Optional<AppRestriction> foundActivity = appRestrictionsRepository.findById(id);
		if (foundActivity.isPresent()) {
			return foundActivity.get();
		}
		return null;
	}// findRestrictionById

	public List<AppRestriction> findAllByRoleId(int roleId) {

		List<AppRestriction> list = appRestrictionsRepository.findAllByRoleId(roleId);
		for (AppRestriction appRestriction : list) {
			appRestriction.setResourceURI(
					appRestrictionsRepository.getResourceURIByResourceId(appRestriction.getResourceId()));
		}
		return list;
	}// findAllByRoleId(int roleId)

	public ResponseMessage addAllRestrictions(List<AppRestriction> restrictionList) {
		ResponseMessage responseMessage = new ResponseMessage();

		appRestrictionsRepository.saveAll(restrictionList);

		responseMessage.setSuccess(true);
		responseMessage.setMessage("Restrictions added Successfully");
		return responseMessage;
	}

	public List<Restriction> getAllRestrictionByRoleId(int roleId) {
		List<Restriction> data = appRestrictionsRepository.getAllDataByRoleId(roleId);
		return data;
	}// getAllRestrictionByRoleId

	/**
	 * 
	 * AppGroupRepository
	 * 
	 * 
	 */

	public ResponseMessage addGroup(AppGroup aclGroup) {
		try {
			appGroupRepository.save(aclGroup);

			return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_ADD_SUCCESS, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// addGroup

	public List<AppGroup> getAllGroup() {
		return appGroupRepository.findAll();
	}// getAllGroup

	public ResponseMessage updateGroupById(int id, AppGroup appGroup) {

		try {

			Optional<AppGroup> foundGroup = appGroupRepository.findById(id);

			if (foundGroup.isPresent()) {

				appGroup.setId(id);
				appGroupRepository.save(appGroup);

				return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_UPDATE_SUCCESS + id, "");

			} else {

				return responseMessageUtil.sendResponse(false, "", "Group" + APIConstants.RESPONSE_UPDATE_ERROR + id);
			}
		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// updateGroupById

	public ResponseMessage deleteGroupById(int id) {

		try {
			appGroupRepository.deleteById(id);
			return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_DELETE_SUCCESS + id, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Group" + APIConstants.RESPONSE_DELETE_ERROR + id);
		}
	}// deleteGroupById

	public AppGroup findGroupById(int id) {
		try {
			Optional<AppGroup> foundGroup = appGroupRepository.findById(id);
			if (foundGroup.isPresent()) {
				return foundGroup.get();
			} else {
				throw new DoesNotExistException("Group" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
			}
		} catch (Exception e) {
			throw e;
		}
	}// findGroupById

	/**
	 * 
	 * AppResourceRepository
	 * 
	 */

	public ResponseMessage addResource(AppResource aclResource) {

		try {
			appResourceRepository.save(aclResource);

			return responseMessageUtil.sendResponse(true, "Resource" + APIConstants.RESPONSE_ADD_SUCCESS, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// addResource

	public List<AppResource> getAllResource() {
		List<AppResource> list = appResourceRepository.findAll();
		return list;
	}// getAllResource

	public ResponseMessage updateResourceById(int id, AppResource appResource) {

		Optional<AppResource> foundResource = appResourceRepository.findById(id);

		if (foundResource.isPresent()) {
			try {
				appResource.setId(id);
				appResourceRepository.save(appResource);

				return responseMessageUtil.sendResponse(true, "Resource" + APIConstants.RESPONSE_UPDATE_SUCCESS + id,
						"");

			} catch (Exception e) {
				return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
			}
		} else {
			return responseMessageUtil.sendResponse(false, "", "Resource" + APIConstants.RESPONSE_UPDATE_ERROR + id);
		}
	}// updateResourceById

	public ResponseMessage deleteResourceById(int id) {
		try {
			appResourceRepository.deleteById(id);

			return responseMessageUtil.sendResponse(true, "Resource" + APIConstants.RESPONSE_DELETE_SUCCESS + id, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Resource" + APIConstants.RESPONSE_DELETE_ERROR + id);
		}
	}// deleteResourceById

	public AppResource findResourceById(int id) {
		try {
			Optional<AppResource> foundResource = appResourceRepository.findById(id);
			if (foundResource.isPresent()) {
				return foundResource.get();
			} else {
				throw new DoesNotExistException("Resource" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
			}
		} catch (Exception e) {
			throw e;
		}
	}// findResourceById

	public List<AppResource> getAllParentResource() {
		return appResourceRepository.getAllParentResource();
	}// getAllParentResource

	public List<AppResource> getAllSubResourceByParentId(int parentId) {
		return appResourceRepository.getAllSubResourceByParentId(parentId);
	}// getAllSubResourceByParentId

	/**
	 * 
	 * AppRoleRepository
	 * 
	 */

	public ResponseMessage addRole(AppRole role) {
		appRoleRepository.save(role);

		return responseMessageUtil.sendResponse(true, "Role" + APIConstants.RESPONSE_ADD_SUCCESS, "");
	}// addRole

	public List<AppRole> getAllRole() {
		return appRoleRepository.findAll();
	}// getAllRole

	public ResponseMessage updateRoleById(int id, AppRole role) {

		try {
			Optional<AppRole> foundRole = appRoleRepository.findById(id);

			if (foundRole.isPresent()) {
				AppRole roleupdate = foundRole.get();

				if (role.getName() != null) {
					roleupdate.setName(role.getName());
				}

				if (role.getDescription() != null) {
					roleupdate.setDescription(role.getDescription());
				}
				appRoleRepository.save(roleupdate);

				return responseMessageUtil.sendResponse(true, "Role" + APIConstants.RESPONSE_UPDATE_SUCCESS + id, "");

			} else {
				return responseMessageUtil.sendResponse(false, "", "Role" + APIConstants.RESPONSE_UPDATE_ERROR + id);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// updateRoleById

	public ResponseMessage deleteRoleById(int id) {

		try {
			appRoleRepository.deleteById(id);

			return responseMessageUtil.sendResponse(true, "Role" + APIConstants.RESPONSE_DELETE_SUCCESS + id, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Role" + APIConstants.RESPONSE_DELETE_ERROR + id);
		}
	}// deleteRoleById

	public AppRole findRoleById(int id) {
		try {
			Optional<AppRole> foundRole = appRoleRepository.findById(id);
			if (foundRole.isPresent()) {
				return foundRole.get();
			} else {
				throw new DoesNotExistException("Role" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
			}
		} catch (Exception e) {
			throw e;
		}
	}// findRoleById

	/**
	 * 
	 * 
	 * AppUserRepository
	 * 
	 */

	public ResponseMessage addUser(AppUser user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		try {
			appUserRepository.save(user);
		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error: " + e.getMessage());
		}
		return responseMessageUtil.sendResponse(true, "User" + APIConstants.RESPONSE_ADD_SUCCESS, "");
	}// addUser

	public List<User> getAllUser() {
		List<User> list = appUserRepository.getAllUserData();
		return list;
	}// getAllUser

	public ResponseMessage updateUserById(int id, AppUser user) {
		Optional<AppUser> foundUser = appUserRepository.findById(id);

		if (foundUser.isPresent()) {
			AppUser updateUser = foundUser.get();
			if (user.getEmail() != null) {
				updateUser.setEmail(user.getEmail());
			}

			if (user.getName() != null) {
				updateUser.setName(user.getName());
			}

			if (user.getStatus() != null) {
				updateUser.setStatus(user.getStatus());
			}

			if (user.getRoleId() != 0) {
				updateUser.setRoleId(user.getRoleId());
			}

			appUserRepository.save(updateUser);

			return responseMessageUtil.sendResponse(true, "User" + APIConstants.RESPONSE_UPDATE_SUCCESS + id, "");
		} else {
			return responseMessageUtil.sendResponse(false, "", "User" + APIConstants.RESPONSE_UPDATE_ERROR + id);
		}
	}// updateUserById

	public ResponseMessage deleteUserById(int id) {

		try {
			appUserRepository.deleteById(id);
			return responseMessageUtil.sendResponse(true, "User" + APIConstants.RESPONSE_DELETE_SUCCESS + id, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "User" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
		}
	}// deleteUserById

	public User findUserById(int id) {

		try {
			User user = appUserRepository.getUserById(id);

			if (user != null) {
				return user;
			} else {
				throw new DoesNotExistException("User" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
			}
		} catch (Exception e) {
			throw e;
		}
	}// findUserById

	public ResponseMessage updatePassword(String oldPassword, String password, int id) {

		try {
			ResponseMessage responseMessage = new ResponseMessage();

			Optional<AppUser> foundVareity = appUserRepository.findById(id);
			if (foundVareity.isPresent()) {
				AppUser user = foundVareity.get();

				if (user.getPassword().equals(bcryptEncoder.encode(oldPassword))) {
					user.setPassword(password);

					try {
						appUserRepository.save(user);
					} catch (Exception e) {
						return responseMessageUtil.sendResponse(false, "", "Server Error: " + e.getMessage());
					}

					responseMessage.setSuccess(true);
					responseMessage.setMessage("Password changed successfully.");
				} else {
					responseMessage.setSuccess(false);
					responseMessage.setError("Invalid old password.");
				}

			} else {
				responseMessage.setSuccess(false);
				responseMessage.setError("Failed to change password.");
			}
			return responseMessage;
		} catch (Exception e) {
			throw e;
		}

	}// updatePassword

	/**
	 * 
	 * AppResourceGroupRepository
	 * 
	 */

	public ResponseMessage addResourceGroup(AppResourceGroup AclResourceGroup) {
		try {
			appResourceGroupRepository.save(AclResourceGroup);

			return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_ADD_SUCCESS, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// addGroup

	public List<AppResourceGroup> getAllResourceGroup() {
		return appResourceGroupRepository.findAll();
	}// getAllGroup

	public ResponseMessage updateResourceGroupById(int id, AppResourceGroup appResourceGroup) {

		try {

			Optional<AppResourceGroup> foundResourceGroup = appResourceGroupRepository.findById(id);

			if (foundResourceGroup.isPresent()) {

				appResourceGroup.setId(id);
				appResourceGroupRepository.save(appResourceGroup);

				return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_UPDATE_SUCCESS + id, "");

			} else {

				return responseMessageUtil.sendResponse(false, "", "Group" + APIConstants.RESPONSE_UPDATE_ERROR + id);
			}
		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Server Error : " + e.getMessage());
		}
	}// updateGroupById

	public ResponseMessage deleteResourceGroupById(int id) {

		try {
			appResourceGroupRepository.deleteById(id);
			return responseMessageUtil.sendResponse(true, "Group" + APIConstants.RESPONSE_DELETE_SUCCESS + id, "");

		} catch (Exception e) {
			return responseMessageUtil.sendResponse(false, "", "Group" + APIConstants.RESPONSE_DELETE_ERROR + id);
		}
	}// deleteGroupById

	public AppResourceGroup findResourceGroupById(int id) {
		try {
			Optional<AppResourceGroup> foundResourceGroup = appResourceGroupRepository.findById(id);
			if (foundResourceGroup.isPresent()) {
				return foundResourceGroup.get();
			} else {
				throw new DoesNotExistException("Group" + APIConstants.RESPONSE_DOES_NOT_EXIST + id);
			}
		} catch (Exception e) {
			throw e;
		}
	}// findGroupById

}
