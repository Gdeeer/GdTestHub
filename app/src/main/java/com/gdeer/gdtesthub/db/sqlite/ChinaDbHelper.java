package com.gdeer.gdtesthub.db.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class ChinaDbHelper extends SQLiteOpenHelper {
    public ChinaDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("zhangjl", "db onCreate");
        String createCity1 = "create table city (" +
            "id integer primary key autoincrement, " +
            "city_id text, " +
            "city_name text)";
        db.execSQL(createCity1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("zhangjl", "db onUpgrade");
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }
}
