package bg.avi.common.comm.pub;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

import bg.avi.common.comm.main.Sendable;

/**
 * <h1>Default Request Communication Class!</h1>
 * 
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since   2020-03-20
 * @see SingleResponse
 * @see MultipleResponse
 */
public class Request {
	@JsonProperty("sender_identity")
	private String senderId;
	
	@JsonProperty("authorization")
	private String authorization;
	
	@JsonProperty("request_entity")
	private Sendable requestEntity;
	
	@JsonProperty("request_timestamp")
	private Date requestTimestamp;
	
	public Request() {}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public Sendable getRequestEntity() {
		return requestEntity;
	}

	public void setRequestEntity(Sendable requestEntity) {
		this.requestEntity = requestEntity;
	}

	public Date getRequestTimestamp() {
		return requestTimestamp;
	}

	public void setRequestTimestamp(Date requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
}
