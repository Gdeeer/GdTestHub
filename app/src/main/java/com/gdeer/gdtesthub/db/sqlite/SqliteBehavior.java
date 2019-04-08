package com.gdeer.gdtesthub.db.sqlite;

import android.content.Context;
import android.util.Log;

public class SqliteBehavior {

    private static final String TAG = "database-sqlite";

    public static void behave(Context context) {
        CityUtil.accessDB(context);
        CityUtil.deleteAllTable();

        CityUtil.createTable();
        Log.d(TAG, "after create table: " + CityUtil.getAllTable());

        CityUtil.updateTable();
        Log.d(TAG, "after update table: " + CityUtil.getAllTable());

        CityUtil.deleteTable();
        Log.d(TAG, "after delete table: " + CityUtil.getAllTable());

        CityUtil.insertCity(new City("1", "北京"));
        CityUtil.insertCity(new City("2", "上海"));
        CityUtil.insertCity(new City("3", "广州"));
        Log.d(TAG, "after insert: " + CityUtil.getAllCity());

        City city = CityUtil.getCity("3");
        city.setCityName("深圳");
        CityUtil.updateCity(city);
        Log.d(TAG, "after update: " + CityUtil.getAllCity());

        CityUtil.deleteCity(city);
        Log.d(TAG, "after delete: " + CityUtil.getAllCity());
    }

    public static void initTest(Context context) {
        CityUtil.accessDB(context);
        CityUtil.insertCity(new City("1", "灵宝"));
        CityUtil.insertCity(new City("2", "阳平"));
        Log.d(TAG, "initTest: " + CityUtil.getAllCity());
    }

    public static void replaceTest(Context context) {
        CityUtil.accessDB(context);

        Log.d(TAG, "replaceTest: " + CityUtil.getAllCity());
    }


}
