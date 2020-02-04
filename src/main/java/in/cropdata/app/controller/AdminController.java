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
import org.springframework.web.bind.annotation.RestController;

import in.cropdata.app.dto.interfaces.Restriction;
import in.cropdata.app.model.AppGroup;
import in.cropdata.app.model.AppResource;
import in.cropdata.app.model.AppResourceGroup;
import in.cropdata.app.model.AppRestriction;
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

	@PostMapping("/add-group")
	public ResponseMessage addGroup(@RequestBody AppGroup appGroup) {
		return appService.addGroup(appGroup);
	}// addGroup

	@GetMapping("/group-list")
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

	@PostMapping("/add-resource")
	public ResponseMessage addResource(@RequestBody AppResource appResource) {
		return appService.addResource(appResource);
	}// addResource

	@GetMapping("/resource-list")
	public List<AppResource> getAllResource() {
		return appService.getAllResource();
	}// getAllResource

	@GetMapping("/parents")
	public List<AppResource> getAllParentResource() {
		return appService.getAllParentResource();
	}// getAllParentResource

	@GetMapping("/parents/{parentId}")
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

	@GetMapping("/list-resource-group")
	public List<AppResourceGroup> getAllResourceGroup() {
		return appService.getAllResourceGroup();
	}// getAllResourceGroup

	@PostMapping("/add-resource-group")
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

	@PostMapping("/add-restriction")
	public ResponseMessage addRestriction(@RequestBody AppRestriction restriction) {
		return appService.addRestriction(restriction);
	}// addRestriction

	@PostMapping("/add-all-restriction")
	public ResponseMessage addAllRestriction(@RequestBody List<AppRestriction> restrictionList) {
		return appService.addAllRestrictions(restrictionList);
	}// addRestriction

	@GetMapping("/list-restriction")
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
}
