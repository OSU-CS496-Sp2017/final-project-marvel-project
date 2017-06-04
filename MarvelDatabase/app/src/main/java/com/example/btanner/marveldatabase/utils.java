package com.example.btanner.marveldatabase;

import android.net.ParseException;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;



/**
 * Created by btanner on 6/3/2017.
 */

public class utils {
    private static final String MARVEL_PRIVATE_API_KEY = "52e53b9e89995ea3ae812c0f406bfc081b7fc467";
    private static final String MARVEL_PUBLIC_API_KEY = "a6c7351eb420be9ea7fdf9966d59e4ce";

    private static final String MARVEL_BASE_URL = "https://gateway.marvel.com:443/v1/public/";
    private static final String MARVEL_APIKEY_QUERY_PARAM = "apikey";
    private static final String MARVEL_TS_QUERY_PARAM = "ts";
    private static final String MARVEL_HASH_QUERY_PARAM = "hash";


    private static final OkHttpClient mHTTPClient = new OkHttpClient();
    private static final DigestUtils digestUtils = new DigestUtils();


    private static String calculateHash () {
        return digestUtils.md5Hex(Long.toString(System.currentTimeMillis()) + MARVEL_PRIVATE_API_KEY + MARVEL_PUBLIC_API_KEY);
    }

    public static class MarvelItem implements Serializable {
        public static int id;
        public static String name;
        public static String description;
        public static String modified;
        public static String thumbnailPath;
        public static String resourceURI;
        public static JSONArray characters;
        public static JSONArray comics;
        public static JSONArray series;
        public static JSONArray stories;
        public static JSONArray events;
        public static JSONArray creators;

    }

    public static String doHTTPGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = mHTTPClient.newCall(request).execute();

        try {
            return response.body().string();
        }
        finally {
            response.close();
        }
    }



    public static String buildMarvelURL(String category) {
        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category)
                .appendQueryParameter(MARVEL_TS_QUERY_PARAM, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(MARVEL_APIKEY_QUERY_PARAM, MARVEL_PUBLIC_API_KEY)
                .appendQueryParameter(MARVEL_HASH_QUERY_PARAM, calculateHash())
                .build()
                .toString();
    }
    public static String buildMarvelURL(String category, String id) {
        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category)
                .appendPath(id)
                .appendQueryParameter(MARVEL_TS_QUERY_PARAM, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(MARVEL_APIKEY_QUERY_PARAM, MARVEL_PUBLIC_API_KEY)
                .appendQueryParameter(MARVEL_HASH_QUERY_PARAM, calculateHash())
                .build()
                .toString();
    }
    public static String buildMarvelURL(String category1, String id, String category2) {
        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category1)
                .appendPath(id)
                .appendPath(category2)
                .appendQueryParameter(MARVEL_TS_QUERY_PARAM, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(MARVEL_APIKEY_QUERY_PARAM, MARVEL_PUBLIC_API_KEY)
                .appendQueryParameter(MARVEL_HASH_QUERY_PARAM, calculateHash())
                .build()
                .toString();
    }

    public static ArrayList<MarvelItem> parseMarvelJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelItem> marvelItemsList = new ArrayList<MarvelItem>();

            for (int i = 0; i < marvelList.length(); ++i) {
                MarvelItem marvelItem = new MarvelItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        catch (ParseException e) {
            e.printStackTrace();
            return null;
        }

    }



}
