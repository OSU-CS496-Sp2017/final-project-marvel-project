package com.example.btanner.marveldatabase;

import android.content.SharedPreferences;
import android.net.ParseException;
import android.net.Uri;
import android.support.v7.preference.PreferenceManager;
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
    private static final String MARVEL_PRIVATE_API_KEY = "49fad242a08c0baba5c9504675cb884ca1c04582";
    private static final String MARVEL_PUBLIC_API_KEY = "ef1255ac8bfd65d1e1efe9d3086e05a0";

    private static final String MARVEL_BASE_URL = "https://gateway.marvel.com:443/v1/public/";
    private static final String MARVEL_APIKEY_QUERY_PARAM = "apikey";
    private static final String MARVEL_TS_QUERY_PARAM = "ts";
    private static final String MARVEL_HASH_QUERY_PARAM = "hash";
    private static final String MARVEL_DATE_FORMAT1 = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static final String MARVEL_DATE_FORMAT2 = "yyyy-MM-dd HH:mm:ss";
    private static final String MARVEL_LIMIT_QUERY_PARAM = "limit";
    private static final String MARVEL_OFFSET_QUERY_PARAM = "offset";
    private static final String MARVEL_ORDER_QUERY_PARAM = "orderBy";



    private static final String TAG = MainActivity.class.getSimpleName();


    private static final OkHttpClient mHTTPClient = new OkHttpClient();
    private static final DigestUtils digestUtils = new DigestUtils();


    private static String calculateHash () {
        String hash = new String(Hex.encodeHex(digestUtils.md5(Long.toString(System.currentTimeMillis()) + MARVEL_PRIVATE_API_KEY + MARVEL_PUBLIC_API_KEY)));
        return hash;
    }

    public static class MarvelItem implements Serializable {
        public static final String EXTRA_MARVEL_ID = "marvel.id";
        public static final String EXTRA_MARVEL_CATEGORY = "marvel.category";
        public JSONObject next;
        public JSONObject previous;
        public Date start;
        public Date end;
        public String nextURI;
        public String nextName;
        public String previousURI;
        public String previousName;
        public String firstName;
        public String middleName;
        public String lastName;
        public String suffix;
        public String fullName;
        public String type;
        public String originalIssueUri;
        public String originalIssueName;
        public int startYear;
        public int endYear;
        public String rating;
        public JSONObject comics;
        public JSONObject series;
        public String wikiURL = "";
        public String comiclinkURL = "";
        public int id;
        public String name;
        public int digitalId;
        public String title;
        public double issueNumber;
        public String variantDescription;
        public String description = "";
        public Date modified;
        public String isbn;
        public String upc;
        public String diamondCode;
        public String ean;
        public String issn;
        public String format;
        public int pageCount;
        public JSONArray textObjects;
        public String resourceURI;
        public String detailURL = "";
        public String seriesURI;
        public String seriesName;
        public JSONArray variants;
        public JSONArray collections;
        public JSONArray collectedIssues;
        public Date onsaleDate;
        public Date focDate;
        public float printPrice;
        public float digitalPurchasePrice;
        public String thumbnailPath;
        public JSONArray images;
        public JSONObject creators;
        public JSONObject characters;
        public JSONObject stories;
        public JSONObject events;
//        public String displayName;
    }

//    public static class MarvelCharacterItem implements Serializable {
//        public static int id;
//        public static String name;
//        public static String description;
//        public static Date modified;
//        public static String thumbnailPath;
//        public static String resourceURI;
//        public static JSONObject comics;
//        public static JSONObject series;
//        public static JSONObject stories;
//        public static JSONObject events;
//        public static String detailURL;
//        public static String wikiURL;
//        public static String comiclinkURL;
//        public static String displayName;
//
//
//    }
//    public static class MarvelComicItem implements Serializable {
//        public static int id;
//        public static int digitalId;
//        public static String title;
//        public static double issueNumber;
//        public static String variantDescription;
//        public static String description;
//        public static Date modified;
//        public static String isbn;
//        public static String upc;
//        public static String diamondCode;
//        public static String ean;
//        public static String issn;
//        public static String format;
//        public static int pageCount;
//        public static JSONArray textObjects;
//        public static String resourceURI;
//        public static String detailURL;
//        public static String seriesURI;
//        public static String seriesName;
//        public static JSONArray variants;
//        public static JSONArray collections;
//        public static JSONArray collectedIssues;
//        public static Date onsaleDate;
//        public static Date focDate;
//        public static float printPrice;
//        public static float digitalPurchasePrice;
//        public static String thumbnailPath;
//        public static JSONArray images;
//        public static JSONObject creators;
//        public static JSONObject characters;
//        public static JSONObject stories;
//        public static JSONObject events;
//        public static String displayName;
//    }
//
//    public static class MarvelSeriesItem implements Serializable {
//        public static int id;
//        public static String title;
//        public static String description;
//        public static String resourceURI;
//        public static String detailURL;
//        public static int startYear;
//        public static int endYear;
//        public static String rating;
//        public static Date modified;
//        public static String thumbnailPath;
//        public static JSONObject comics;
//        public static JSONObject stories;
//        public static JSONObject events;
//        public static JSONObject characters;
//        public static JSONObject creators;
//        public static JSONObject next;
//        public static JSONObject previous;
//        public static String displayName;
//    }
//
//    public static class MarvelStoryItem implements Serializable {
//        public static int id;
//        public static String title;
//        public static String description;
//        public static String resourceURI;
//        public static String type;
//        public static Date modified;
//        public static String thumbnailPath;
//        public static JSONObject comics;
//        public static JSONObject series;
//        public static JSONObject events;
//        public static JSONObject characters;
//        public static JSONObject creators;
//        public static String originalIssueUri;
//        public static String originalIssueName;
//        public static String displayName;
//
//    }
//
//    public static class MarvelCreatorItem implements Serializable {
//        public static int id;
//        public static String firstName;
//        public static String middleName;
//        public static String lastName;
//        public static String suffix;
//        public static String fullName;
//        public static Date modified;
//        public static String resourceURI;
//        public static String detailURL;
//        public static String thumbnailPath;
//        public static JSONObject series;
//        public static JSONObject stories;
//        public static JSONObject comics;
//        public static JSONObject events;
//        public static String displayName;
//    }
//
//    public static class MarvelEventItem implements Serializable {
//        public static int id;
//        public static String title;
//        public static String description;
//        public static String resourceURI;
//        public static String detailURL;
//        public static Date modified;
//        public static Date start;
//        public static Date end;
//        public static String thumbnailPath;
//        public static JSONObject comics;
//        public static JSONObject stories;
//        public static JSONObject series;
//        public static JSONObject characters;
//        public static JSONObject creators;
//        public static String nextURI;
//        public static String nextName;
//        public static String previousURI;
//        public static String previousName;
//        public static String displayName;
//    }



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



    public static String buildMarvelURL(String offset, String limit, String order, String category) {
        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category)
                .appendQueryParameter(MARVEL_ORDER_QUERY_PARAM, order)
                .appendQueryParameter(MARVEL_LIMIT_QUERY_PARAM, limit)
                .appendQueryParameter(MARVEL_OFFSET_QUERY_PARAM, offset)
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
    public static String buildMarvelURL(String offset, String limit, String order, String category1, String id, String category2) {
        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category1)
                .appendPath(id)
                .appendPath(category2)
                .appendQueryParameter(MARVEL_ORDER_QUERY_PARAM, order)
                .appendQueryParameter(MARVEL_LIMIT_QUERY_PARAM, limit)
                .appendQueryParameter(MARVEL_OFFSET_QUERY_PARAM, offset)
                .appendQueryParameter(MARVEL_TS_QUERY_PARAM, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(MARVEL_APIKEY_QUERY_PARAM, MARVEL_PUBLIC_API_KEY)
                .appendQueryParameter(MARVEL_HASH_QUERY_PARAM, calculateHash())
                .build()
                .toString();
    }

    public static String buildFilteredMarvelURL(String offset, String limit, String order, String filter, String category) {
        String filterQueryParam;
        if ((category.equals("characters")) || (category.equals("creators")) || (category.equals("events"))){
            filterQueryParam = "nameStartsWith";
        } else if (category.equals("comics")|| (category.equals("series"))) {
            filterQueryParam = "titleStartsWith";
        } else {
            Log.d(TAG, "Unexpected category at buildFilteredMarvelURL");
            filterQueryParam = "nameStartsWith";
        }

        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category)
                .appendQueryParameter(filterQueryParam, filter)
                .appendQueryParameter(MARVEL_ORDER_QUERY_PARAM, order)
                .appendQueryParameter(MARVEL_LIMIT_QUERY_PARAM, limit)
                .appendQueryParameter(MARVEL_OFFSET_QUERY_PARAM, offset)
                .appendQueryParameter(MARVEL_TS_QUERY_PARAM, Long.toString(System.currentTimeMillis()))
                .appendQueryParameter(MARVEL_APIKEY_QUERY_PARAM, MARVEL_PUBLIC_API_KEY)
                .appendQueryParameter(MARVEL_HASH_QUERY_PARAM, calculateHash())
                .build()
                .toString();
    }

    public static String buildFilteredMarvelURL(String offset, String limit, String order, String filter, String category1, String id, String category2) {
        String filterQueryParam;
        if ((category2.equals("characters")) || (category2.equals("creators")) || (category2.equals("events"))){
            filterQueryParam = "nameStartsWith";
        } else if (category2.equals("comics")|| (category2.equals("series"))) {
            filterQueryParam = "titleStartsWith";
        } else {
            Log.d(TAG, "Unexpected category at buildFilteredMarvelURL");
            filterQueryParam = "nameStartsWith";
        }

        return Uri.parse(MARVEL_BASE_URL).buildUpon()
                .appendPath(category1)
                .appendPath(id)
                .appendPath(category2)
                .appendQueryParameter(filterQueryParam, filter)
                .appendQueryParameter(MARVEL_ORDER_QUERY_PARAM, order)
                .appendQueryParameter(MARVEL_LIMIT_QUERY_PARAM, limit)
                .appendQueryParameter(MARVEL_OFFSET_QUERY_PARAM, offset)
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
            SimpleDateFormat dateParser1 = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
            SimpleDateFormat dateParser2 = new SimpleDateFormat(MARVEL_DATE_FORMAT2);


            JSONObject tempJSONobj;
            JSONArray tempJSONarr;
            MarvelItem marvelItem;

            for (int i = 0; i < marvelList.length(); ++i) {

                marvelItem = new MarvelItem();

                JSONObject marvelListItem = marvelList.getJSONObject(i);
                if (!marvelListItem.isNull("id")) {
                    marvelItem.id = marvelListItem.getInt("id");
                }
                if (!marvelListItem.isNull("name")) {
                    marvelItem.name = marvelListItem.getString("name");
                }
                if (!marvelListItem.isNull("firstName")) {
                    marvelItem.firstName = marvelListItem.getString("firstName");
                }
                if (!marvelListItem.isNull("middleName")) {
                    marvelItem.middleName = marvelListItem.getString("middleName");
                }
                if (!marvelListItem.isNull("lastName")) {
                    marvelItem.lastName = marvelListItem.getString("lastName");
                }
                if (!marvelListItem.isNull("suffix")) {
                    marvelItem.suffix = marvelListItem.getString("suffix");
                }
                if (!marvelListItem.isNull("fullName")) {
                    marvelItem.fullName = marvelListItem.getString("fullName");
                }
                if (!marvelListItem.isNull("digitalId")) {
                    marvelItem.digitalId = marvelListItem.getInt("digitalId");
                }
                if (!marvelListItem.isNull("title")) {
                    marvelItem.title = marvelListItem.getString("title");
                }
                if (!marvelListItem.isNull("issueNumber")) {
                    marvelItem.issueNumber = marvelListItem.getDouble("issueNumber");
                }
                if (!marvelListItem.isNull("variantDescription")) {
                    marvelItem.variantDescription = marvelListItem.getString("variantDescription");
                }
                if (!marvelListItem.isNull("description")) {
                    marvelItem.description = marvelListItem.getString("description");
                }
                if (!marvelListItem.isNull("modified")) {
                    marvelItem.modified = dateParser1.parse(marvelListItem.getString("modified"));
                }
                if (!marvelListItem.isNull("startYear")) {
                    marvelItem.startYear = marvelListItem.getInt("startYear");
                }
                if (!marvelListItem.isNull("endYear")) {
                    marvelItem.endYear = marvelListItem.getInt("endYear");
                }
                if (!marvelListItem.isNull("start")) {
                    marvelItem.start = dateParser2.parse(marvelListItem.getString("start"));
                }
                if (!marvelListItem.isNull("end")) {
                    marvelItem.end = dateParser2.parse(marvelListItem.getString("end"));
                }
                if (!marvelListItem.isNull("rating")) {
                    marvelItem.rating = marvelListItem.getString("rating");
                }
                if (!marvelListItem.isNull("isbn")) {
                    marvelItem.isbn = marvelListItem.getString("isbn");
                }
                if (!marvelListItem.isNull("upc")) {
                    marvelItem.upc = marvelListItem.getString("upc");
                }
                if (!marvelListItem.isNull("diamondCode")) {
                    marvelItem.diamondCode = marvelListItem.getString("diamondCode");
                }
                if (!marvelListItem.isNull("ean")) {
                    marvelItem.ean = marvelListItem.getString("ean");
                }
                if (!marvelListItem.isNull("issn")) {
                    marvelItem.issn = marvelListItem.getString("issn");
                }
                if (!marvelListItem.isNull("format")) {
                    marvelItem.format = marvelListItem.getString("format");
                }
                if (!marvelListItem.isNull("pageCount")) {
                    marvelItem.pageCount = marvelListItem.getInt("pageCount");
                }
                if (!marvelListItem.isNull("textObjects")) {
                    marvelItem.textObjects = marvelListItem.getJSONArray("textObjects");
                }
                if (!marvelListItem.isNull("resourceURI")) {
                    marvelItem.resourceURI = marvelListItem.getString("resourceURI");
                }
                if (!marvelListItem.isNull("type")) {
                    marvelItem.type = marvelListItem.getString("type");
                }
                if (!marvelListItem.isNull("variants")) {
                    marvelItem.variants = marvelListItem.getJSONArray("variants");
                }
                if (!marvelListItem.isNull("collections")) {
                    marvelItem.collections = marvelListItem.getJSONArray("collections");
                }
                if (!marvelListItem.isNull("collectedIssues")) {
                    marvelItem.collectedIssues = marvelListItem.getJSONArray("collectedIssues");
                }

                if (!marvelListItem.isNull("urls")) {
                    tempJSONarr = marvelListItem.getJSONArray("urls");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (!tempJSONobj.isNull("type")) {
                            if (tempJSONobj.getString("type").equals("detail")) {
                                marvelItem.detailURL = tempJSONobj.getString("url");
                            } else if (tempJSONobj.getString("type").equals("wiki")) {
                                marvelItem.wikiURL = tempJSONobj.getString("url");
                            } else if (tempJSONobj.getString("type").equals("comiclink")) {
                                marvelItem.comiclinkURL = tempJSONobj.getString("url");
                            } else {
                                Log.d(TAG, "Found unexpected type in urls when parsing comic JSON: " + tempJSONobj.getString("type"));
                            }
                        }
                    }
                }

                if (!marvelListItem.isNull("dates")) {
                    tempJSONarr = marvelListItem.getJSONArray("dates");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (!tempJSONobj.isNull("type")) {
                            if (tempJSONobj.getString("type").equals("onsaleDate")) {
                                marvelItem.onsaleDate = dateParser1.parse(tempJSONobj.getString("date"));
                            } else if (tempJSONobj.getString("type").equals("focDate")) {
                                marvelItem.focDate = dateParser1.parse(tempJSONobj.getString("date"));
                            } else {
                                Log.d(TAG, "Found unexpected type in dates when parsing comic JSON: " + tempJSONobj.getString("type"));
                            }
                        }
                    }
                }
                if (!marvelListItem.isNull("prices")) {
                    tempJSONarr = marvelListItem.getJSONArray("prices");
                    for (int j = 0; j < tempJSONarr.length(); ++j) {
                        tempJSONobj = tempJSONarr.getJSONObject(j);
                        if (!tempJSONobj.isNull("type")) {
                            if (tempJSONobj.getString("type").equals("printPrice")) {
                                marvelItem.printPrice = (float) tempJSONobj.getDouble("price");
                            } else if (tempJSONobj.getString("type").equals("digitalPurchasePrice")) {
                                marvelItem.digitalPurchasePrice = (float) tempJSONobj.getDouble("price");
                            } else {
                                Log.d(TAG, "Found unexpected type in prices when parsing comic JSON: " + tempJSONobj.getString("type"));
                            }
                        }
                    }
                }
                if (!marvelListItem.isNull("originalIssue")) {
                    tempJSONobj = marvelListItem.getJSONObject("originalIssue");
                    if (!tempJSONobj.isNull("resourceURI")) {
                        marvelItem.originalIssueUri = tempJSONobj.getString("resourceURI");
                    }
                    if (!tempJSONobj.isNull("name")) {
                        marvelItem.originalIssueName = tempJSONobj.getString("name");
                    }
                }
                if (!marvelListItem.isNull("series")) {
                    tempJSONobj = marvelListItem.getJSONObject("series");
                    if (!tempJSONobj.isNull("resourceURI")) {
                        marvelItem.seriesURI = tempJSONobj.getString("resourceURI");
                    }
                    if (!tempJSONobj.isNull("name")) {
                        marvelItem.seriesName = tempJSONobj.getString("name");
                    }
                }
                if (!marvelListItem.isNull("thumbnail")) {
                    tempJSONobj = marvelListItem.getJSONObject("thumbnail");
                    if (!tempJSONobj.isNull("path") && !tempJSONobj.isNull("extension")) {
                        marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
                    }
                    if (!tempJSONobj.isNull("images")) {
                        marvelItem.images = marvelListItem.getJSONArray("images");
                    }
                }

                if (!marvelListItem.isNull("next")) {
                    tempJSONobj = marvelListItem.getJSONObject("next");
                    if (!tempJSONobj.isNull("resourceURI")) {
                        marvelItem.nextURI = tempJSONobj.getString("resourceURI");
                    }
                    if (!tempJSONobj.isNull("name")) {
                        marvelItem.nextName = tempJSONobj.getString("name");
                    }
                }
                if (!marvelListItem.isNull("previous")) {
                    tempJSONobj = marvelListItem.getJSONObject("previous");
                    if (!tempJSONobj.isNull("resourceURI")) {
                        marvelItem.previousURI = tempJSONobj.getString("resourceURI");
                    }
                    if (!tempJSONobj.isNull("name")) {
                        marvelItem.previousName = tempJSONobj.getString("name");
                    }
                }

                if (!marvelListItem.isNull("characters")) {
                    marvelItem.characters = marvelListItem.getJSONObject("characters");
                }
                if (!marvelListItem.isNull("comics")) {
                    marvelItem.comics = marvelListItem.getJSONObject("comics");
                }
                if (!marvelListItem.isNull("series")) {
                    marvelItem.series = marvelListItem.getJSONObject("series");
                }
                if (!marvelListItem.isNull("stories")) {
                    marvelItem.stories = marvelListItem.getJSONObject("stories");
                }
                if (!marvelListItem.isNull("creators")) {
                    marvelItem.creators = marvelListItem.getJSONObject("creators");
                }
                if (!marvelListItem.isNull("events")) {
                    marvelItem.events = marvelListItem.getJSONObject("events");
                }
                if (!marvelListItem.isNull("next")) {
                    marvelItem.next = marvelListItem.getJSONObject("next");
                }
                if (!marvelListItem.isNull("previous")) {
                    marvelItem.previous = marvelListItem.getJSONObject("previous");
                }

//              if (marvelItem.fullName == null) {
//                    if (marvelItem.name == null) {
//                        if (marvelItem.title == null) {
//                            marvelItem.displayName = "error";
//                        }
//                        else {
//                            marvelItem.displayName = marvelItem.title;
//                            //comic, series, story, event
//                        }
//                    }
//                    else {
//                        marvelItem.displayName = marvelItem.name;
//                        //character
//                    }
//                }
//                else {
//                    marvelItem.displayName = marvelItem.fullName;
//                    //creator
//                }
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

//    public static ArrayList<MarvelCharacterItem> parseCharactersJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            marvelObj = marvelObj.getJSONObject("data");
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelCharacterItem> marvelItemsList = new ArrayList<MarvelCharacterItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelCharacterItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelCharacterItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.name = marvelListItem.getString("name");
//                //marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                marvelItem.description = marvelListItem.getString("description");
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                marvelItem.comics = marvelListItem.getJSONObject("comics");
//                marvelItem.series = marvelListItem.getJSONObject("series");
//                marvelItem.stories = marvelListItem.getJSONObject("stories");
//                marvelItem.events = marvelListItem.getJSONObject("events");
//                tempJSONarr = marvelListItem.getJSONArray("urls");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("detail")) {
//                        marvelItem.detailURL = tempJSONobj.getString("url");
//                    }
//                    else if (tempJSONobj.getString("type").equals("wiki")) {
//                        marvelItem.wikiURL = tempJSONobj.getString("url");
//                    }
//                    else if (tempJSONobj.getString("type").equals("comiclink")) {
//                        marvelItem.comiclinkURL = tempJSONobj.getString("url");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in urls when parsing character JSON");
//                    }
//                }
//                marvelItem.displayName = marvelItem.name;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
////        catch (java.text.ParseException e) {
////            e.printStackTrace();
////            return null;
////        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
//    public static ArrayList<MarvelComicItem> parseComicsJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelComicItem> marvelItemsList = new ArrayList<MarvelComicItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelComicItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelComicItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.digitalId = marvelListItem.getInt("digitalId");
//                marvelItem.title = marvelListItem.getString("title");
//                marvelItem.issueNumber = marvelListItem.getDouble("issueNumber");
//                marvelItem.variantDescription = marvelListItem.getString("variantDescription");
//                marvelItem.description = marvelListItem.getString("description");
//                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                marvelItem.isbn = marvelListItem.getString("isbn");
//                marvelItem.upc = marvelListItem.getString("upc");
//                marvelItem.diamondCode = marvelListItem.getString("diamondCode");
//                marvelItem.ean = marvelListItem.getString("ean");
//                marvelItem.issn = marvelListItem.getString("issn");
//                marvelItem.format = marvelListItem.getString("format");
//                marvelItem.pageCount = marvelListItem.getInt("pageCount");
//                marvelItem.textObjects = marvelListItem.getJSONArray("textObjects");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                tempJSONarr = marvelListItem.getJSONArray("urls");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("detail")) {
//                        marvelItem.detailURL = tempJSONobj.getString("url");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in urls when parsing comic JSON");
//                    }
//                }
//                tempJSONobj = marvelListItem.getJSONObject("series");
//                marvelItem.seriesURI = tempJSONobj.getString("resourceURI");
//                marvelItem.seriesName = tempJSONobj.getString("name");
//                marvelItem.variants = marvelListItem.getJSONArray("variants");
//                marvelItem.collections = marvelListItem.getJSONArray("collections");
//                marvelItem.collectedIssues = marvelListItem.getJSONArray("collectedIssues");
//                tempJSONarr = marvelListItem.getJSONArray("dates");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("onsaleDate")) {
//                        marvelItem.onsaleDate = dateParser.parse(tempJSONobj.getString("date"));
//                    }
//                    else if (tempJSONobj.getString("type").equals("focDate")) {
//                        marvelItem.focDate = dateParser.parse(tempJSONobj.getString("date"));
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in dates when parsing comic JSON");
//                    }
//                }
//                tempJSONarr = marvelListItem.getJSONArray("prices");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("printPrice")) {
//                        marvelItem.printPrice = (float) tempJSONobj.getDouble("price");
//                    }
//                    else if (tempJSONobj.getString("type").equals("digitalPurchasePrice")) {
//                        marvelItem.digitalPurchasePrice = (float) tempJSONobj.getDouble("price");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in prices when parsing comic JSON");
//                    }
//                }
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                marvelItem.images = marvelListItem.getJSONArray("images");
//                marvelItem.creators = marvelListItem.getJSONObject("creators");
//                marvelItem.characters = marvelListItem.getJSONObject("characters");
//                marvelItem.stories = marvelListItem.getJSONObject("stories");
//                marvelItem.events = marvelListItem.getJSONObject("events");
//
//                marvelItem.displayName = marvelItem.title;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
//    public static ArrayList<MarvelSeriesItem> parseSeriesJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelSeriesItem> marvelItemsList = new ArrayList<MarvelSeriesItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelSeriesItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelSeriesItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.title = marvelListItem.getString("title");
//                marvelItem.description = marvelListItem.getString("description");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                tempJSONarr = marvelListItem.getJSONArray("urls");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("detail")) {
//                        marvelItem.detailURL = tempJSONobj.getString("url");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in urls when parsing series JSON");
//                    }
//                }
//                marvelItem.startYear = marvelListItem.getInt("startYear");
//                marvelItem.endYear = marvelListItem.getInt("endYear");
//                marvelItem.rating = marvelListItem.getString("rating");
//                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                marvelItem.creators = marvelListItem.getJSONObject("creators");
//                marvelItem.characters = marvelListItem.getJSONObject("characters");
//                marvelItem.stories = marvelListItem.getJSONObject("stories");
//                marvelItem.comics = marvelListItem.getJSONObject("comics");
//                marvelItem.events = marvelListItem.getJSONObject("events");
//                marvelItem.next = marvelListItem.getJSONObject("next");
//                marvelItem.previous = marvelListItem.getJSONObject("previous");
//
//                marvelItem.displayName = marvelItem.title;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
//    public static ArrayList<MarvelStoryItem> parseStoriesJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelStoryItem> marvelItemsList = new ArrayList<MarvelStoryItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelStoryItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelStoryItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.title = marvelListItem.getString("title");
//                marvelItem.description = marvelListItem.getString("description");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                marvelItem.type = marvelListItem.getString("type");
//                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                marvelItem.creators = marvelListItem.getJSONObject("creators");
//                marvelItem.characters = marvelListItem.getJSONObject("characters");
//                marvelItem.series = marvelListItem.getJSONObject("series");
//                marvelItem.comics = marvelListItem.getJSONObject("comics");
//                marvelItem.events = marvelListItem.getJSONObject("events");
//                tempJSONobj = marvelListItem.getJSONObject("originalIssue");
//                marvelItem.originalIssueUri = tempJSONobj.getString("resourceURI");
//                marvelItem.originalIssueName = tempJSONobj.getString("name");
//                marvelItem.displayName = marvelItem.title;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
//    public static ArrayList<MarvelCreatorItem> parseCreatorJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelCreatorItem> marvelItemsList = new ArrayList<MarvelCreatorItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelCreatorItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelCreatorItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.firstName = marvelListItem.getString("firstName");
//                marvelItem.middleName = marvelListItem.getString("middleName");
//                marvelItem.lastName = marvelListItem.getString("lastName");
//                marvelItem.suffix = marvelListItem.getString("suffix");
//                marvelItem.fullName = marvelListItem.getString("fullName");
//                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                marvelItem.comics = marvelListItem.getJSONObject("comics");
//                marvelItem.series = marvelListItem.getJSONObject("series");
//                marvelItem.stories = marvelListItem.getJSONObject("stories");
//                marvelItem.events = marvelListItem.getJSONObject("events");
//                tempJSONarr = marvelListItem.getJSONArray("urls");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("detail")) {
//                        marvelItem.detailURL = tempJSONobj.getString("url");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in urls when parsing creators JSON");
//                    }
//                }
//                marvelItem.displayName = marvelItem.fullName;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//
//
//    }
//    public static ArrayList<MarvelEventItem> parseEventsJSON(String marvelJSON) {
//        try {
//            JSONObject marvelObj = new JSONObject(marvelJSON);
//            JSONArray marvelList = marvelObj.getJSONArray("results");
//            ArrayList<MarvelEventItem> marvelItemsList = new ArrayList<MarvelEventItem>();
//            SimpleDateFormat dateParser = new SimpleDateFormat(MARVEL_DATE_FORMAT1);
//            MarvelEventItem marvelItem;
//            JSONObject tempJSONobj;
//            JSONArray tempJSONarr;
//
//            for (int i = 0; i < marvelList.length(); ++i) {
//                marvelItem = new MarvelEventItem();
//                JSONObject marvelListItem = marvelList.getJSONObject(i);
//                marvelItem.id = marvelListItem.getInt("id");
//                marvelItem.title = marvelListItem.getString("title");
//                marvelItem.description = marvelListItem.getString("description");
//                marvelItem.resourceURI = marvelListItem.getString("resourceURI");
//                tempJSONarr = marvelListItem.getJSONArray("urls");
//                for (int j = 0; j < tempJSONarr.length(); ++j) {
//                    tempJSONobj = tempJSONarr.getJSONObject(j);
//                    if (tempJSONobj.getString("type").equals("detail")) {
//                        marvelItem.detailURL = tempJSONobj.getString("url");
//                    }
//                    else {
//                        Log.d(TAG, "Found unexpected type in urls when parsing events JSON");
//                    }
//                }
//                marvelItem.modified = dateParser.parse(marvelListItem.getString("modified"));
//                marvelItem.start = dateParser.parse(marvelListItem.getString("start"));
//                marvelItem.end = dateParser.parse(marvelListItem.getString("end"));
//                tempJSONobj = marvelListItem.getJSONObject("thumbnail");
//                marvelItem.thumbnailPath = tempJSONobj.getString("path") + "." + tempJSONobj.getString("extension");
//
//                marvelItem.creators = marvelListItem.getJSONObject("creators");
//                marvelItem.characters = marvelListItem.getJSONObject("characters");
//                marvelItem.stories = marvelListItem.getJSONObject("stories");
//                marvelItem.comics = marvelListItem.getJSONObject("comics");
//                marvelItem.series = marvelListItem.getJSONObject("series");
//                tempJSONobj = marvelListItem.getJSONObject("next");
//                marvelItem.nextURI = tempJSONobj.getString("resourceURI");
//                marvelItem.nextName = tempJSONobj.getString("name");
//                tempJSONobj = marvelListItem.getJSONObject("previous");
//                marvelItem.previousURI = tempJSONobj.getString("resourceURI");
//                marvelItem.previousName = tempJSONobj.getString("name");
//                marvelItem.displayName = marvelItem.title;
//                marvelItemsList.add(marvelItem);
//            }
//            return marvelItemsList;
//        }
//        catch (java.text.ParseException e) {
//            e.printStackTrace();
//            return null;
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}