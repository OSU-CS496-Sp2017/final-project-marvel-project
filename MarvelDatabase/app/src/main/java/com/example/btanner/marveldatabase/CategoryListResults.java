package com.example.btanner.marveldatabase;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class CategoryListResults extends AppCompatActivity
    implements LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = CategoryListResults.class.getSimpleName();
    private static final String SEARCH_URL_KEY = "marvelSearchURL";
    private static final int MARVEL_SEARCH_LOADER_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_results);

        Intent intent = getIntent();
        String category = intent.getStringExtra(MainActivity.CATEGORY_MESSAGE);

        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);
        makeApiCall(category);
    }

    public void makeApiCall(String category) {
        String apiURL = utils.buildMarvelURL(category);
        Log.e(category, apiURL);

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
        //hide progressBar

        if(data != null) {
            //Parse Data
            //Update Data on adapter
        } else {
            //Error messages
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

}
