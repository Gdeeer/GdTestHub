package com.gdeer.gdtesthub.db.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Town {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
}
