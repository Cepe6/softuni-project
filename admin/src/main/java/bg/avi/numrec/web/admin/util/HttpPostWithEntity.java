package bg.avi.numrec.web.admin.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

public class HttpPostWithEntity extends HttpEntityEnclosingRequestBase {
    public final static String METHOD_NAME = "POST";

    public HttpPostWithEntity(String URL) {
    	try {
			this.setURI(new URI(URL));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
    }
    
	@Override
    public String getMethod() {
        return METHOD_NAME;
    }
}
