package in.cropdata.app.utils;

import org.springframework.stereotype.Component;

import in.cropdata.app.response.ResponseMessage;

@Component
public class ResponseMessageUtil {

	public ResponseMessage sendResponse(boolean isSuccess, String message, String error) {

		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.setSuccess(isSuccess);
		if (isSuccess) {
			responseMessage.setMessage(message);
		} else {
			responseMessage.setError(error);
		}
		return responseMessage;

	}

}
