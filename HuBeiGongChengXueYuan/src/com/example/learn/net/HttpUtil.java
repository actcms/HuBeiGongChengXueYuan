package com.example.learn.net;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.NameValuePair;
import android.util.Log;

public class HttpUtil {
	// Httpget
	public void httpGet(final String param, final HttpCallbackListener listener) {
		Log.i("HttpUtil", "url=" + param);
		try {
			HttpGet httpGet = new HttpGet(param);
			HttpClient httpClient = new DefaultHttpClient();
			// // 请求超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
			// 20000);
			// // 读取超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
			// 20000);
			HttpResponse httpResponse = httpClient.execute(httpGet);

			InputStream inputStream = httpResponse.getEntity().getContent();
			InputStreamReader inputRead = new InputStreamReader(inputStream,
					"utf-8");
			BufferedReader bufferReader = new BufferedReader(inputRead);
			String data = "";
			StringBuffer stringBuffer = new StringBuffer();
			while ((data = bufferReader.readLine()) != null) {
				stringBuffer.append(data);
			}
			Log.i("HttpUtil", stringBuffer.toString());
			listener.onFinish(httpClient, inputStream, stringBuffer.toString());
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			listener.onError(e);
		}

	}

	// Httpget 带cookie
	public void httpGet(final String param, final String cookie,
			final HttpCallbackListener listener) {
		Log.i("HttpUtil", "url=" + param);
		try {
			HttpGet httpGet = new HttpGet(param);
			HttpClient httpClient = new DefaultHttpClient();
			httpGet.setHeader("cookie", "JSESSIONID=" + cookie);
			// // 请求超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
			// 20000);
			// // 读取超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
			// 20000);
			HttpResponse httpResponse = httpClient.execute(httpGet);

			InputStream inputStream = httpResponse.getEntity().getContent();
			InputStreamReader inputRead = new InputStreamReader(inputStream,
					"utf-8");
			BufferedReader bufferReader = new BufferedReader(inputRead);
			String data = "";
			StringBuffer stringBuffer = new StringBuffer();
			while ((data = bufferReader.readLine()) != null) {
				stringBuffer.append(data);
			}
			Log.i("HttpUtil", stringBuffer.toString());
			listener.onFinish(httpClient, inputStream, stringBuffer.toString());
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			listener.onError(e);
		}

	}

	// HttpPost
	public void httpPost(final String param, List<NameValuePair> postParams,
			final HttpCallbackListener listener) {
		try {
			HttpPost httpPost = new HttpPost(param);
			HttpClient httpClient = new DefaultHttpClient();
			// // 请求超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
			// 20000);
			// // 读取超时
			// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
			// 20000);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams,
					"utf-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			InputStream inputStream = httpResponse.getEntity().getContent();
			InputStreamReader inputRead = new InputStreamReader(inputStream,
					"utf-8");
			BufferedReader bufferReader = new BufferedReader(inputRead);
			String data = "";
			StringBuffer stringBuffer = new StringBuffer();
			while ((data = bufferReader.readLine()) != null) {
				stringBuffer.append(data);
			}
			Log.i("HttpUtil", stringBuffer.toString());
			listener.onFinish(httpClient, inputStream, stringBuffer.toString());
		} catch (Exception e) {
			Log.i("HttpUtil", e.toString());
			listener.onError(e);
		}
	}
	
	// HttpPost带cookie
		public void httpPost(final String param,final String cookie, List<NameValuePair> postParams,
				final HttpCallbackListener listener) {
			try {
				HttpPost httpPost = new HttpPost(param);
				HttpClient httpClient = new DefaultHttpClient();
				httpPost.setHeader("cookie", "JSESSIONID=" + cookie);
				// // 请求超时
				// httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
				// 20000);
				// // 读取超时
				// httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
				// 20000);
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(postParams,
						"utf-8");
				httpPost.setEntity(entity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				InputStream inputStream = httpResponse.getEntity().getContent();
				InputStreamReader inputRead = new InputStreamReader(inputStream,
						"utf-8");
				BufferedReader bufferReader = new BufferedReader(inputRead);
				String data = "";
				StringBuffer stringBuffer = new StringBuffer();
				while ((data = bufferReader.readLine()) != null) {
					stringBuffer.append(data);
				}
				Log.i("HttpUtil", stringBuffer.toString());
				listener.onFinish(httpClient, inputStream, stringBuffer.toString());
			} catch (Exception e) {
				Log.i("HttpUtil", e.toString());
				listener.onError(e);
			}
		}
}
