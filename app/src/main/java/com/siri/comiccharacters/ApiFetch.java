package com.siri.comiccharacters;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ApiFetch {

    private static final String TAG = "Fetching Data";
//    private static final String API_KEY = "469d45fe8bb5973edf7b414b8133fe36";


    public byte[] getUrlBytes(String urlSpec) throws IOException {

        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);

            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {

                out.write(buffer, 0, bytesRead);

            }

            out.close();
            return out.toByteArray();

        } finally {

            connection.disconnect();

        }

    }

    public String getUrlString(String urlSpec) throws IOException {

        return new String(getUrlBytes(urlSpec));

    }



    @SuppressLint("LongLogTag")
    public List<Hero> fetchItems() {

        List<Hero> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://simplifiedcoding.net/demos/marvel/")
                    .buildUpon()

                    .build().toString();
            Log.i(TAG, "fetch url: " + url);

            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
                JSONArray jsonBody = new JSONArray(jsonString);
//            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (IOException ioe) {

            Log.e(TAG, "Failed to fetch items", ioe);

        } catch (JSONException je){

            Log.e(TAG, "Failed to parse JSON", je);

        }

        return items;
    }



    @SuppressLint("LongLogTag")
    private void parseItems(List<Hero> heroes, JSONArray jsonBody)


            throws IOException, JSONException {
            Log.i(TAG, "jason array: " + jsonBody);

        for (int i = 0; i < jsonBody.length(); i++) {
            JSONObject dataJsonObject = jsonBody.getJSONObject(i);

            Log.i(TAG, "Single Object Name: " + dataJsonObject);

            Hero h = new Hero();

            h.setName(dataJsonObject.getString("name"));
            h.setRealName(dataJsonObject.getString("realname"));
            if (!dataJsonObject.has("imageurl")) {
                continue; }
            h.setUrl(dataJsonObject.getString("imageurl"));

            heroes.add(h);

        }
    }
}
