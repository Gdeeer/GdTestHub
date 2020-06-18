package com.gdeer.gdtesthub.db.coverupgradetest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsaCity.class}, version = 3, exportSchema = false)
public abstract class UsaDatabase extends RoomDatabase {
    public abstract UsaCityDao cityDao();
}
