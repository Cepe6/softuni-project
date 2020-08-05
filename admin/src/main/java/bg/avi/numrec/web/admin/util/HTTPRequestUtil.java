package bg.avi.numrec.web.admin.util;

import java.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HTTPRequestUtil {
	private final static Logger LOGGER = LoggerFactory.getLogger(HTTPRequestUtil.class);
	
	 private final static CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public static String doGetWithBody(String URL, String requestBody, String username, String pass) throws Exception {
		HttpGetWithEntity request = new HttpGetWithEntity(URL);
		request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept",       "application/json");
        request.addHeader("Authorization", convertCredentials(username, pass));
		
		HttpEntity httpEntity = null;
		httpEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
		request.setEntity(httpEntity);
		
		 try (CloseableHttpResponse response = httpClient.execute(request)) {
			 StatusLine sl = response.getStatusLine();           
			 if (sl.getStatusCode() == 401) {
				 LOGGER.info("Unauthorized!");
				 return null;
			 }
			 
			 HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        return null;
	}
	
	public static String doGet(String URL, String username, String pass) throws Exception {
        HttpGet request = new HttpGet(URL);
        request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept",       "application/json");
        request.addHeader("Authorization", convertCredentials(username, pass));

        try (CloseableHttpResponse response = httpClient.execute(request)) {
        	StatusLine sl = response.getStatusLine();           
			 if (sl.getStatusCode() == 401) {
				 LOGGER.info("Unauthorized!");
				 return null;
			 }
			 
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                return result;
            }
        }
        return null;
    }
	
	public static void doPost(String URL, String requestBody, String username, String pass) throws Exception {
		HttpPostWithEntity request = new HttpPostWithEntity(URL);
		request.addHeader("Content-Type", "application/json");
        request.addHeader("Accept",       "application/json");
        request.addHeader("Authorization", convertCredentials(username, pass));
		
		HttpEntity httpEntity = null;
		httpEntity = new StringEntity(requestBody, ContentType.APPLICATION_JSON);
		request.setEntity(httpEntity);
  
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			StatusLine sl = response.getStatusLine();           
			 if (sl.getStatusCode() == 401) {
				 LOGGER.info("Unauthorized!");
			 }
			 
	        HttpEntity entity = response.getEntity();
	
	        if (entity != null) {
	            String result = EntityUtils.toString(entity);
	            LOGGER.info(result);
	        }
		} 
	}
	
	private static String convertCredentials(String username, String pass) {
		String authString = username + ":" + pass;
        byte[] authEncBytes = Base64.getEncoder().encode(authString.getBytes());
        String authStringEnc = new String(authEncBytes);

        return "Basic " + authStringEnc;
	}
}
