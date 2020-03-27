package com.gdeer.gdtesthub.db;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.db.coverUpgradeTest.CoverUpgradeActivity;
import com.gdeer.gdtesthub.db.room.RoomBehavior;
import com.gdeer.gdtesthub.db.sqlite.SqliteBehavior;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;

public class DbActivity extends AppCompatActivity {

    private SQLiteDatabase chinaDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);

//        SqliteBehavior.behave(this);
//        SqliteBehavior.initTest(this);
    }

    public void onClickCreateSQLiteChinadb(View view) {
        SqliteBehavior.initTest(this);
    }

    public void onClickCreateRoomChinadb(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                RoomBehavior.behave(DbActivity.this);
            }
        }).start();
    }

    public void onClickCoverUpgrade(View view) {
        Intent intent = new Intent(this, CoverUpgradeActivity.class);
        startActivity(intent);
    }
}
