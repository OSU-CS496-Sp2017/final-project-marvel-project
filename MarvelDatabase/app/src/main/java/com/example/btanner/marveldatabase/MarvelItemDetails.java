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
import android.widget.TextView;

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
    private ArrayList<utils.MarvelItem> marvelItems;
    private utils.MarvelItem marvelItem;

    private TextView mTitleTV;
    private TextView mModifiedTV;
    private TextView mDescriptionTV;
    private TextView mDetailsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_item_details);

        mTitleTV = (TextView)findViewById(R.id.tv_title);
        mModifiedTV = (TextView)findViewById(R.id.tv_modified);
        mDescriptionTV = (TextView)findViewById(R.id.tv_description);
        mDetailsTV = (TextView)findViewById(R.id.tv_details);

        Intent intent = getIntent();

        currentOffset = 0;

        Bundle extras = intent.getExtras();
        mItemId = extras.getString(utils.MarvelItem.EXTRA_MARVEL_ID);
        mCategory = extras.getString(utils.MarvelItem.EXTRA_MARVEL_CATEGORY);


        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);
        Log.d(TAG, "id: " + mItemId + " cat: " + mCategory);

        makeApiCall();

    }

    public void showDetails(){
        Log.d(TAG, "SHOW DETAILS");
        if(mCategory.equals("characters")) {
            mTitleTV.setText(marvelItem.name);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("Descrition: " +  marvelItem.description);
            mDetailsTV.setText("more details:\t" + marvelItem.detailURL +
                     "\ncomic link:\t" + marvelItem.comiclinkURL +
                     "\nwiki:\t" + marvelItem.wikiURL);
        }
        else if(mCategory.equals("comics")) {
            mTitleTV.setText(marvelItem.title);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("On Sale Date:\n\t" + marvelItem.onsaleDate +
                                    "\nFoc Date:\n\t" + marvelItem.focDate);
            mDetailsTV.setText("more details:\t" + marvelItem.detailURL);
        }
        else if(mCategory.equals("creators")) {
            mTitleTV.setText(marvelItem.fullName);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("");
            mDetailsTV.setText("more details:\t" + marvelItem.detailURL);
        }
        else if(mCategory.equals("events")) {
            mTitleTV.setText(marvelItem.title);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("Descrition: " +  marvelItem.description);
            mDetailsTV.setText("Start Date:\n\t" + marvelItem.start +
                                "\nEnd Date:\n\t" + marvelItem.end);
        }
        else if(mCategory.equals("series")) {
            mTitleTV.setText(marvelItem.title);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("Descrition: " +  marvelItem.description);
            mDetailsTV.setText("Start Year:\t" + marvelItem.startYear +
                                "\nEnd Year:\t" + marvelItem.endYear);
        }
        else if(mCategory.equals("stories")){
            mTitleTV.setText(marvelItem.title);
            mModifiedTV.setText("Modifed Date:\n\t" + marvelItem.modified);
            mDescriptionTV.setText("Descrition: " +  marvelItem.description);
            mDetailsTV.setText("Original Issue Name:\t" + marvelItem.originalIssueName);
        }
        else {
            mTitleTV.setText("Error");
        }

    }

    @Override
    public void onMarvellItemClick(utils.MarvelItem marvelItem) {

    }

    public void makeApiCall() {
        String apiURL = utils.buildMarvelURL(mCategory, mItemId);

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
                marvelItems = utils.parseMarvelItemJSON(data);
                marvelItem = marvelItems.get(0);
                Log.e("ITEM SIZE ", "" + marvelItems.size());
                for (int i = 0; i < marvelItems.size(); i++) {
                    Log.d(TAG, "item " + Integer.toString(i) + ": " + marvelItem.name);
                }
                showDetails();
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
