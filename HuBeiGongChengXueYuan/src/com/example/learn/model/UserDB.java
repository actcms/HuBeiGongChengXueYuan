package com.example.learn.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learn.db.UserOpenHelper;

public class UserDB {
	private static final String DBName = "User";
	private static final int Verson = 1;
	private static UserDB userDB;
	private SQLiteDatabase db;

	private UserDB(Context context) {
		UserOpenHelper userOpenHelper = new UserOpenHelper(context, DBName,
				null, Verson);
		db = userOpenHelper.getWritableDatabase();
	}

	public synchronized static UserDB getInstance(Context context) {
		if (userDB == null) {
			userDB = new UserDB(context);
		}
		return userDB;
	}

	/**
	 * 清空表
	 * 
	 * @param tableName
	 */
	public void del(String tableName) {
		db.execSQL("DELETE FROM " + tableName);
	}

	public void saveUserNub(String id, String password) {
		if (id != null && password != null) {
			if ("".equals(id) != true && "".equals(password) != true) {
				ContentValues contentValues = new ContentValues();
				contentValues.put("studentID", id);
				contentValues.put("password", password);
				db.insert("UserNumber", null, contentValues);
			}
		}
	}

	public List loadUserNub(String id) {
		List list = new ArrayList<String>();
		Cursor cursor = db.query("UserNumber", null, null, null, null, null,
				null);
		if (id != null) {
			if (id.equals("first")) {
				if (cursor.moveToFirst()) {
					String studentID = cursor.getColumnName(cursor
							.getColumnIndex("studentID"));
					list.add(studentID);
					list.add(cursor.getColumnName(cursor
							.getColumnIndex("password")));
				}
			}

			else if (cursor.moveToFirst()) {
				do {
					String studentID = cursor.getColumnName(cursor
							.getColumnIndex("studentID"));
					if (id != null && id.equals(studentID)) {
						list.add(studentID);
						list.add(cursor.getColumnName(cursor
								.getColumnIndex("password")));
					}
				} while (cursor.moveToNext());
			}
		}
		return list;
	}

}
