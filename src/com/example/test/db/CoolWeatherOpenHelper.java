package com.example.test.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper {
	//ע�� �����Ҫһ���ո�!
	/**
	 *  Province���������
	 */
	public static final String CREATE_PROVINCE = "create table Province ("
				+ "id integer primary key autoincrement, " 
				+ "province_name text, "
				+ "province_code text)";
	/**
	 *  City���������
	 */
	public static final String CREATE_CITY = "create table City ("
				+ "id integer primary key autoincrement, " 
				+ "city_name text, " 
				+ "city_code text, " 
				+ "province_id integer)";
	/**
	 *  County���������
	 */
	public static final String CREATE_COUNTY = "create table County ("
				+ "id integer primary key autoincrement, " 
				+ "county_name text, " 
				+ "county_code text, " 
				+ "city_id integer)";
	//ע���ֶ���� CursorFactory��,�������Զ���ǰ�����,�ͻ����.
	public CoolWeatherOpenHelper(Context context, String name, CursorFactory factory,
			//ָ���ݿ�汾�ţ���1��ʼ��
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);  // ����Province��
		db.execSQL(CREATE_CITY);  // ����City��
		db.execSQL(CREATE_COUNTY);  // ����Country��
	}

	@Override
	//onUpgrade�����������ݿ���Ҫ������ʱ����á�����������ɾ�����������κβ�����
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

}