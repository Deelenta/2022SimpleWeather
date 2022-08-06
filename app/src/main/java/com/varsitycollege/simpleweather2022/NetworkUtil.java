package com.varsitycollege.simpleweather2022;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {

    private static final String BASE_URL = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/30605";
    private static final String API_KEY = "CIvazOzGvEmsEirJ7VMHvO0Bgu7ESOtB";
    private static final String PARAM_API_KEY = "apikey";
    private static final String METRIC = "metric";
    private static final String METRIC_PARAM = "true";
    public static String TAG = "NETWORK_UTIL";

    public static URL buildURL(){
        Uri uri = Uri.parse(BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_API_KEY, API_KEY)
                .appendQueryParameter(METRIC_PARAM, METRIC)
                .build();

        URL url = null;

        try{
            url = new URL((uri).toString());

        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }

    public static String getResponse(URL url) throws IOException{
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

        try{
            InputStream in = httpURLConnection.getInputStream();
            Scanner scanner = new Scanner(in);

            scanner.useDelimiter("//4");
            boolean hasInput = scanner.hasNext();

            if(hasInput){
                return scanner.next();
            }else{
               Log.i(TAG, "getResponse: " + scanner.next());
               return null;
            }
        }
        finally {
            httpURLConnection.disconnect();
        }
    }

}
