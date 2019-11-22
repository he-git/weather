package com.example.test.db;

import java.util.ArrayList;
import java.util.List;

import com.example.test.model.City;
import com.example.test.model.County;
import com.example.test.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class CoolWeatherDB {

	/**
	 * 数据库名
	 */
	public static final String DB_NAME = "cool_weather";

	/**
	 * 数据库版本
	 */
	public static final int VERSION = 1;
	//声明了一个公有的静态的常量值VERSION,并且初始化值为1

	private static CoolWeatherDB coolWeatherDB;
	//创建一个coolWeatherDB类,把一些常用的数据库操作进行封装，以便于后期使用。

	private SQLiteDatabase db;
	//单例模式
	//这个类用于管理和操作SQLite数据库，对数据库的操作离不开这个类

	/**
	 * 构造方法私有化
	 */
	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DB_NAME, null, VERSION); 
		//创建建表类，传入的参数分别是上下文，数据库名，第三个参数不用管，数据库版本
		db = dbHelper.getWritableDatabase();
		//通过getWritableDatabase()方法获取到db实例
	}

	/**
	 * 获取CoolWeatherDB的实例
	 */
	//synchronized 避免同步创建实例
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			//如果数据库的操作类对象不存在则创建对象
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	/**
	 * 将Province实例存储到数据库
	 */
	public void saveProvince(Province province) {
		if (province != null) {
			//ContentValues类是负责存储一些键值对，但是它存储的键值对当中的名是一个String类型
			//（第一个参数），而值都是基本类型。此类在这里用就是存储一下省的名字及代号
			ContentValues values = new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			db.insert("Province", null, values);
		}
	}

	/**
	 * 从数据库读取全国所有的省份信息
	 */
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		//list集合存储的均为Province类型
		//Cursor 类是每行的集合，先查询Province表将其中的数据内容放到Cursor类中
		Cursor cursor = db
				.query("Province", null, null, null, null, null, null);
		if (cursor.moveToFirst()) {//从第一行开始
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				//getColumnIndex是返回指定列的名称，然后用get方法获取到想要的值并放到新创建的Province类中    
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex("province_name")));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex("province_code")));
				list.add(province);//添加到集合中
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * 将City实例存储到数据库
	 */
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert("City", null, values);
		}
	}

	/**
	 * 从数据库读取某省下所有的城市信息
	 */
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query("City", null, "province_id = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex("city_name")));
				city.setCityCode(cursor.getString(cursor
						.getColumnIndex("city_code")));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());
		}
		return list;
	}

	/**
	 * 将Country实例存储到数据库
	 */
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put("county_name", county.getCountyName());
			values.put("county_code", county.getCountyCode());
			values.put("city_id", county.getCityId());
			db.insert("County", null, values);
		}
	}

	/**
	 * 从数据库读取某城市下所有的县信息
	 */
	public List<County> loadCounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query("County", null, "city_id = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex("county_name")));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex("county_code")));
				county.setCityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());
		}
		return list;
	}

}
