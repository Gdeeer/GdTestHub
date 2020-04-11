package com.gdeer.gdtesthub.db.coverupgradetest;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsaCityDao {
    @Query("select * from usacity")
    List<UsaCity> getAllCity();

    @Insert
    void insertCity(UsaCity usaCity);
}
