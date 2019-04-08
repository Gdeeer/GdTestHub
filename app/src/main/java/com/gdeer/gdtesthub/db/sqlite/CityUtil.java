package com.gdeer.gdtesthub.db.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class CityUtil {

    private static SQLiteDatabase chinaDb;

    public static void accessDB(Context context) {
        ChinaDbHelper chinaDbHelper = new ChinaDbHelper(context, "china.db", null, 1);
        // getWritableDatabase 时会调 onCreate、onUpgrade、onDowngrade
        chinaDb = chinaDbHelper.getWritableDatabase();
    }

    /**
     * 创建 city1、city2 表
     */
    public static void createTable() {
        String createCity1 = "create table city1 (" +
            "id integer primary key autoincrement, " +
            "city_id text)";
        chinaDb.execSQL(createCity1);

        String createCity2 = "create table city2 (" +
            "id integer primary key autoincrement, " +
            "city_id text)";
        chinaDb.execSQL(createCity2);
    }

    /**
     * 更新 city1 表名至 city
     * 给 city 中增加一列 city_name
     */
    public static void updateTable() {
        String renameTable = "alter table city1 rename to city";
        chinaDb.execSQL(renameTable);
        String addColumn = "alter table city add column city_name text";
        chinaDb.execSQL(addColumn);
    }

    /**
     * 删除 city2 表
     */
    public static void deleteTable() {
        String deleteTable = "drop table city2";
        chinaDb.execSQL(deleteTable);
    }

    /**
     * 删除所有表
     */
    public static void deleteAllTable() {
        List<String> list = getAllTable();
        for (String s : list) {
            if ("sqlite_sequence".equals(s)) {
                continue;
            }
            String deleteTable = "drop table " + s;
            chinaDb.execSQL(deleteTable);
        }
    }

    /**
     * 获取库中所有表
     */
    public static List<String> getAllTable() {
        List<String> tables = new ArrayList<>();
        String getAllTable = "select name from sqlite_master where type='table' order by name";
        Cursor cursor = chinaDb.rawQuery(getAllTable, null);
        while (cursor.moveToNext()) {
            tables.add(cursor.getString(0));
        }
        cursor.close();
        return tables;
    }

    /**
     * 插入一条数据
     */
    public static void insertCity(City city) {
        ContentValues values = new ContentValues();
        values.put("city_id", city.getCityId());
        values.put("city_name", city.getCityName());
        chinaDb.insert("city", null, values);
    }

    /**
     * 删除一条数据
     */
    public static void deleteCity(City city) {
        chinaDb.delete("city", "city_id = ?", new String[]{city.getCityId()});
    }

    /**
     * 更新一条数据
     */
    public static void updateCity(City city) {
        ContentValues values = new ContentValues();
        values.put("city_name", city.getCityName());
        chinaDb.update("city", values, "city_id = ?", new String[]{city.getCityId()});
    }

    /**
     * 获取所有数据
     */
    public static List<City> getAllCity() {
        List<City> list = new ArrayList<>();
        Cursor cursor = chinaDb.rawQuery("select * from city", null);
        while (cursor.moveToNext()) {
            String cityId = cursor.getString(cursor.getColumnIndex("city_id"));
            String cityName = cursor.getString(cursor.getColumnIndex("city_name"));
            City city = new City(cityId, cityName);
            list.add(city);
        }
        cursor.close();
        return list;
    }

    /**
     * 获取某条数据
     */
    public static City getCity(String cityId) {
        Cursor cursor = chinaDb.rawQuery("select * from city where city_id = " + cityId, null);
        if (cursor.moveToNext()) {
            String _cityId = cursor.getString(cursor.getColumnIndex("city_id"));
            String _cityName = cursor.getString(cursor.getColumnIndex("city_name"));
            return new City(_cityId, _cityName);
        }
        cursor.close();
        return null;
    }
}
