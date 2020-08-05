package bg.avi.common.comm.main;

import java.util.Collection;
import java.util.Date;

import bg.avi.common.comm.pub.MultipleResponse;
import bg.avi.common.comm.pub.SingleResponse;

/**
 * <h1>Response Builder Class!</h1>
 * 
 * @author Alexander Verbovskiy
 * @version 1.0
 * @since 2020-03-28
 */
public class ResponseBuilder {
	
	public static MultipleResponse build(ResponseMessage message, String description,
			String version, Collection<Sendable> list) {
		return new MultipleResponse(message, description, version, new Date(), list);
	}
	
	public static SingleResponse build(ResponseMessage message, String description,
			String version, Sendable entity) {
		return new SingleResponse(message, description, version, new Date(), entity);
	}
}
