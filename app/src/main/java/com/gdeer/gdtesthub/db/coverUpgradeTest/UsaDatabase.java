package com.gdeer.gdtesthub.db.coverUpgradeTest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {UsaCity.class}, version = 3)
public abstract class UsaDatabase extends RoomDatabase {
    public abstract UsaCityDao cityDao();
}