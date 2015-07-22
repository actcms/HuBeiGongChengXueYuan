package com.example.learn.presenter;

import java.util.List;

import com.example.learn.model.DataDB;
import com.example.learn.model.WeekClass;
import com.example.learn.tool.MyApplication;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

public class Fragment_MyClass_Presenter {
	private DataDB dataDB;
	private Context context;

	public Fragment_MyClass_Presenter() {
		this.context = MyApplication.getContext();
		dataDB = DataDB.getInstance(context);
	}

	public void setClassButtonText(final Handler handler, final List list,
			final String week) {
		new Thread(new Runnable() {
			public void run() {
				
				List classList = dataDB.loadMyClass(week);
				Log.i("Fragment_MyClass_Presenter", list.size() + "=="
						+ classList.size());
				Message msg;
				Bundle bundle;
				for (int i = 0; i < classList.size(); i++) {
//					try {
//						Thread.sleep(0);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
					msg = new Message();
					bundle = new Bundle();
					WeekClass weekClass = (WeekClass) classList.get(i);
					int a = Integer.valueOf(weekClass.getClassNum()) - 1;
					bundle.putString("text", weekClass.getClassName()
							+"@" +weekClass.getClassPlace());
					bundle.putString("nub", a + "");
					msg.setData(bundle);
					msg.what = 1;
					handler.sendMessage(msg);
				}
			}
		}).start();
	}
}
