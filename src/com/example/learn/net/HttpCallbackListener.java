package com.example.learn.net;

import java.io.InputStream;

import org.apache.http.client.HttpClient;

public interface HttpCallbackListener {
	void onFinish(String response);
	void onError(Exception e);
}
