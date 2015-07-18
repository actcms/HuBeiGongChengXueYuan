package com.example.learn.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class NetPresenter {
	private HttpUtil httpUtil;
	private Analysis analysis;
	private static NetPresenter netPresenter;
	// cookie
	public static String JSESSIONID;
	// 验证码
	Bitmap bitmap;
	// 获取cookie
	String url0 = "http://jwgl.hbeu.cn:8080/hbgcxy/";
	// 获取验证码
	String url1 = "http://jwgl.hbeu.cn:8080/hbgcxy/verifycode.servlet";
	// 登陆地址
	String url2 = "http://jwgl.hbeu.cn:8080/hbgcxy/Logon.do?method=logon";
	// 登入页面
	String url3 = "http://jwgl.hbeu.cn:8080/hbgcxy/Logon.do?method=logonBySSO";
	// 成绩查询
	String url4 = "http://jwgl.hbeu.cn:8080/hbgcxy/xszqcjglAction.do?method=queryxscj";
	// 校园新闻网站的地址
	String url5 = "http://news.hbeu.cn";
	// 此网站前必须进 URL3 课表的网站，后面要定义所查的学期
	String url6 = "http://jwgl.hbeu.cn:8080/hbgcxy/tkglAction.do?method=goListKbByXs&istsxx=no&xnxqh=";
	// 大学全部课程url
	String url7 = "http://jwgl.hbeu.cn:8080/hbgcxy/pyfajhgl.do?method=toViewJxjhXs&tktime=1414123888000";
	// 大学等级证书url
	String url8 = "http://jwgl.hbeu.cn:8080/hbgcxy/kjlbgl.do?method=findXskjcjXszq&tktime=1414124059000";

	private NetPresenter() {
		httpUtil = new HttpUtil();
	}

	public static NetPresenter getInstence() {
		if (netPresenter != null) {
			netPresenter = new NetPresenter();
		}
		return netPresenter;

	}

	// 获取cookie
	public void getCookie() {

		new HttpUtil().httpGet(url0, new HttpCallbackListener() {

			@Override
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
				List<Cookie> cookie = ((AbstractHttpClient) httpClient)
						.getCookieStore().getCookies();
				JSESSIONID = cookie.get(0).getValue();
			}

			public void onError(Exception e) {
				Log.e("NetPresenter", e.toString());
			}
		});

	}

	// 获取验证码
	public Bitmap getCheckCodePhoto() {
		new HttpUtil().httpGet(url1, new HttpCallbackListener() {

			@Override
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
				bitmap = BitmapFactory.decodeStream(inputStream);
			}

			public void onError(Exception e) {
				Log.e("NetPresenter", e.toString());
				bitmap=null;
			}
		});
		return bitmap;

	}
	private int logInNub=0;
	// 带cookie和用户名密码和验证码进行验证
	public int logIn(String user,String password) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("PASSWORD", password));
		params.add(new BasicNameValuePair("RANDOMCODE", ""));
		params.add(new BasicNameValuePair("USERNAME", user));
		new HttpUtil().httpPost(url2, JSESSIONID, params,
				new HttpCallbackListener() {
					public void onFinish(HttpClient httpClient,
							InputStream inputStream, String response) {
						logInNub=1;
						
						testCookie2();
						
						Log.i("get1", "success");
					}

					@Override
					public void onError(Exception e) {
						Log.e("NetPresenter", e.toString());
						logInNub=0;
					}
				});
		return logInNub;
	}

	/**
	 *  进入成绩查询预备页面，不进此页面直接查成绩会报错
	 */
	public void testCookie2() {
		new HttpUtil().httpGet(url3, JSESSIONID, new HttpCallbackListener() {
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
			}

			public void onError(Exception e) {
			}
		});

	}
	private int scoreNub=0;
	// 进入成绩查询界面，返回页面html文件
	public int getScore() {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("kcmc", null));
		params.add(new BasicNameValuePair("kcxz", null));
		params.add(new BasicNameValuePair("kksj", null));
		params.add(new BasicNameValuePair("ok", null));
		params.add(new BasicNameValuePair("xsfs", "qbcj"));
		new HttpUtil().httpPost(url4, JSESSIONID, params,
				new HttpCallbackListener() {
					public void onFinish(HttpClient httpClient,
							InputStream inputStream, String response) {
						scoreNub=1;
						analysis.analysisScore(response);
					}
					public void onError(Exception e) {
						scoreNub=0;
						Log.e("", e.toString());
					}
				});
		return scoreNub;
	}

	private int newsNub=0;
	// 登入校园新闻网，并爬去数据
	public int getNews() {
		new HttpUtil().httpGet(url5, new HttpCallbackListener() {

			@Override
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
				newsNub=1;
				analysis.analysisNews(response);
			}

			public void onError(Exception e) {
				// TODO Auto-generated method stub
				newsNub=0;

			}
		});
		return newsNub;
	}

	private int classNub=0;
	
	// 获得课程表的html
	public int getClass(String a) {
		new HttpUtil().httpGet(url6 + a, JSESSIONID,
				new HttpCallbackListener() {
					public void onFinish(HttpClient httpClient,
							InputStream inputStream, String response) {
						classNub=1;
						analysis.analysisMyClass(response);
					}

					@Override
					public void onError(Exception e) {
						classNub=0;
					}
				});
		return classNub;
	}
	private int allClassNub=0;
	// 获得全部成绩的的html
	public int getAllClass() {
		new HttpUtil().httpGet(url7, JSESSIONID, new HttpCallbackListener() {

			@Override
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
				allClassNub=1;
				analysis.analyseAllClass(response);

			}

			@Override
			public void onError(Exception e) {
				allClassNub=0;
				// TODO Auto-generated method stub

			}
		});
		return allClassNub;
	}

	
	private int testNub=0;
	// 获得等级考试的的的html
	public int getTest() {
		new HttpUtil().httpGet(url8, JSESSIONID, new HttpCallbackListener() {

			@Override
			public void onFinish(HttpClient httpClient,
					InputStream inputStream, String response) {
				testNub=1;
				analysis.analysisGradeTest(response);
			}

			@Override
			public void onError(Exception e) {
				testNub=0;
			}
		});
		return testNub;
	}

}
