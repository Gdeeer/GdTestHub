package com.gdeer.gdtesthub.db.coverUpgradeTest;

import com.gdeer.gdtesthub.R;
import com.gdeer.gdtesthub.utils.FileUtil;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CoverUpgradeActivity extends AppCompatActivity {


    private static final String ASSET_DB_PATH = "usa.db";
    private static final String DATABASE_FILENAME = "usa.db";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover_upgrade);

        installDbFromAssets();
        UsaDatabase usaDatabase = Room.databaseBuilder(this, UsaDatabase.class, "usa.db")
            .addCallback(new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                }
            })
            .allowMainThreadQueries()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE)
            .addMigrations(new Migration(1, 3) {
                @Override
                public void migrate(@NonNull SupportSQLiteDatabase database) {
                }
            })
            .build();
        Log.d("zhangjl", "" + usaDatabase.getOpenHelper().getWritableDatabase().getVersion());
        Log.d("zhangjl", "" + usaDatabase.cityDao().getAllCity());
    }


    /**
     * 从asset目录下复制安装城市数据库
     */
    public void installDbFromAssets() {
        final Context context = this;
        copyCityDbFileFromAssets(true);
    }

    /**
     * 从assets目录下复制城市数据库文件.
     * 测试复制时间在 7ms左右.
     *
     * @param cover 是否覆盖重复文件
     */
    private void copyCityDbFileFromAssets(boolean cover) {
        final Context context = this;
        final File dbFile = context.getDatabasePath(DATABASE_FILENAME);
        FileUtil.copyFileFromAssets(context, ASSET_DB_PATH, dbFile, cover);
    }
}
