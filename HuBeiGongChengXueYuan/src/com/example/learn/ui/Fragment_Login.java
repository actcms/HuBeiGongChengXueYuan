package com.example.learn.ui;

import java.util.List;

import com.example.learn.R;
import com.example.learn.model.UserDB;
import com.example.learn.tool.MyApplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class Fragment_Login extends Fragment{
	private View view;
	private EditText studentID;
	private EditText password;
	private EditText checkCode;
	private ImageView checkCodePhoto;
	private Button enter;
	private Button refresh;
	private CheckBox scoreCheckBox;
	private CheckBox timetableCheckBox;
	private CheckBox bxCheckBox;
	private CheckBox gradeTestCheckBox;
	private TextView classtime;
	private UserDB userDB;
	private Context context;
	
	
	
	@SuppressLint("InflateParams")
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		view=inflater.inflate(R.layout.login, null, false);
		initialize();
		context=MyApplication.getContext();
		return view;
	}
	
	private void initialize(){
		studentID=(EditText) view.findViewById(R.id.studentID);
		password=(EditText) view.findViewById(R.id.password);
		checkCode=(EditText) view.findViewById(R.id.checkCode);
		checkCodePhoto=(ImageView)view.findViewById(R.id.checkCodePhoto);
		enter=(Button)view.findViewById(R.id.enter);
		refresh=(Button)view.findViewById(R.id.refresh);
		classtime=(TextView)view.findViewById(R.id.classtime);
		scoreCheckBox=(CheckBox)view.findViewById(R.id.scoreCheckBox);
		timetableCheckBox=(CheckBox)view.findViewById(R.id.timetableCheckBox);
		bxCheckBox=(CheckBox)view.findViewById(R.id.bxCheckBox);
		gradeTestCheckBox=(CheckBox)view.findViewById(R.id.gradeTestCheckBox);
	
		userDB=UserDB.getInstance(context);
		enter.setOnClickListener(new EnterListener());
		refresh.setOnClickListener(new RefreshListener());
	}
	
	
	public void setStudentNub(String studentID,String password){
		this.studentID.setText(studentID);
		this.password.setText(password);
	}
	
	public void setCheckCodePhoto(Bitmap checkCodePhoto){
		this.checkCodePhoto.setImageBitmap(checkCodePhoto);
	}
	
	private void saveIDPassword(){
		String id=this.studentID.getText().toString();
		String password=this.password.getText().toString();
		userDB.saveUserNub(id, password);
	}
	
	class EnterListener implements OnClickListener{

		public void onClick(View v) {
			saveIDPassword();
		}
		
	}
	
	class RefreshListener implements OnClickListener{

		public void onClick(View v) {
			
		}
		
	}
	
	
}
