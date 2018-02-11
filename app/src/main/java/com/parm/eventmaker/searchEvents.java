package com.parm.eventmaker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.location.places.Place;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by alman on 2018-02-11.
 */

public class searchEvents extends AppCompatActivity {

    ConnectivityManager connectivityManager;
    NetworkInfo networkInfo;
    private SharedPreferences prefs;
    private double lon;
    private double lat;
    GPSActivity gps;
    ArrayList<String> names;
    ArrayList<String> locations;


    String input;
    private static final String TAG = "searchEventsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchevents);
        gps = new GPSActivity(this);
        names = new ArrayList<>();
        locations = new ArrayList<>();

    }

    protected void onStart() {
        super.onStart();

        if(!gps.canGetLocation()) {
            gps.promptSettings();
        }

        gps.getLocation();
        if(gps.canGetLocation()) {
            lat = gps.getLat();
            lon = gps.getLon();
            Log.i("SEARCHEVENT","_____________________LAT"+ lat);
            Log.i("SEARCHEVENT","_____________________LLON"+ lon);


        }
    }


    private boolean isConnected() {

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Log.d(TAG, "Connection to Network");
            return true;
        }

        return false;
    }



    public void displayCat(View view) {

        EditText ed = findViewById(R.id.category);
        input = ed.getText().toString();

            String searchurl = "https://maps.googleapis.com/maps/api/place/textsearch/json?query="+input+"&location="+ lat + "," + lon +"&radius=10000&key=AIzaSyBEDE6uYrnLceEJbvViKvSXr7L-Jj1nKtg";

            if (isConnected()) {
                new SearchAsyncTask().execute(searchurl);
            }
        }

    public class SearchAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... args) {
            InputStream inputStream = null;
            HttpURLConnection connection = null;
            int response = 0;
            String content = "";

            try {
                URL url = new URL(args[0]);

                Log.d(TAG, url.toString());

                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                response = connection.getResponseCode();
                if (response != HttpURLConnection.HTTP_OK) {
                    return "could not connect to server. Server response: " + response;
                }

                inputStream = connection.getInputStream();
                content = readInputStream(inputStream);

            } catch (java.io.IOException e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null) {
                    connection.disconnect();
                }
            }
            return content;
        }

        @Override
        public void onPostExecute(String result) {
            super.onPostExecute(result);


            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray restaurantArray = jsonObject.getJSONArray("results");


                for(int i = 0; i<restaurantArray.length(); i++) {
                    JSONObject js = restaurantArray.optJSONObject(i);
                    names.add(js.opt("name").toString());
                    locations.add(js.opt("formatted_address").toString());

                }

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("nameList", names);
                intent.putExtra("locationList", locations);
                startActivity(intent);


            } catch (JSONException e) {
                   e.printStackTrace();
            }
        }

    }
        private String getDate(JSONObject js) {

            String newTime = "";

            int hour = 0;

            String date  = js.optString("dt_txt").substring(0, js.optString("dt_txt").indexOf(' '));

            SimpleDateFormat localDateFormat = new SimpleDateFormat("HH");
            int currentTime = Integer.parseInt(localDateFormat.format(new Date()));

            if(currentTime == 0 || currentTime == 1 || currentTime == 2){
                hour = 3;
            } else if(currentTime == 3 || currentTime == 4 || currentTime == 6){
                hour = 6;
            } else if(currentTime == 6 || currentTime == 7 || currentTime == 8){
                hour = 9;
            } else if(currentTime == 9 || currentTime == 10 || currentTime == 11){
                hour = 12;
            } else if(currentTime == 12 || currentTime == 13 || currentTime == 14){
                hour = 15;
            } else if(currentTime == 15 || currentTime == 16 || currentTime == 17){
                hour = 18;
            } else if(currentTime == 18 || currentTime == 19 || currentTime == 20){
                hour = 21;
            } else if(currentTime == 21 || currentTime == 22 || currentTime == 23){
                hour = 0;
            }

            if(hour < 10) {
                newTime = date + " 0" + hour +":00:00";
            }else{
                newTime = date + " " + hour +":00:00";
            }

            return newTime;
        }



    private String readInputStream(InputStream is) {

        InputStreamReader inputStreamReader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();
        String line = "";
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

}
