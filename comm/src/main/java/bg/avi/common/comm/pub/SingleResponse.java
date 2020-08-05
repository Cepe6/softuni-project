package bg.avi.common.comm.pub;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import bg.avi.common.comm.main.ResponseMessage;
import bg.avi.common.comm.main.Sendable;

/**
 * <h1>Default Response Communication Class!</h1>
 * 
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since 2020-03-20
 * @see Request
 * @see ResponseMessage
 */
public class SingleResponse {
	private ResponseMessage message;
	private String description;
	private String version;
	
	@JsonProperty("response_timestamp")
	private Date responseTimestamp;
	
	@JsonProperty("response_entity")
	private Sendable responseEntity;
	
	public SingleResponse() {}

	public SingleResponse(ResponseMessage message, String description, String version, Date responseTimestamp,
			Sendable responseEntity) {
		this.message = message;
		this.description = description;
		this.version = version;
		this.responseTimestamp = responseTimestamp;
		this.responseEntity = responseEntity;
	}

	public ResponseMessage getMessage() {
		return message;
	}

	public void setMessage(ResponseMessage message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getResponseTimestamp() {
		return responseTimestamp;
	}

	public void setResponseTimestamp(Date responseTimestamp) {
		this.responseTimestamp = responseTimestamp;
	}

	public Sendable getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(Sendable responseEntity) {
		this.responseEntity = responseEntity;
	}
}
