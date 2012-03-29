package com.supinfo.geekquote.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "geekquote.db";
	private static final int DATABASE_VERSION = 1;
	
	private static final String TABLE = "quotes";
	private static final String CREATE = "create table " + TABLE + " (" +
			"id integer primary key autoincrement, " +
			"strquote text, " +
			"creation_date date, " +
			"rating integer);";
	
	
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("drop table if exists " + TABLE);
		onCreate(db);
	}

}
