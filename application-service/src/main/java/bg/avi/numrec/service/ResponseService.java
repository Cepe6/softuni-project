package bg.avi.numrec.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import bg.avi.common.comm.main.ResponseMessage;
import bg.avi.common.comm.main.Sendable;
import bg.avi.common.comm.pub.MultipleResponse;
import bg.avi.numrec.config.Config;

@Service
public class ResponseService {
	
	public MultipleResponse buildResponse(ResponseMessage message, List<Sendable> responseEntity, String description) {
		MultipleResponse response = new MultipleResponse();
		response.setMessage(message);
		response.setResponseEntity(responseEntity);
		response.setDescription(description);
		response.setResponseTimestamp(new Date());
		response.setVersion(Config.APP_VERSION);
		
		return response;
	}
}
