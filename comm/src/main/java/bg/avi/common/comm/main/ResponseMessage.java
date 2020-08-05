package bg.avi.common.comm.main;

import bg.avi.common.comm.pub.SingleResponse;

/**
 * <h1>Response Messages for @see PublcResponse!</h1>
 * 
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since   2020-03-20
 * @see SingleResponse
 */
public enum ResponseMessage {
	SUCCESSFULL("S01", "Successfull message"),
	ERROR("E01", "Error message"), 
	UNAUTHORIZED ("E02", "Unauthrized message"), ;
	
	private String code;
	private String message;
	
	private ResponseMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
