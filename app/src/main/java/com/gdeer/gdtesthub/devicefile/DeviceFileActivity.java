package com.gdeer.gdtesthub.devicefile;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceFileActivity extends ListActivity {

    List<String> data = new ArrayList<String>() {
        {
            add("/sdcard");
            add("/mnt/sdcard");
            add("/storage/sdcard");
            add("/storage/emulated");
            add("/sdcard0");
            add("/mnt/sdcard0");
            add("/storage/sdcard0");
            add("/storage/emulated0");
            add("/sdcard/0");
            add("/mnt/sdcard/0");
            add("/storage/sdcard/0");
            add("/storage/emulated/0");
            add("/storage/emulated/legacy");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (int i = 0; i < data.size(); i++) {
            String s = data.get(i);
            File file = new File(s);
            boolean exist = file.exists();
            String path = "";
            if (exist) {
                try {
                    path = "\nAbsolutePath: " + file.getAbsolutePath() + "\nCanonicalPath: " + file.getCanonicalPath();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            data.set(i, s + "\nexist: " + exist + path);
        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, data);
        setListAdapter(arrayAdapter);
    }
}
