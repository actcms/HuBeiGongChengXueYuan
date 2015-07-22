package com.example.learn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserOpenHelper extends SQLiteOpenHelper{
private static final String UserNumber ="create table UserNumber(" + " id integer primary key autoincrement , " +
		" studentID text, " + " password text ) ";


	public UserOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		Log.i("UserOpenHelper", "UserOpenHelper");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		Log.i("UserOpenHelper", "onCreate");
		db.execSQL(UserNumber);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
