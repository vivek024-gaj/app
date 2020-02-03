/**
 * 
 */
package in.cropdata.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.cropdata.app.dto.interfaces.Restriction;
import in.cropdata.app.model.AppRestriction;
import in.cropdata.app.repository.AppRestrictionsRepository;
import in.cropdata.app.response.ResponseMessage;

/**
 * @author Vivek Gajbhiye - Cropdata
 *
 *         03-Feb-2020
 */
@Service
public class AppService {

	@Autowired
	AppRestrictionsRepository appRestrictionsRepository;

	public ResponseMessage addRestriction(AppRestriction restriction) {

		ResponseMessage responseMessage = new ResponseMessage();

		appRestrictionsRepository.save(restriction);

		responseMessage.setSuccess(true);
		responseMessage.setMessage("Restrictions added Successfully");
		return responseMessage;
	}// addRestriction

	public List<Restriction> getAllRestriction() {
		List<Restriction> data = appRestrictionsRepository.getAllData();
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
		for (AppRestriction aclRestriction : list) {
//		    aclRestriction.setGroup(aclRestrictionsRepository.getGroupNameByGroupId(aclRestriction.getGroupId()));
			aclRestriction.setResourceURI(
					appRestrictionsRepository.getResourceURIByResourceId(aclRestriction.getResourceId()));
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

}
