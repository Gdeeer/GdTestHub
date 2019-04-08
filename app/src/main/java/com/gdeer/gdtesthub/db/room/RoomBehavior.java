package com.gdeer.gdtesthub.db.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

public class RoomBehavior {
    private static final String TAG = "database-room";

    private static ChinaDatabase chinaDb;

    public static void behave(final Context context) {
        accessDb(context);

        CityDao cityDao = chinaDb.cityDao();
        Log.d(TAG, "before deleteAll: " + cityDao.getAllCity());
//        cityDao.deleteAllCity(cityDao.getAllCity().toArray(new City[0]));
//        Log.d(TAG, "after deleteAll: " + cityDao.getAllCity());
//
//        for (int i = 0; i < 30; i++) {
//
//            cityDao.insertCity(new City("1", "北京"));
//            cityDao.insertCity(new City("2", "上海"));
//            cityDao.insertCity(new City("3", "广州"));
//        }
//        Log.d(TAG, "after insert: " + cityDao.getAllCity());
//
//        City city = cityDao.getCity("3");
//        city.setCityName("深圳");
//        cityDao.updateCity(city);
//        Log.d(TAG, "after update: " + cityDao.getAllCity());
//
//        cityDao.deleteCity(city);
//        Log.d(TAG, "after delete: " + cityDao.getAllCity());
    }

    public static void accessDb(Context context) {
        chinaDb = Room.databaseBuilder(context, ChinaDatabase.class, "china.db")
            .addMigrations(new Migration(1, 2) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                    Log.d(TAG, "xxxxxxxxxxxxxxxxxxxxxxxxxx");
                }
            })
            .setJournalMode(RoomDatabase.JournalMode.AUTOMATIC)
            .build();
    }
}
