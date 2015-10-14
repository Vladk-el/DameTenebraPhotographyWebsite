package com.vladkel.dametenebra.utils.http;

import java.io.InputStream;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

/**
 * @author eliott
 *
 */
@SuppressWarnings("deprecation")
public class PostHelper {

	protected int timeOut = 10000;

	protected String user;

	protected String pwd;

	public PostHelper() {

	}

	public PostHelper(int timeOut) {
		this.timeOut = timeOut;
	}

	public PostHelper(String user, String pwd, int timeOut) {
		this.user = user;
		this.pwd = pwd;
		this.timeOut = timeOut;
	}

	public PostHelper(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	public void setTimeOut(int timeOut) {
		this.timeOut = timeOut;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void setAuth(String user, String pwd) {
		this.user = user;
		this.pwd = pwd;
	}

	protected HttpClient getClient() {
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, timeOut);
		HttpConnectionParams.setSoTimeout(httpParams, timeOut);
		HttpProtocolParams.setVersion(httpParams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(httpParams, "UTF-8");
		return new DefaultHttpClient(httpParams);
	}

	private HttpResponse getResponse(String url, String requestBody, Map<String, String> headers) {

		HttpClient httpClient = getClient();
		HttpPost post = new HttpPost(url);

		try {

			if (user != null && pwd != null)
				post.addHeader(new BasicScheme().authenticate(new UsernamePasswordCredentials(user, pwd), post));

			post.setEntity(new StringEntity(requestBody));

			for (String headerName : headers.keySet())
				post.addHeader(headerName, headers.get(headerName));

			HttpResponse response = httpClient.execute(post);
			return response;

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return null;
	}

	public InputStream post(String url, String requestBody, Map<String, String> headers) {

		InputStream responseBody = null;

		try {

			HttpResponse response = getResponse(url, requestBody, headers);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = entity.getContent();
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return responseBody;
	}

	public String postAsString(String url, String requestBody, Map<String, String> headers) {

		String responseBody = null;

		try {

			HttpResponse response = getResponse(url, requestBody, headers);
			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					responseBody = EntityUtils.toString(entity, "UTF-8");
					EntityUtils.consume(entity);
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return responseBody;
	}

}
