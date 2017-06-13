package com.example.btanner.marveldatabase;

import android.net.ParseException;
import android.net.Uri;
import android.util.Log;

import org.apache.commons.codec.binary.Hex;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
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
    private static final String MARVEL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private static final String TAG = MainActivity.class.getSimpleName();


    private static final OkHttpClient mHTTPClient = new OkHttpClient();
    private static final DigestUtils digestUtils = new DigestUtils();


    private static String calculateHash () {
        String hash = new String(Hex.encodeHex(digestUtils.md5(Long.toString(System.currentTimeMillis()) + MARVEL_PRIVATE_API_KEY + MARVEL_PUBLIC_API_KEY)));
        return hash;
    }

    public static class MarvelItem implements Serializable {
        public static JSONObject next;
        public static JSONObject previous;
        public static Date start;
        public static Date end;
        public static String nextURI;
        public static String nextName;
        public static String previousURI;
        public static String previousName;
        public static String firstName;
        public static String middleName;
        public static String lastName;
        public static String suffix;
        public static String fullName;
        public static String type;
        public static String originalIssueUri;
        public static String originalIssueName;
        public static int startYear;
        public static int endYear;
        public static String rating;
        public static JSONObject comics;
        public static JSONObject series;
        public static String wikiURL;
        public static String comiclinkURL;
        public static int id;
        public static String name;
        public static int digitalId;
        public static String title;
        public static double issueNumber;
        public static String variantDescription;
        public static String description;
        public static Date modified;
        public static String isbn;
        public static String upc;
        public static String diamondCode;
        public static String ean;
        public static String issn;
        public static String format;
        public static int pageCount;
        public static JSONArray textObjects;
        public static String resourceURI;
        public static String detailURL;
        public static String seriesURI;
        public static String seriesName;
        public static JSONArray variants;
        public static JSONArray collections;
        public static JSONArray collectedIssues;
        public static Date onsaleDate;
        public static Date focDate;
        public static float printPrice;
        public static float digitalPurchasePrice;
        public static String thumbnailPath;
        public static JSONArray images;
        public static JSONObject creators;
        public static JSONObject characters;
        public static JSONObject stories;
        public static JSONObject events;
        public static String displayName;

    }

    public static class MarvelCharacterItem implements Serializable {
        public static int id;
        public static String name;
        public static String description;
        public static Date modified;
        public static String thumbnailPath;
        public static String resourceURI;
        public static JSONObject comics;
        public static JSONObject series;
        public static JSONObject stories;
        public static JSONObject events;
        public static String detailURL;
        public static String wikiURL;
        public static String comiclinkURL;
        public static String displayName;


    }
    public static class MarvelComicItem implements Serializable {
        public static int id;
        public static int digitalId;
        public static String title;
        public static double issueNumber;
        public static String variantDescription;
        public static String description;
        public static Date modified;
        public static String isbn;
        public static String upc;
        public static String diamondCode;
        public static String ean;
        public static String issn;
        public static String format;
        public static int pageCount;
        public static JSONArray textObjects;
        public static String resourceURI;
        public static String detailURL;
        public static String seriesURI;
        public static String seriesName;
        public static JSONArray variants;
        public static JSONArray collections;
        public static JSONArray collectedIssues;
        public static Date onsaleDate;
        public static Date focDate;
        public static float printPrice;
        public static float digitalPurchasePrice;
        public static String thumbnailPath;
        public static JSONArray images;
        public static JSONObject creators;
        public static JSONObject characters;
        public static JSONObject stories;
        public static JSONObject events;
        public static String displayName;
    }

    public static class MarvelSeriesItem implements Serializable {
        public static int id;
        public static String title;
        public static String description;
        public static String resourceURI;
        public static String detailURL;
        public static int startYear;
        public static int endYear;
        public static String rating;
        public static Date modified;
        public static String thumbnailPath;
        public static JSONObject comics;
        public static JSONObject stories;
        public static JSONObject events;
        public static JSONObject characters;
        public static JSONObject creators;
        public static JSONObject next;
        public static JSONObject previous;
        public static String displayName;
    }

    public static class MarvelStoryItem implements Serializable {
        public static int id;
        public static String title;
        public static String description;
        public static String resourceURI;
        public static String type;
        public static Date modified;
        public static String thumbnailPath;
        public static JSONObject comics;
        public static JSONObject series;
        public static JSONObject events;
        public static JSONObject characters;
        public static JSONObject creators;
        public static String originalIssueUri;
        public static String originalIssueName;
        public static String displayName;

    }

    public static class MarvelCreatorItem implements Serializable {
        public static int id;
        public static String firstName;
        public static String middleName;
        public static String lastName;
        public static String suffix;
        public static String fullName;
        public static Date modified;
        public static String resourceURI;
        public static String detailURL;
        public static String thumbnailPath;
        public static JSONObject series;
        public static JSONObject stories;
        public static JSONObject comics;
        public static JSONObject events;
        public static String displayName;
    }

    public static class MarvelEventItem implements Serializable {
        public static int id;
        public static String title;
        public static String description;
        public static String resourceURI;
        public static String detailURL;
        public static Date modified;
        public static Date start;
        public static Date end;
        public static String thumbnailPath;
        public static JSONObject comics;
        public static JSONObject stories;
        public static JSONObject series;
        public static JSONObject characters;
        public static JSONObject creators;
        public static String nextURI;
        public static String nextName;
        public static String previousURI;
        public static String previousName;
        public static String displayName;
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

    public static ArrayList<MarvelItem> parseMarvelItemJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            marvelObj = marvelObj.getJSONObject("data");
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelItem> marvelItemsList = new ArrayList<MarvelItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                MarvelItem marvelItem = new MarvelItem();   //problem here
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                if (marvelListItem.has("id")) {
                    marvelItem.id = marvelListItem.getInt("id");
                }
                if (marvelListItem.has("name")) {
                    marvelItem.name = marvelListItem.getString("name");
                }
                if (marvelListItem.has("firstName")) {
                    marvelItem.firstName = marvelListItem.getString("firstName");
                }
                if (marvelListItem.has("middleName")) {
                    marvelItem.middleName = marvelListItem.getString("middleName");
                }
                if (marvelListItem.has("lastName")) {
                    marvelItem.lastName = marvelListItem.getString("lastName");
                }
                if (marvelListItem.has("suffix")) {
                    marvelItem.suffix = marvelListItem.getString("suffix");
                }
                if (marvelListItem.has("fullName")) {
                    marvelItem.fullName = marvelListItem.getString("fullName");
                }
                if (marvelListItem.has("digitalId")) {
                    marvelItem.digitalId = marvelListItem.getInt("digitalId");
                }
                if (marvelListItem.has("title")) {
                    marvelItem.title = marvelListItem.getString("title");
                }
                if (marvelListItem.has("issueNumber")) {
                    marvelItem.issueNumber = marvelListItem.getDouble("issueNumber");
                }
                if (marvelListItem.has("variantDescription")) {
                    marvelItem.variantDescription = marvelListItem.getString("variantDescription");
                }
                if (marvelListItem.has("description")) {
                    marvelItem.description = marvelListItem.getString("description");
                }
                if (marvelListItem.has("modified")) {
                    marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                }
                if (marvelListItem.has("startYear")) {
                    marvelItem.startYear = marvelListItem.getInt("startYear");
                }
                if (marvelListItem.has("endYear")) {
                    marvelItem.endYear = marvelListItem.getInt("endYear");
                }
                if (marvelListItem.has("start")) {
                    //marvelItem.start = dateParser.parse(marvelListItem.getString("start"));
                }
                if (marvelListItem.has("end")) {
                    //marvelItem.end = dateParser.parse(marvelListItem.getString("end"));
                }
                if (marvelListItem.has("rating")) {
                    marvelItem.rating = marvelListItem.getString("rating");
                }
                if (marvelListItem.has("isbn")) {
                    marvelItem.isbn = marvelListItem.getString("isbn");
                }
                if (marvelListItem.has("upc")) {
                    marvelItem.upc = marvelListItem.getString("upc");
                }
                if (marvelListItem.has("diamondCode")) {
                    marvelItem.diamondCode = marvelListItem.getString("diamondCode");
                }
                if (marvelListItem.has("ean")) {
                    marvelItem.ean = marvelListItem.getString("ean");
                }
                if (marvelListItem.has("issn")) {
                    marvelItem.issn = marvelListItem.getString("issn");
                }
                if (marvelListItem.has("format")) {
                    marvelItem.format = marvelListItem.getString("format");
                }
                if (marvelListItem.has("pageCount")) {
                    marvelItem.pageCount = marvelListItem.getInt("pageCount");
                }
                if (marvelListItem.has("textObjects")) {
                    marvelItem.textObjects = marvelListItem.getJSONArray("textObjects");
                }
                if (marvelListItem.has("resourceURI")) {
                    marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                }
                if (marvelListItem.has("type")) {
                    marvelItem.type = marvelListItem.getString("type");
                }
                if (marvelListItem.has("variants")) {
                    marvelItem.variants = marvelListItem.getJSONArray("variants");
                }
                if (marvelListItem.has("collections")) {
                    marvelItem.collections = marvelListItem.getJSONArray("collections");
                }
                if (marvelListItem.has("collectedIssues")) {
                    marvelItem.collectedIssues = marvelListItem.getJSONArray("collectedIssues");
                }

                if (marvelListItem.has("urls")) {
                    tempJSONarr = marvelListItem.getJSONArray("urls");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (tempJSONobj.has("type")) {
                            if (tempJSONobj.getString("type").equals("detail")) {
                                marvelItem.detailURL = tempJSONobj.getString("url");
                            } else if (tempJSONobj.getString("type").equals("wiki")) {
                                marvelItem.wikiURL = tempJSONobj.getString("url");
                            } else if (tempJSONobj.getString("type").equals("comiclink")) {
                                marvelItem.comiclinkURL = tempJSONobj.getString("url");
                            } else {
                                Log.d(TAG, "Found unexpected type in urls when parsing comic JSON");
                            }
                        }
                    }
                }

                if (marvelListItem.has("dates")) {
                    tempJSONarr = marvelListItem.getJSONArray("dates");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (tempJSONobj.has("type")) {
                            if (tempJSONobj.getString("type").equals("onsaleDate")) {
                                marvelItem.onsaleDate = dateParser.parse(tempJSONobj.getString("date"));
                            } else if (tempJSONobj.getString("type").equals("focDate")) {
                                marvelItem.focDate = dateParser.parse(tempJSONobj.getString("date"));
                            } else {
                                Log.d(TAG, "Found unexpected type in dates when parsing comic JSON");
                            }
                        }
                    }
                }
                if (marvelListItem.has("prices")) {
                    tempJSONarr = marvelListItem.getJSONArray("prices");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (tempJSONobj.has("type")) {
                            if (tempJSONobj.getString("type").equals("printPrice")) {
                                marvelItem.printPrice = (float) tempJSONobj.getDouble("price");
                            } else if (tempJSONobj.getString("type").equals("digitalPurchasePrice")) {
                                marvelItem.digitalPurchasePrice = (float) tempJSONobj.getDouble("price");
                            } else {
                                Log.d(TAG, "Found unexpected type in prices when parsing comic JSON");
                            }
                        }
                    }
                }
                if (marvelListItem.has("originalIssue")) {
                    tempJSONobj = marvelListItem.getJSONObject("originalIssue");
                    if (tempJSONobj.has("resourceURI")) {
                        marvelItem.originalIssueUri = tempJSONobj.getString("resourceURI");
                    }
                    if (tempJSONobj.has("name")) {
                        marvelItem.originalIssueName = tempJSONobj.getString("name");
                    }
                }
                if (marvelListItem.has("series")) {
                    tempJSONobj = marvelListItem.getJSONObject("series");
                    if (tempJSONobj.has("resourceURI")) {
                        marvelItem.seriesURI = tempJSONobj.getString("resourceURI");
                    }
                    if (tempJSONobj.has("name")) {
                        marvelItem.seriesName = tempJSONobj.getString("name");
                    }
                }

//                if (marvelListItem.has("thumbnail")) {
//                    tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                    if (tempJSONobj.has("path") && tempJSONobj.has("extension")) {
//                        marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                    }
//                    if (tempJSONobj.has("images")) {
//                        marvelItem.images = marvelListItem.getJSONArray("images");
//                    }
//                }

//                if (marvelListItem.has("next")) {
//                    tempJSONobj = marvelListItem.getJSONObject("next");
//                    if (tempJSONobj.has("resourceURI")) {
//                        marvelItem.nextURI = tempJSONobj.getString("resourceURI");
//                    }
//                    if (tempJSONobj.has("name")) {
//                        marvelItem.nextName = tempJSONobj.getString("name");
//                    }
//                }
//                if (marvelListItem.has("previous")) {
//                    tempJSONobj = marvelListItem.getJSONObject("previous");
//                    if (tempJSONobj.has("resourceURI")) {
//                        marvelItem.previousURI = tempJSONobj.getString("resourceURI");
//                    }
//                    if (tempJSONobj.has("name")) {
//                        marvelItem.previousName = tempJSONobj.getString("name");
//                    }
//                }

                if (marvelListItem.has("characters")) {
                    marvelItem.characters = marvelListItem.getJSONObject("characters");
                }
                if (marvelListItem.has("comics")) {
                    marvelItem.comics = marvelListItem.getJSONObject("comics");
                }
                if (marvelListItem.has("series")) {
                    marvelItem.series = marvelListItem.getJSONObject("series");
                }
                if (marvelListItem.has("stories")) {
                    marvelItem.stories = marvelListItem.getJSONObject("stories");
                }
                if (marvelListItem.has("creators")) {
                    marvelItem.creators = marvelListItem.getJSONObject("creators");
                }
                if (marvelListItem.has("events")) {
                    marvelItem.events = marvelListItem.getJSONObject("events");
                }
//                if (marvelListItem.has("next")) {
//                    marvelItem.next = marvelListItem.getJSONObject("next");
//                }
//                if (marvelListItem.has("previous")) {
//                    marvelItem.previous = marvelListItem.getJSONObject("previous");
//                }
                if (marvelItem.fullName == null) {
                    if (marvelItem.name == null) {
                        if (marvelItem.title == null) {
                            marvelItem.displayName = "error";
                        }
                        else {
                            marvelItem.displayName = marvelItem.title;
                            //comic, series, story, event
                        }
                    }
                    else {
                        marvelItem.displayName = marvelItem.name;
                        //character
                    }
                }
                else {
                    marvelItem.displayName = marvelItem.fullName;
                    //creator
                }
                marvelItemsList.add(marvelItem);
            }
            //Log.d(TAG, "index 10 " + marvelItemsList.get(10).name + "\nindex 11 " + marvelItemsList.get(11).name);
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            Log.d(TAG, "Expection 1" + e);
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            Log.d(TAG, "Expection 2" + e);
            return null;
        }


    }

    public static ArrayList<MarvelCharacterItem> parseCharactersJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            marvelObj = marvelObj.getJSONObject("data");
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelCharacterItem> marvelItemsList = new ArrayList<MarvelCharacterItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelCharacterItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelCharacterItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.name = marvelListItem.getString("name");
                //marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                marvelItem.description = marvelListItem.getString("description");
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                marvelItem.comics = marvelListItem.getJSONObject("comics");
                marvelItem.series = marvelListItem.getJSONObject("series");
                marvelItem.stories = marvelListItem.getJSONObject("stories");
                marvelItem.events = marvelListItem.getJSONObject("events");
                tempJSONarr = marvelListItem.getJSONArray("urls");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("detail")) {
                        marvelItem.detailURL = tempJSONobj.getString("url");
                    }
                    else if (tempJSONobj.getString("type").equals("wiki")) {
                        marvelItem.wikiURL = tempJSONobj.getString("url");
                    }
                    else if (tempJSONobj.getString("type").equals("comiclink")) {
                        marvelItem.comiclinkURL = tempJSONobj.getString("url");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in urls when parsing character JSON");
                    }
                }
                marvelItem.displayName = marvelItem.name;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static ArrayList<MarvelComicItem> parseComicsJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelComicItem> marvelItemsList = new ArrayList<MarvelComicItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelComicItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelComicItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.digitalId = marvelListItem.getInt("digitalId");
                marvelItem.title = marvelListItem.getString("title");
                marvelItem.issueNumber = marvelListItem.getDouble("issueNumber");
                marvelItem.variantDescription = marvelListItem.getString("variantDescription");
                marvelItem.description = marvelListItem.getString("description");
                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                marvelItem.isbn = marvelListItem.getString("isbn");
                marvelItem.upc = marvelListItem.getString("upc");
                marvelItem.diamondCode = marvelListItem.getString("diamondCode");
                marvelItem.ean = marvelListItem.getString("ean");
                marvelItem.issn = marvelListItem.getString("issn");
                marvelItem.format = marvelListItem.getString("format");
                marvelItem.pageCount = marvelListItem.getInt("pageCount");
                marvelItem.textObjects = marvelListItem.getJSONArray("textObjects");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                tempJSONarr = marvelListItem.getJSONArray("urls");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("detail")) {
                        marvelItem.detailURL = tempJSONobj.getString("url");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in urls when parsing comic JSON");
                    }
                }
                tempJSONobj = marvelListItem.getJSONObject("series");
                marvelItem.seriesURI = tempJSONobj.getString("resourceURI");
                marvelItem.seriesName = tempJSONobj.getString("name");
                marvelItem.variants = marvelListItem.getJSONArray("variants");
                marvelItem.collections = marvelListItem.getJSONArray("collections");
                marvelItem.collectedIssues = marvelListItem.getJSONArray("collectedIssues");
                tempJSONarr = marvelListItem.getJSONArray("dates");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("onsaleDate")) {
                        marvelItem.onsaleDate = dateParser.parse(tempJSONobj.getString("date"));
                    }
                    else if (tempJSONobj.getString("type").equals("focDate")) {
                        marvelItem.focDate = dateParser.parse(tempJSONobj.getString("date"));
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in dates when parsing comic JSON");
                    }
                }
                tempJSONarr = marvelListItem.getJSONArray("prices");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("printPrice")) {
                        marvelItem.printPrice = (float) tempJSONobj.getDouble("price");
                    }
                    else if (tempJSONobj.getString("type").equals("digitalPurchasePrice")) {
                        marvelItem.digitalPurchasePrice = (float) tempJSONobj.getDouble("price");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in prices when parsing comic JSON");
                    }
                }
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                marvelItem.images = marvelListItem.getJSONArray("images");
                marvelItem.creators = marvelListItem.getJSONObject("creators");
                marvelItem.characters = marvelListItem.getJSONObject("characters");
                marvelItem.stories = marvelListItem.getJSONObject("stories");
                marvelItem.events = marvelListItem.getJSONObject("events");

                marvelItem.displayName = marvelItem.title;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static ArrayList<MarvelSeriesItem> parseSeriesJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelSeriesItem> marvelItemsList = new ArrayList<MarvelSeriesItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelSeriesItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelSeriesItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.title = marvelListItem.getString("title");
                marvelItem.description = marvelListItem.getString("description");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                tempJSONarr = marvelListItem.getJSONArray("urls");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("detail")) {
                        marvelItem.detailURL = tempJSONobj.getString("url");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in urls when parsing series JSON");
                    }
                }
                marvelItem.startYear = marvelListItem.getInt("startYear");
                marvelItem.endYear = marvelListItem.getInt("endYear");
                marvelItem.rating = marvelListItem.getString("rating");
                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                marvelItem.creators = marvelListItem.getJSONObject("creators");
                marvelItem.characters = marvelListItem.getJSONObject("characters");
                marvelItem.stories = marvelListItem.getJSONObject("stories");
                marvelItem.comics = marvelListItem.getJSONObject("comics");
                marvelItem.events = marvelListItem.getJSONObject("events");
                marvelItem.next = marvelListItem.getJSONObject("next");
                marvelItem.previous = marvelListItem.getJSONObject("previous");

                marvelItem.displayName = marvelItem.title;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static ArrayList<MarvelStoryItem> parseStoriesJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelStoryItem> marvelItemsList = new ArrayList<MarvelStoryItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelStoryItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelStoryItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.title = marvelListItem.getString("title");
                marvelItem.description = marvelListItem.getString("description");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                marvelItem.type = marvelListItem.getString("type");
                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                marvelItem.creators = marvelListItem.getJSONObject("creators");
                marvelItem.characters = marvelListItem.getJSONObject("characters");
                marvelItem.series = marvelListItem.getJSONObject("series");
                marvelItem.comics = marvelListItem.getJSONObject("comics");
                marvelItem.events = marvelListItem.getJSONObject("events");
                tempJSONobj = marvelListItem.getJSONObject("originalIssue");
                marvelItem.originalIssueUri = tempJSONobj.getString("resourceURI");
                marvelItem.originalIssueName = tempJSONobj.getString("name");
                marvelItem.displayName = marvelItem.title;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static ArrayList<MarvelCreatorItem> parseCreatorJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelCreatorItem> marvelItemsList = new ArrayList<MarvelCreatorItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelCreatorItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelCreatorItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.firstName = marvelListItem.getString("firstName");
                marvelItem.middleName = marvelListItem.getString("middleName");
                marvelItem.lastName = marvelListItem.getString("lastName");
                marvelItem.suffix = marvelListItem.getString("suffix");
                marvelItem.fullName = marvelListItem.getString("fullName");
                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                marvelItem.comics = marvelListItem.getJSONObject("comics");
                marvelItem.series = marvelListItem.getJSONObject("series");
                marvelItem.stories = marvelListItem.getJSONObject("stories");
                marvelItem.events = marvelListItem.getJSONObject("events");
                tempJSONarr = marvelListItem.getJSONArray("urls");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("detail")) {
                        marvelItem.detailURL = tempJSONobj.getString("url");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in urls when parsing creators JSON");
                    }
                }
                marvelItem.displayName = marvelItem.fullName;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }


    }
    public static ArrayList<MarvelEventItem> parseEventsJSON(String marvelJSON) {
        try {
            JSONObject marvelObj = new JSONObject(marvelJSON);
            JSONArray marvelList = marvelObj.getJSONArray("results");
            ArrayList<MarvelEventItem> marvelItemsList = new ArrayList<MarvelEventItem>();
            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT);
            MarvelEventItem marvelItem;
            JSONObject tempJSONobj;
            JSONArray tempJSONarr;

            for (int i = 0; i < marvelList.length(); ++i) {
                marvelItem = new MarvelEventItem();
                JSONObject marvelListItem = marvelList.getJSONObject(i);
                marvelItem.id = marvelListItem.getInt("id");
                marvelItem.title = marvelListItem.getString("title");
                marvelItem.description = marvelListItem.getString("description");
                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                tempJSONarr = marvelListItem.getJSONArray("urls");
                for (int j = 0; j < tempJSONarr.length(); ++j) {
                    tempJSONobj = tempJSONarr.getJSONObject(j);
                    if (tempJSONobj.getString("type").equals("detail")) {
                        marvelItem.detailURL = tempJSONobj.getString("url");
                    }
                    else {
                        Log.d(TAG, "Found unexpected type in urls when parsing events JSON");
                    }
                }
                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
                marvelItem.start = dateParser.parse(marvelListItem.getString("start"));
                marvelItem.end = dateParser.parse(marvelListItem.getString("end"));
                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");

                marvelItem.creators = marvelListItem.getJSONObject("creators");
                marvelItem.characters = marvelListItem.getJSONObject("characters");
                marvelItem.stories = marvelListItem.getJSONObject("stories");
                marvelItem.comics = marvelListItem.getJSONObject("comics");
                marvelItem.series = marvelListItem.getJSONObject("series");
                tempJSONobj = marvelListItem.getJSONObject("next");
                marvelItem.nextURI = tempJSONobj.getString("resourceURI");
                marvelItem.nextName = tempJSONobj.getString("name");
                tempJSONobj = marvelListItem.getJSONObject("previous");
                marvelItem.previousURI = tempJSONobj.getString("resourceURI");
                marvelItem.previousName = tempJSONobj.getString("name");
                marvelItem.displayName = marvelItem.title;
                marvelItemsList.add(marvelItem);
            }
            return marvelItemsList;
        }
        catch (java.text.ParseException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}