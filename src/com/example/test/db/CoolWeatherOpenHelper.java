package com.example.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	//注意 点后面要一个空格!
	/**
	 *  Province表建表语句
	 */
	public static final String CREATE_PROVINCE = "create table Province ("
				+ "id integer primary key autoincrement, " 
				+ "province_name text, "
				+ "province_code text)";
	/**
	 *  City表建表语句
	 */
	public static final String CREATE_CITY = "create table City ("
				+ "id integer primary key autoincrement, " 
				+ "city_name text, " 
				+ "city_code text, " 
				+ "province_id integer)";
	/**
	 *  County表建表语句
	 */
	public static final String CREATE_COUNTY = "create table County ("
				+ "id integer primary key autoincrement, " 
				+ "county_name text, " 
				+ "county_code text, " 
				+ "city_id integer)";
	//注意手动打包 CursorFactory类,不可以自动在前面加上,就会出错.
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory,
			//指数据库版本号（从1开始）
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);  // 创建Province表
		db.execSQL(CREATE_CITY);  // 创建City表
		db.execSQL(CREATE_COUNTY);  // 创建Country表
	}

	@Override
	//onUpgrade方法会在数据库需要升级的时候调用。可以用来增删表或者其他任何操作。
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}
