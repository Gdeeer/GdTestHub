package com.gdeer.gdtesthub.db.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Town {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
}
