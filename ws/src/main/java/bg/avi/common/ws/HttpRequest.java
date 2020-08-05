package bg.avi.common.ws;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public class HttpRequest {
	private HttpHeaders headers;
	private HttpComponentsClientHttpRequestFactory factory;

	public HttpRequest() {
		initializeVariables();
	}

	public HttpRequest(int connectTimeout, int readTimeout) {
		initializeVariables(connectTimeout, readTimeout);
	}
	
	public HttpRequest(Map<String, String> headers, int connectTimeout, int readTimeout) {
		initializeVariables(connectTimeout, readTimeout);
		addHeaders(headers);
	}

	public HttpRequest(String username, String password, Map<String, String> headers, int connectTimeout,
			int readTimeout) {
		initializeVariables(connectTimeout, readTimeout);
		String token = getBase64EncodedAccount(username, password);
		setAuthorization(AuthorizationType.BASIC, token);
		addHeaders(headers);
	}

	public HttpRequest(AuthorizationType authType, String token, Map<String, String> headers, int connectTimeout,
			int readTimeout) {
		initializeVariables(connectTimeout, readTimeout);
		setAuthorization(authType, token);
		addHeaders(headers);
	}

	private void initializeVariables() {
		this.headers = new HttpHeaders();
		factory = new HttpComponentsClientHttpRequestFactory();
	}

	private void initializeVariables(int connectTimeout, int readTimeout) {
		initializeVariables();
		factory.setConnectTimeout(connectTimeout);
		factory.setReadTimeout(readTimeout);
	}

	private void addHeaders(Map<String, String> headers) {
		for (Entry<String, String> entry : headers.entrySet()) {
			this.headers.add(entry.getKey(), entry.getValue());
		}
	}

	private void setAuthorization(AuthorizationType authType, String token) {
		this.headers.add("Authorization", authType.toString() + " " + token);
	}

	public void trustAllSources() {
		try {
			TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

			SSLContext sslContext;
			sslContext = SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

			SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

			CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

			factory.setHttpClient(httpClient);
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			throw new RuntimeException("Unexpected exception while building the certificate-ignoring SSLContext.", e);
		}
	}

	public <T> ResponseEntity<T> doGet(Map<String, String> params, String url, Class<T> clazz) throws URISyntaxException {
		HttpEntity<?> entity = new HttpEntity<>(headers);
		RestTemplate restTemplate = new RestTemplate(factory);
		URIBuilder b = new URIBuilder(url);
		
		if (params != null && !params.isEmpty())
			for (Entry<String, String> param : params.entrySet())
				b.addParameter(param.getKey(), param.getValue());
		
		return restTemplate.exchange(b.build(), HttpMethod.GET, entity, clazz);
	}
	
	public <T> ResponseEntity<T> doPost(Object obj, String url, Class<T> clazz) {
		HttpEntity<?> entity = new HttpEntity<>(obj, headers);

		RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate.exchange(url, HttpMethod.POST, entity, clazz);
	}

	public static String getBase64EncodedAccount(String username, String password) {
		try {
			String auth = username + ":" + password;
			return Base64.getEncoder().encodeToString(auth.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// UTF-8 is a supported encoding so this exception should not happen
			return null;
		}
	}

	public enum AuthorizationType {
		BASIC("Basic"), API_KEY("App"), IBSSO("IBSSO");

		private final String name;

		private AuthorizationType(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
	
	public static String encode(String string) {
		try {
			return URLEncoder.encode(string, "UTF-8");
		} catch (Exception e) { // Should not happen
			throw new RuntimeException(e);
		}
	}
	
	public static String decode(String string) {
		try {
			return URLDecoder.decode(string, "UTF-8");
		} catch (Exception e) { // Should not happen
			throw new RuntimeException(e);
		}
	}
}

