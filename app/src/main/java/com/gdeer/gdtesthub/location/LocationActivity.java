package com.gdeer.gdtesthub.location;

import com.gdeer.gdtesthub.R;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class LocationActivity extends AppCompatActivity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mTextView = findViewById(R.id.tv_location);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        List<String> providerList = locationManager.getProviders(true);
        Log.d("zhangjl", providerList.toString());

        String provider = LocationManager.GPS_PROVIDER;
        Location location = null;
        location = locationManager.getLastKnownLocation(provider);

        mTextView.setText(location.toString());
    }
}
