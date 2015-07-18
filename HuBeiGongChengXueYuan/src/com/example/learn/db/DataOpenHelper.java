package com.example.learn.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper{

private static final String Class="create table Score(" + "id integer primary key autoincrement," + "time text,"
		+ "name text," + "score text,"+ "type text," + "studyTime text," + "studyScore text)";

private static final String News="create table News(" + "id integer primary key autoincrement," + "title text," +
		 "url text)";

private static final String AllClass="create table AllClass(" + "id integer primary key autoincrement," + "name text," + 
		 "studyTime text," + "studyScore text," + "term text, " +  "testType text)";
	
private static final String GradeTest="create table GradeTest(" + "id integer primary key autoincrement," + "name text," +
"end text," + "score text)";

private static final String MyClass="create table MyClass(" + "id integer primary key autoincrement," + "className text,"+
"teacherName text," + "classPlace text," + "classWeek text," + "classNum integer)";

public DataOpenHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(Class);
		db.execSQL(News);
		db.execSQL(AllClass);
		db.execSQL(MyClass);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
