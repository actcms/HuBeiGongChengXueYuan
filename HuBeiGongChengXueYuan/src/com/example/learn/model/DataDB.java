package com.example.learn.model;

import java.util.ArrayList;
import java.util.List;

import android.R.dimen;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.learn.db.DataOpenHelper;

public class DataDB {
	private static final String DBName = "Date";
	public static final int VERSION = 1;
	private static DataDB dataDB;
	private SQLiteDatabase db;

	private DataDB(Context context) {
		DataOpenHelper dataOpenHelper = new DataOpenHelper(context, DBName,
				null, VERSION);
		db = dataOpenHelper.getWritableDatabase();
	}

	public synchronized static DataDB getInstance(Context context) {
		if (dataDB == null) {
			dataDB = new DataDB(context);
		}
		return dataDB;
	}

	public void delTable(String tableName) {
		db.execSQL("DELETE FROM " + tableName);
	}

	public void saveMyScore(List list) {
		if (list.size() > 0) {
			delTable("Score");
			for (Object object : list) {
				MyScore myClass = (MyScore) object;
				ContentValues contentValues = new ContentValues();
				contentValues.put("time", myClass.getTime());
				contentValues.put("name", myClass.getName());
				contentValues.put("score", myClass.getScore());
				contentValues.put("type", myClass.getType());
				contentValues.put("studyTime", myClass.getStudyTime());
				contentValues.put("studyScore", myClass.getStudyScore());
				db.insert("Score", null, contentValues);
			}
		}
	}

	public List loadMyScore(String param) {
		List list = new ArrayList<MyScore>();
		Cursor cursor = db.query("Score", null, "time = ?",
				new String[] { param }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				MyScore myScore = new MyScore();
				myScore.setTime(cursor.getString(cursor.getColumnIndex("time")));
				myScore.setName(cursor.getString(cursor.getColumnIndex("name")));
				myScore.setScore(cursor.getString(cursor
						.getColumnIndex("score")));
				myScore.setType(cursor.getString(cursor.getColumnIndex("type")));
				myScore.setStudyTime(cursor.getString(cursor
						.getColumnIndex("studyTime")));
				myScore.setStudyScore(cursor.getString(cursor
						.getColumnIndex("studyScore")));
				list.add(myScore);
			} while (cursor.moveToNext());
		}
		return list;

	}

	public void saveNews(List title, List url) {
		if (title.size() > 0) {
			delTable("News");
			for (int i = 0; i < title.size(); i++) {
				String titleText = (String) title.get(i);
				String urlText = (String) url.get(i);
				ContentValues contentValues = new ContentValues();
				contentValues.put("title", titleText);
				contentValues.put("url", urlText);
				db.insert("News", null, contentValues);
			}
		}
	}

	public List loadNewsTitle() {
		List list = new ArrayList<String>();
		Cursor cursor = db.query("News", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(cursor.getColumnIndex("title")));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public List loadNewsUrl() {
		List list = new ArrayList<String>();
		Cursor cursor = db.query("News", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				list.add(cursor.getString(cursor.getColumnIndex("url")));
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveAllClass(List list) {
		if (list.size() > 0) {
			delTable("AllClass");
			for (Object object : list) {
				AllClass allClass = (AllClass) object;
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", allClass.getName());
				contentValues.put("studyTime", allClass.getStudyTime());
				contentValues.put("studyScore", allClass.getStudyScore());
				contentValues.put("term", allClass.getTerm());
				contentValues.put("testType", allClass.getTestType());
				db.insert("AllClass", null, contentValues);
			}
		}
	}

	public List loadAllClass() {
		List list = new ArrayList<AllClass>();
		Cursor cursor = db
				.query("AllClass", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				AllClass allClass = new AllClass();
				allClass.setName(cursor.getString(cursor.getColumnIndex("name")));
				allClass.setStudyTime(cursor.getString(cursor
						.getColumnIndex("studyTime")));
				allClass.setStudyScore(cursor.getString(cursor
						.getColumnIndex("studyScore")));
				allClass.setTerm(cursor.getString(cursor.getColumnIndex("term")));
				allClass.setTestType(cursor.getString(cursor
						.getColumnIndex("testType")));
				list.add(allClass);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveGradeTest(List list) {
		if (list.size() > 0) {
			delTable("GradeTest");
			for (Object object : list) {
				GradeTest gradeTest = (GradeTest) object;
				ContentValues contentValues = new ContentValues();
				contentValues.put("name", gradeTest.getName());
				contentValues.put("end", gradeTest.getEnd());
				contentValues.put("score", gradeTest.getScore());
				db.insert("GradeTest", null, contentValues);
			}
		}
	}

	public List loadGradeTest() {
		List list = new ArrayList<GradeTest>();
		Cursor cursor = db.query("GradeTest", null, null, null, null, null,
				null);
		if (cursor.moveToFirst()) {
			do {
				GradeTest gradeTest = new GradeTest();
				gradeTest.setName(cursor.getString(cursor
						.getColumnIndex("name")));
				gradeTest
						.setEnd(cursor.getString(cursor.getColumnIndex("end")));
				gradeTest.setScore(cursor.getString(cursor
						.getColumnIndex("score")));
				list.add(gradeTest);
			} while (cursor.moveToNext());
		}
		return list;
	}

	public void saveMyClass(ArrayList<MyClass> list) {
		if (list.size() == 42) {
			delTable("MyClass");
			for (Object object : list) {
				MyClass myClass = (MyClass) object;
				if (myClass != null) {
					int nub = ((ArrayList<String>) myClass.getClassName())
							.size();
					for (int i = 0; i < nub; i++) {
						ArrayList<String> className = (ArrayList<String>) myClass
								.getClassName();
						ArrayList<String> teacherName = (ArrayList<String>) myClass
								.getTeacherName();
						ArrayList<String> classPlace = (ArrayList<String>) myClass
								.getClassPlace();
						ArrayList<String> classWeek = (ArrayList<String>) myClass
								.getClassWeek();
						ArrayList<String> classNum = (ArrayList<String>) myClass
								.getClassNum();
						ContentValues contentValues = new ContentValues();
						contentValues.put("className", className.get(i));
						contentValues.put("teatherName", teacherName.get(i));
						contentValues.put("classPlace", classPlace.get(i));
						contentValues.put("classWeek", classWeek.get(i));
						contentValues.put("classNum", i);
						db.insert("MyClass", null, contentValues);
					}
				}
			}
		}
	}

	public MyClass loadMyClass() {
		MyClass myClass=new MyClass();
		Cursor cursor = db.query("MyClass", null, null, null, null, null, null);
		ArrayList<String> classNameList = new ArrayList<String>();
		ArrayList<String> teatherNameList = new ArrayList<String>();
		ArrayList<String> classPlaceList = new ArrayList<String>();
		ArrayList<String> classWeekList = new ArrayList<String>();
		ArrayList<String> classNumList = new ArrayList<String>();
		if (cursor.moveToFirst()) {
			do {
				String className = cursor.getColumnName(cursor
						.getColumnIndex("className"));
				String teatherName = cursor.getColumnName(cursor
						.getColumnIndex("teatherName"));
				String classPlace = cursor.getColumnName(cursor
						.getColumnIndex("classPlace"));
				String classWeek = cursor.getColumnName(cursor
						.getColumnIndex("classWeek"));
				String classNum = cursor.getColumnName(cursor
						.getColumnIndex("classNum"));
				classNameList.add(className);
				teatherNameList.add(teatherName);
				classPlaceList.add(classPlace);
				classWeekList.add(classWeek);
				classNumList.add(classNum);
			} while (cursor.moveToNext());
			myClass.setClassName(classNameList);
			myClass.setClassNum(classNumList);
			myClass.setClassPlace(classPlaceList);
			myClass.setClassWeek(classWeekList);
			myClass.setTeacherName(teatherNameList);
		}
		return myClass;
	}
}
