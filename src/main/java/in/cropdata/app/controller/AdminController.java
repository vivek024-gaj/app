/**
 * 
 */
package in.cropdata.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.cropdata.app.dto.interfaces.Restriction;
import in.cropdata.app.dto.interfaces.User;
import in.cropdata.app.model.AppGroup;
import in.cropdata.app.model.AppResource;
import in.cropdata.app.model.AppResourceGroup;
import in.cropdata.app.model.AppRestriction;
import in.cropdata.app.model.AppRole;
import in.cropdata.app.model.AppUser;
import in.cropdata.app.response.ResponseMessage;
import in.cropdata.app.service.impl.AppService;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
@RestController
@CrossOrigin("*")
public class AdminController {

	@Autowired
	AppService appService;

	@PostMapping("/group/add")
	public ResponseMessage addGroup(@RequestBody AppGroup appGroup) {
		return appService.addGroup(appGroup);
	}// addGroup

	@GetMapping("/group/list")
	public List<AppGroup> getAllGroup() {
		return appService.getAllGroup();
	}// getAllGroup

	@PutMapping("/group/{id}/update")
	public ResponseMessage updateGroupById(@PathVariable int id, @RequestBody AppGroup appGroup) {
		return appService.updateGroupById(id, appGroup);
	}// updateGroupById

	@DeleteMapping("/group/{id}/delete")
	public ResponseMessage deleteGroupById(@PathVariable int id) {
		return appService.deleteGroupById(id);
	}// deleteGroupById

	@GetMapping("/group/{id}")
	public AppGroup findGroupById(@PathVariable int id) {
		return appService.findGroupById(id);
	}// findGroupById

	@PostMapping("/resource/add")
	public ResponseMessage addResource(@RequestBody AppResource appResource) {
		return appService.addResource(appResource);
	}// addResource

	@GetMapping("/resource/list")
	public List<AppResource> getAllResource() {
		return appService.getAllResource();
	}// getAllResource

	@GetMapping("/resource/parents")
	public List<AppResource> getAllParentResource() {
		return appService.getAllParentResource();
	}// getAllParentResource

	@GetMapping("/resource/parents/{parentId}")
	public List<AppResource> getAllParentResource(@PathVariable int parentId) {
		return appService.getAllSubResourceByParentId(parentId);
	}// getAllResource

	@PutMapping("/resource/{id}/update")
	public ResponseMessage updateResourceById(@PathVariable int id, @RequestBody AppResource appResource) {
		return appService.updateResourceById(id, appResource);
	}// updateResourceById

	@DeleteMapping("/resource/{id}/delete")
	public ResponseMessage deleteResourceById(@PathVariable int id) {
		return appService.deleteResourceById(id);
	}// deleteResourceById

	@GetMapping("/resource/{id}")
	public AppResource findResourceById(@PathVariable int id) {
		return appService.findResourceById(id);
	}// findResourceById

	@GetMapping("/resource-group/list")
	public List<AppResourceGroup> getAllResourceGroup() {
		return appService.getAllResourceGroup();
	}// getAllResourceGroup

	@PostMapping("/resource-group/add")
	public ResponseMessage addResourceGroup(@RequestBody AppResourceGroup appResourceGroup) {
		return appService.addResourceGroup(appResourceGroup);
	}// addAllResourceGroup

	@PutMapping("/resource-group/{id}/update")
	public ResponseMessage updateResourceGroupById(@PathVariable int id,
			@RequestBody AppResourceGroup appResourceGroup) {
		return appService.updateResourceGroupById(id, appResourceGroup);
	}// updateResourceGroupById

	@DeleteMapping("/resource-group/{id}/delete")
	public ResponseMessage deleteResourceGroupById(@PathVariable int id) {
		return appService.deleteResourceGroupById(id);
	}// deleteResourceGroupById

	@GetMapping("/resource-group/{id}")
	public AppResourceGroup findResourceGroupById(@PathVariable int id) {
		return appService.findResourceGroupById(id);
	}// findResourceGroupById

	@PostMapping("/restriction/add")
	public ResponseMessage addRestriction(@RequestBody AppRestriction restriction) {
		return appService.addRestriction(restriction);
	}// addRestriction

	@PostMapping("/restriction/add-all")
	public ResponseMessage addAllRestriction(@RequestBody List<AppRestriction> restrictionList) {
		return appService.addAllRestrictions(restrictionList);
	}// addRestriction

	@GetMapping("/restriction/list")
	public List<Restriction> getAllRestriction() {
		return appService.getAllRestriction();
	}// getAllRestriction

	@GetMapping("/restriction/{roleId}/role")
	public List<Restriction> getAllRestrictionByRoleId(@PathVariable int roleId) {
		return appService.getAllRestrictionByRoleId(roleId);
	}// getAllRestriction

	@PutMapping("/restriction/{id}/update")
	public ResponseMessage updateRestrictionById(@PathVariable int id, @RequestBody AppRestriction restriction) {
		return appService.updateRestrictionById(id, restriction);
	}// updateRestrictionById

	@DeleteMapping("/restriction/{id}/delete")
	public ResponseMessage deleteRestrictionById(@PathVariable int id) {
		return appService.deleteRestrictionById(id);
	}// deleteRestrictionById

	@GetMapping("/restriction/{id}")
	public AppRestriction findRestrictionById(@PathVariable int id) {
		return appService.findRestrictionById(id);
	}// findRestrictionById

	@PostMapping("/role/add")
	public ResponseMessage addRole(@RequestBody AppRole role) {
		return appService.addRole(role);
	}// addRole

	@GetMapping("/role/list")
	public List<AppRole> getAllRole() {
		List<AppRole> roleList = appService.getAllRole();
		for (AppRole aclRole : roleList) {
			List<Restriction> restrictionList = appService.getAllRestrictionByRoleId(aclRole.getId());
			if (restrictionList != null) {
				aclRole.setRestrictions(restrictionList);
			}
		}
		return roleList;
	}// getAllRole

	@PutMapping("/role/{id}/update")
	public ResponseMessage updateRoleById(@PathVariable int id, @RequestBody AppRole role) {
		return appService.updateRoleById(id, role);
	}// updateRoleById

	@DeleteMapping("/role/{id}/delete")
	public ResponseMessage deleteRoleById(@PathVariable int id) {
		return appService.deleteRoleById(id);
	}// deleteRoleById

	@GetMapping("/role/{id}")
	public AppRole findRoleById(@PathVariable int id) {
		return appService.findRoleById(id);
	}// findRoleById

	@PostMapping("/user/add")
	public ResponseMessage addUser(@RequestBody AppUser user) {
		return appService.addUser(user);
	}// addUser

	@GetMapping("/user/list")
	public List<User> getAllUser() {
		return appService.getAllUser();
	}// getAllUser

	@PutMapping("/user/{id}/update")
	public ResponseMessage updateUserById(@PathVariable int id, @RequestBody AppUser user) {
		return appService.updateUserById(id, user);
	}// updateUserById

	@DeleteMapping("/user/{id}/delete")
	public ResponseMessage deleteUserById(@PathVariable int id) {
		return appService.deleteUserById(id);
	}// deleteUserById

	@GetMapping("/user/{id}") // edit by admin
	public User findUserById(@PathVariable int id) {
		return appService.findUserById(id);
	}// findUserById

//	@PutMapping("/{id}/user/changePassword")
//	public ResponseMessage updatePassword(@RequestParam String oldPassword, @PathVariable int id,
//			@RequestParam String password) {
//		return appService.updatePassword(oldPassword, password, id);
//	}// updatePassword

//	@PostMapping("/{id}/user/forgetPassword")
//	public ResponseMessage forgetPassword(@RequestParam String email) {
//		return appService.forgetPassword(email);
//	}// forgetPassword

}
