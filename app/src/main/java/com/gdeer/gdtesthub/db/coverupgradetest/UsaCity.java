package com.gdeer.gdtesthub.db.coverupgradetest;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UsaCity {
    @PrimaryKey(autoGenerate = true)
    Integer id;
    String name;

    public UsaCity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UsaCity{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
