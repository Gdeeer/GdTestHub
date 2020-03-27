package com.gdeer.gdtesthub.db.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 2)
public abstract class ChinaDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
}
