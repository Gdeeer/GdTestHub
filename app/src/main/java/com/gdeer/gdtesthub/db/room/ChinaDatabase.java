package com.gdeer.gdtesthub.db.room;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {City.class}, version = 2)
public abstract class ChinaDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
