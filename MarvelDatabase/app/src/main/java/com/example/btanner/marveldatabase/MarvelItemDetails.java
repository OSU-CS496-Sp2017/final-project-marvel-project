package com.example.btanner.marveldatabase;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;

public class MarvelItemDetails extends AppCompatActivity
        implements MarvelRVAdapter.OnMarvelItemClickListener, LoaderManager.LoaderCallbacks<String>{
    private static final String TAG = CategoryListResults.class.getSimpleName();
    private static final String SEARCH_URL_KEY = "marvelSearchURL";
    private static final int MARVEL_SEARCH_LOADER_ID = 0;
    private String mCategory;
    private String mItemId;
    private int currentOffset;
    private SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(CategoryListResults.getContextOfApplication());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_item_details);

        Intent intent = getIntent();
        mCategory = intent.getStringExtra(MarvelRVAdapter.ITEM_CATEGORY);
        mItemId = intent.getStringExtra(MarvelRVAdapter.ITEM_ID);
        currentOffset = 0;

        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);
        makeApiCall();

    }

    @Override
    public void onMarvellItemClick(utils.MarvelItem marvelItem) {

    }

    public void makeApiCall() {
        String apiURL = utils.buildMarvelURL(Integer.toString(currentOffset), sharedPreferences.getString("pref_limit_key", "20"),
                sharedPreferences.getString("pref_order_key", "name"), mCategory, mItemId);
        Log.e(mCategory, apiURL);

        Bundle argsBundle = new Bundle();

        argsBundle.putString(SEARCH_URL_KEY, apiURL);
        getSupportLoaderManager().restartLoader(MARVEL_SEARCH_LOADER_ID, argsBundle, this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args) {
        return new AsyncTaskLoader<String>(this) {

            String apiResultsJSON;

            @Override
            protected void onStartLoading() {
                if(args != null) {
                    if(apiResultsJSON != null) {
                        Log.d(TAG, "AsyncTaskLoader delivering cached results");
                        deliverResult(apiResultsJSON);
                    }
                    else {
                        //Set Progress Bar Visibility
                        Log.d(TAG, "AsyncTaskLoader is doing a forceload");
                        forceLoad();
                    }
                }
            }

            @Override
            public String loadInBackground() {
                if(args != null) {
                    String marvelUrl = args.getString(SEARCH_URL_KEY);
                    Log.d(TAG, "ASYNCTaskLoader making network call: " + marvelUrl);
                    String searchResults = null;
                    try {
                        searchResults = NetworkUtils.doHTTPGet(marvelUrl);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.e("RESULTS", searchResults);
                    return searchResults;
                } else {
                    return null;
                }
            }

            @Override
            public void deliverResult(String data) {
                apiResultsJSON = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        Log.d(TAG, "AsyncTaskLoader's onLoadFinished called");
        //mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
        if (data != null) {
           // mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
           // mMarvelItemsRV.setVisibility(View.VISIBLE);
            if(utils.parseMarvelItemJSON(data) == null){
                Log.d(TAG, "null parse JSON");
            }
            else {
                ArrayList<utils.MarvelItem> marvelItems = utils.parseMarvelItemJSON(data);
                Log.e("ITEM", "" + marvelItems);
//                for (int i = 0; i < marvelItems.size(); i++) {
//                    Log.d(TAG, "item " + Integer.toString(i) + ": " + marvelItems.get(i).displayName);
//                }
               // mMarvelAdapter.updateMarvelItems(marvelItems, mCategory);
            }
        }
        else {
           // mMarvelItemsRV.setVisibility(View.INVISIBLE);
           // mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}
