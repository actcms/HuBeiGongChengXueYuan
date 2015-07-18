package com.example.learn.presenter;

import com.example.learn.net.NetPresenter;

import android.graphics.Bitmap;

public class Fragment_Login_Presenter {
	private NetPresenter netPresenter;
	
	/**
	 * 获取验证码图片
	 */
	public Bitmap getCheckCodePhoto(){
		Bitmap bitmap;
		netPresenter.getCookie();
		//bitmap可能为null
		bitmap=netPresenter.getCheckCodePhoto();
		return bitmap;
	}
	
	
	/**
	 * 如果返回1，则验证成功，返回0则用户名，密码或者验证码错误
	 */
	public int logIn(String id,String password){
		return netPresenter.logIn(id, password);
	}
	
	/**
	 * if return 1,success;else return 0,fail
	 */
	public int getScore(){
		return netPresenter.getScore();
	}
	
	/**
	 * if return 1,success;else return 0,fail
	 */
	public int getNews(){
		return netPresenter.getNews();
	}
	/**
	 * if return 1,success;else return 0,fail
	 */
	public int getClass(String time){
		return netPresenter.getClass(time);
	}
	
	/**
	 * if return 1,success;else return 0,fail
	 */
	public int getAllClass(){
		return netPresenter.getAllClass();
	}
	/**
	 * if return 1,success;else return 0,fail
	 */
	public int getTest(){
		return netPresenter.getTest();
	}
	
	
	
	
	
	
}
