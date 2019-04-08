package com.gdeer.gdtesthub.db.coverUpgradeTest;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

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
