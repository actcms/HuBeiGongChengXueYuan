package com.example.learn.presenter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.learn.net.NetPresenter;
import com.example.learn.ui.Fragment_Login;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.util.Log;

public class Fragment_Login_Presenter {
	// private Fragment_Login fragment_Login;
	private NetPresenter netPresenter;

	// private Handler fragment_Login_Myhandler;

	public Fragment_Login_Presenter() {
		// fragment_Login = new Fragment_Login();
		// fragment_Login_Myhandler = fragment_Login.new MyHandler();
		netPresenter = NetPresenter.getInstence();
	}

	/**
	 * 向Fragment_Login发送消息
	 */

	private void sendMessageToFragment_Login(int param, Handler handler) {
		Message message = new Message();
		message.what = param;
		handler.sendMessage(message);
	}

	/**
	 * 获取验证码图片,并通知Fragment_Login更新验证码
	 */
	private Bitmap bitmap;

	public Bitmap getBitmap() {
		return this.bitmap;
	}

	public void getCheckCodePhoto(final Handler handler) {
		new Thread(new Runnable() {
			public void run() {
				// bitmap可能为null
				if (netPresenter.getCookie() == 1) {
					bitmap = netPresenter.getCheckCodePhoto();
					sendMessageToFragment_Login(1, handler);
					// saveMyBitmap("bitmap", bitmap);
					// sendMessageToFragment_Login(1);

				} else {
					// 检查网络，稍后重试
				}
			}
		}).start();
	}

	/**
	 * 如果返回1，则验证成功，返回0则用户名，密码或者验证码错误
	 */
	private int logNub = 0;

	public int logIn(final String id, final String password,
			final String checkCode, final Handler handler, final int a[]) {
		new Thread(new Runnable() {
			public void run() {
				int logNub = netPresenter.logIn(id, password, checkCode);
				sendMessageToFragment_Login(13, handler);
				if (a[0] == 1) {
					getScore();
				}
				if (a[1] == 1) {
					getMyClass("2014-2015-1");
				}
				if (a[2] == 1) {
					getAllClass();
				}
				if (a[3] == 1) {
					getTest();
				}

			}
		}).start();

		return logNub;
	}

	/**
	 * 期末成绩 if return 1,success;else return 0,fail
	 */
	private int scoreNub = 0;

	public int getScore() {
		Log.i("Fragment_Login_Presenter", "getScore");
		scoreNub = netPresenter.getScore();

		return scoreNub;
	}

	/**
	 * 校园新闻 if return 1,success;else return 0,fail
	 */
	private int newsNub = 0;

	public int getNews() {
		newsNub = netPresenter.getNews();

		return newsNub;
	}

	/**
	 * 课表 if return 1,success;else return 0,fail
	 */
	private int classNub = 0;

	public int getMyClass(String time) {
		classNub = netPresenter.getClass(time);
		return classNub;
	}

	/**
	 * 大学全部课程 if return 1,success;else return 0,fail
	 */
	private int allClassNub = 0;

	public int getAllClass() {

		allClassNub = netPresenter.getAllClass();

		return allClassNub;
	}

	/**
	 * 等级考试成绩 if return 1,success;else return 0,fail
	 */
	private int testNub = 0;

	public int getTest() {
		testNub = netPresenter.getTest();

		return testNub;
	}

}
