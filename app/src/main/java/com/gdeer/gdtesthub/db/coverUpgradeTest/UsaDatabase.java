package com.gdeer.gdtesthub.db.coverUpgradeTest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {UsaCity.class}, version = 3)
public abstract class UsaDatabase extends RoomDatabase {
    public abstract UsaCityDao cityDao();
}