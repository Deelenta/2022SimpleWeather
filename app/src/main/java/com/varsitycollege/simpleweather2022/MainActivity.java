package com.varsitycollege.simpleweather2022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "AccuWeatherURL";
    Fragment fiveDayWeather;
    Fragment tide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().hide();

        fiveDayWeather = new FiveDayWeather();
        tide = new TideFragment();

        URL accuWeatherURL = NetworkUtil.buildURL();
        Log.i(TAG, "onCreate: " + accuWeatherURL);


        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.weather_frame, fiveDayWeather);
        transaction.replace(R.id.tide_frame, tide);
        transaction.commit();

    }
}