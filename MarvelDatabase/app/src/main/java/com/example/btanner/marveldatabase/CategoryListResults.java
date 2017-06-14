package com.example.btanner.marveldatabase;

import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class CategoryListResults extends AppCompatActivity implements MarvelRVAdapter.OnMarvelItemClickListener, LoaderManager.LoaderCallbacks<String> {

    private static final String TAG = CategoryListResults.class.getSimpleName();
    private static final String SEARCH_URL_KEY = "marvelSearchURL";
    private static final int MARVEL_SEARCH_LOADER_ID = 0;

    private TextView mLoadingErrorMessageTV;
    private EditText txtFilter;
    private Button btnNext, btnPrev, btnFilter;
    private ProgressBar mLoadingIndicatorPB;
    private RecyclerView mMarvelItemsRV;
    private MarvelRVAdapter mMarvelAdapter;
    private String mCategory;
    private int currentOffset;
    private int totalAvailableResults;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        Intent intent = getIntent();
        mCategory = intent.getStringExtra(MainActivity.CATEGORY_MESSAGE);
        currentOffset = 0;
        totalAvailableResults = 0;
        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);


        mLoadingErrorMessageTV = (TextView) findViewById(R.id.tv_loading_error_message);
        mLoadingIndicatorPB = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        mMarvelItemsRV = (RecyclerView) findViewById(R.id.rv_marvel_items);
        btnNext = (Button) findViewById(R.id.btn_next);
        btnPrev = (Button) findViewById(R.id.btn_prev);
        btnFilter = (Button) findViewById(R.id.btn_filter);
        txtFilter = (EditText) findViewById(R.id.txt_filter);



        getSupportLoaderManager().initLoader(MARVEL_SEARCH_LOADER_ID, null, this);
        makeApiCall();



        mMarvelAdapter = new MarvelRVAdapter(this);
        mMarvelAdapter.setContext(this);
        mMarvelItemsRV.setAdapter(mMarvelAdapter);
        mMarvelItemsRV.setLayoutManager(new LinearLayoutManager(this));
        mMarvelItemsRV.setHasFixedSize(true);

    }



    public void getNextResults(View v) {
        Log.d(TAG, Integer.toString(totalAvailableResults));
        if (currentOffset < (totalAvailableResults - 100)) {
            currentOffset += 100;
            makeApiCall();
        }
    }

    public void getPrevResults(View v) {
        if (currentOffset >= 100) {
            currentOffset -= 100;
            makeApiCall();
        }
    }

    private void updateButtons() {
        if ((currentOffset >= 100)) {
            btnPrev.setClickable(true);
            btnPrev.setAlpha(1.0f);
            if (currentOffset < (totalAvailableResults - 100)) {
                btnNext.setClickable(true);
                btnNext.setAlpha(1.0f);
            }
            else {
                btnNext.setClickable(false);
                btnNext.setAlpha(.5f);
            }
        }
        else {
            btnPrev.setClickable(false);
            btnPrev.setAlpha(.5f);
        }
    }

    public void doFilter() {


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.categorylistresults, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    public void makeApiCall() {
        updateButtons();
        String apiURL = utils.buildMarvelURL(Integer.toString(currentOffset), mCategory);

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
                        mLoadingIndicatorPB.setVisibility(View.VISIBLE);
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
                        JSONObject tempJSONObj = new JSONObject(searchResults);
                        JSONObject tempJSONobjData = tempJSONObj.getJSONObject("data");
                        if (!tempJSONobjData.isNull("total")) {
                            totalAvailableResults = tempJSONobjData.getInt("total");
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                    //Log.e("RESULTS", searchResults);
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
            if(utils.parseMarvelItemJSON(data) == null){
                Log.d(TAG, "null parse JSON");
            }
            else {
                ArrayList<utils.MarvelItem> marvelItems = utils.parseMarvelItemJSON(data);
//                for (int i = 0; i < marvelItems.size(); i++) {
//                    Log.d(TAG, "item " + Integer.toString(i) + ": " + marvelItems.get(i).displayName);
//                }
                mMarvelAdapter.updateMarvelItems(marvelItems, mCategory);
            }
        }
        else {
            mMarvelItemsRV.setVisibility(View.INVISIBLE);
            mLoadingErrorMessageTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    @Override
    public void onMarvellItemClick(utils.MarvelItem marvelItem) {

    }
}
