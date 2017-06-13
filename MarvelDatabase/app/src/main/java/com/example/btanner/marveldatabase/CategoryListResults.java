package com.example.btanner.marveldatabase;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryListResults extends AppCompatActivity implements MarvelRVAdapter.OnMarvelItemClickListener, LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = CategoryListResults.class.getSimpleName();
    private static final String SEARCH_URL_KEY = "marvelSearchURL";
    private static final int MARVEL_SEARCH_LOADER_ID = 0;

    private TextView mLoadingErrorMessageTV;
    private ProgressBar mLoadingIndicatorPB;
    private RecyclerView mMarvelItemsRV;
    private MarvelRVAdapter mMarvelAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        Intent intent = getIntent();
        String category = intent.getStringExtra(MainActivity.CATEGORY_MESSAGE);

        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);
        makeApiCall(category);

        mLoadingErrorMessageTV = (TextView) findViewById(R.id.tv_loading_error_message);
        mLoadingIndicatorPB = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMarvelItemsRV = (RecyclerView) findViewById(R.id.rv_marvel_items);

        mMarvelAdapter = new MarvelRVAdapter(this);
        mMarvelItemsRV.setAdapter(mMarvelAdapter);
        mMarvelItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mMarvelItemsRV.setHasFixedSize(true);

    }

    @Override
    public void onMarvellItemClick(utils.MarvelItem marvelItem) {

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
        mLoadingIndicatorPB.setVisibility(View.INVISIBLE);
        if (data != null) {
            mLoadingErrorMessageTV.setVisibility(View.INVISIBLE);
            mMarvelItemsRV.setVisibility(View.VISIBLE);
            ArrayList<utils.MarvelItem> marvelItems = utils.parseMarvelItemJSON(data);
            //Log.d(TAG, "Items in parse JSON: " + Integer.toString(marvelItems.size()));
            mMarvelAdapter.updateMarvelItems(marvelItems);
        }
        else {
            mMarvelItemsRV.setVisibility(View.INVISIBLE);
            mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }


}
