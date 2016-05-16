package com.jlm.app.jianlemei_demo.activity.home.examcenter;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

	private final static String SUBTIME="jsd_jf_submit.db";
	private final static int DB_VERSION = 3;// 数据库版本号
	private static DatabaseHelper databaseHelper;
	 
	
	
	private DatabaseHelper(Context context) {
		super(context, SUBTIME, null, DB_VERSION);
	}

	public static DatabaseHelper getInstance(Context context,String userId){
		if(databaseHelper==null){
			databaseHelper=new DatabaseHelper(context.getApplicationContext());
	   SQLiteDatabase db=databaseHelper.getWritableDatabase();
			
		ContentValues values=new ContentValues();
			values.put("userid", userId);
			values.put("year", 2015);
			values.put("month", 1);
			values.put("day", 1);
			db.insert("subtime", null, values);
			
		ContentValues value=new ContentValues();
			value.put("userId", userId);
			value.put("backIcon", "null");
			value.put("userIcon", "null");
			value.put("userName", "null");
			value.put("userZhang", "null");
			value.put("userQian", "null");
			value.put("userSex", "null");
			value.put("userBirth", "null");
			value.put("userAddress", "null");
			db.insert("user", null, value);
		
		
	  ContentValues valueS=new ContentValues();
	   valueS.put("userId", userId);
	   valueS.put("step", "0");
	   valueS.put("time", "null");
	   valueS.put("date", "null");
	   db.insert("step", null, valueS);
		
    }
		
		return databaseHelper;
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		
		String sql = "CREATE TABLE IF NOT EXISTS subtime (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , userid text, year INTEGER, month INTEGER, day INTEGER)"; 
        db.execSQL(sql);
        

        String step = "CREATE TABLE IF NOT EXISTS step(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,userId text,step INTEGER, time text,date text, year text, month text, day text)";
        db.execSQL(step);
        

        String user = "CREATE TABLE IF NOT EXISTS user (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, userId text, backIcon BLOB NULL, userIcon BLOB NULL, userName text NULL, userZhang text NULL, userQian text NULL, userSex text NULL, userBirth text NULL, userAddress text NULL)";
        db.execSQL(user);		

	}


	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {		


	}

}
