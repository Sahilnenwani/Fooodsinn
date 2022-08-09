package com.example.babycare;

import android.os.AsyncTask;
import android.util.JsonReader;
import android.util.Log;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class foodAPI  extends AsyncTask<Void, Void, Void> {
    @Override
    protected Void doInBackground(Void... voids) {




        // rapid api
        URL rapidAPI = null;
        try {
            rapidAPI = new URL("https://api.fda.gov/food/enforcement.json?limit=10");

            HttpsURLConnection myConnection =
                    (HttpsURLConnection) rapidAPI.openConnection();
            if (myConnection.getResponseCode() == 200) {
                InputStream responseBody = myConnection.getInputStream();
                InputStreamReader responseBodyReader =
                        new InputStreamReader(responseBody, "UTF-8");
                responseBodyReader.toString();
                JsonReader jsonReader = new JsonReader(responseBodyReader);
                Log.d("Inside Reposnse Neeha",jsonReader.toString());

            } else {
                // Error handling code goes here
            }

        } catch (MalformedURLException e) {


            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }




}
