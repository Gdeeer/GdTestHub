package com.gdeer.gdtesthub.db.coverUpgradeTest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface UsaCityDao {
    @Query("select * from usacity")
    List<UsaCity> getAllCity();

    @Insert
    void insertCity(UsaCity usaCity);
}
