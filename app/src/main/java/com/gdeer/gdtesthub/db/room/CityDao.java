package com.gdeer.gdtesthub.db.room;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    void insertCity(City city);

    @Delete
    void deleteCity(City city);

    @Delete
    void deleteAllCity(City...city);

    @Update
    void updateCity(City city);

    @Query("select * from city")
    List<City> getAllCity();

    @Query("select * from city where city_id = (:cityId)")
    City getCity(String cityId);
}
