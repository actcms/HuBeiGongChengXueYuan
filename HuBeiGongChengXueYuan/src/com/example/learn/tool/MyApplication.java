package com.example.learn.tool;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class MyApplication extends Application {
	/*
	 * 在<application中添加
	 * 
    	android:name="com.example.learn.tool.MyApplication"
    */
	private static Context context;

	@Override
	public void onCreate() {
		Log.i("MyApplication", "onCreate");
		context = getApplicationContext();
	}

	public static Context getContext() {
		Log.i("MyApplication", "getContext");
		return context;
	}

}
