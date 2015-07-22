package com.example.learn;

import java.util.Random;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.bluemor.reddotface.activity.ImageActivity;
import com.bluemor.reddotface.adapter.ImageAdapter;
import com.bluemor.reddotface.util.Callback;
import com.bluemor.reddotface.util.Invoker;
import com.bluemor.reddotface.util.Util;
import com.bluemor.reddotface.view.DragLayout;
import com.bluemor.reddotface.view.DragLayout.DragListener;
import com.example.learn.R;
import com.example.learn.model.MyClass;
import com.example.learn.ui.Fragment_Login;
import com.example.learn.ui.Fragment_MyClass;
import com.example.learn.ui.Fragment_Score;
import com.nineoldandroids.view.ViewHelper;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MainActivity extends FragmentActivity {
	


	


	private MyHandler myHandler;
	private DragLayout dl;
//	private GridView gv_img;
	private ImageAdapter adapter;
	private ListView lv;
//	private TextView tv_noimg;
	private ImageView iv_icon, iv_bottom;
	private FrameLayout frameLayout;
	private Fragment fragment_myClass;
	private Fragment fragment_login;
	private Fragment_Score fragment_Score;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Util.initImageLoader(this);
		
		//初始化
		initView();
		//费时动作
		initLongTime();
		
		initDragLayout();
//		changeFrameLayout(fragment_login);
		
		
	}

	//侧方菜单
	private void initDragLayout() {
		
		dl.setDragListener(new DragListener() {
			@Override
			public void onOpen() {
				lv.smoothScrollToPosition(new Random().nextInt(30));
			}

			@Override
			public void onClose() {
				//shake();
			}

			@Override
			public void onDrag(float percent) {
				ViewHelper.setAlpha(iv_icon, 1 - percent);
			}
		});
	}

	private void changeFrameLayout(Fragment targetFragment){
			Log.i("MainActivity", "changeFrameLayout");
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//			ft.add(R.id.main_fragment, targetFragment, "fragment");
			ft.replace(R.id.main_frameLayout, targetFragment, "fragment");
			ft.setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
			ft.commit();
	}
	
	/**
	 * 费时命令
	 */
	private void initLongTime(){
		new Thread(new Runnable() {
			public void run() {
				fragment_myClass=new Fragment_MyClass();
				sengTheMessage(0);
				fragment_login=new Fragment_Login();
				fragment_Score=new Fragment_Score();
			}
		}).start();
	}
	
	private void initView() {
		
		dl = (DragLayout) findViewById(R.id.dl);
		frameLayout=(FrameLayout)findViewById(R.id.main_frameLayout);
		iv_icon = (ImageView) findViewById(R.id.iv_icon);
		iv_bottom = (ImageView) findViewById(R.id.iv_bottom);
		myHandler=new MyHandler();
		
		
		
//		gv_img = (GridView) findViewById(R.id.gv_img);
//		tv_noimg = (TextView) findViewById(R.id.iv_noimg);
//		gv_img.setFastScrollEnabled(true);
		adapter = new ImageAdapter(this);
//		gv_img.setAdapter(adapter);
//		gv_img.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> parent, View view,
//					int position, long id) {
//				Intent intent = new Intent(MainActivity.this,
//						ImageActivity.class);
//				intent.putExtra("path", adapter.getItem(position));
//				startActivity(intent);
//			}
//		});
		//侧方listview
		lv = (ListView) findViewById(R.id.lv);
		lv.setAdapter(new ArrayAdapter<String>(MainActivity.this,
				R.layout.item_text, new String[] { "课表", "登录",
						"成绩", "必修课程", "等级考试" }));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
//				Util.t(getApplicationContext(), "click " + position);
				final int aa=position;
				switch (position) {
				case 0:
					new Thread(new Runnable() {
						public void run() {
							try {
								Thread.sleep(150);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							fragment_myClass=new Fragment_MyClass();
							sengTheMessage(aa);
						}
					}).start();
					break;
				case 1:
					sengTheMessage(position);
					break;
				case 2:
					sengTheMessage(position);
					break;
				default:
					break;
				}
				dl.close();
			}
		});
		iv_icon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				dl.open();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		//loadImage();
	}

	private void sengTheMessage(int param){
		Message message=new Message();
		message.what=param;
		myHandler.sendMessage(message);
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
//		Log.i("MainActivity", "dispatchTouchEvent:"+super.dispatchTouchEvent(ev));
		return super.dispatchTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
//		Log.i("MainActivity", "dispatchTouchEvent:"+super.dispatchTouchEvent(ev));
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
	
	
	class MyHandler extends Handler{

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				changeFrameLayout(fragment_myClass);
				break;
			case 1:
				changeFrameLayout(fragment_login);
				break;
			case 2:
				changeFrameLayout(fragment_Score);
			default:
				break;
			}
		}
		
	}
	
}
