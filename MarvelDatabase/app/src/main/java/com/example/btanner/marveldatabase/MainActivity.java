package com.example.btanner.marveldatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import okhttp3.internal.Util;

public class MainActivity extends AppCompatActivity {

    public static final String CATEGORY_MESSAGE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button characterButton = (Button)findViewById(R.id.btn_characters);
        characterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doCharacters(v);
                //}
            }
        });

        Button comicsButton = (Button)findViewById(R.id.btn_comics);
        comicsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doComics(v);
                //}
            }
        });

        Button creatorsButton = (Button)findViewById(R.id.btn_creators);
        creatorsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doCreators(v);
                //}
            }
        });

        Button eventsButton = (Button)findViewById(R.id.btn_events);
        eventsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doEvents(v);
                //}
            }
        });


        Button seriesButton = (Button)findViewById(R.id.btn_series);
        seriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doSeries(v);
                //}
            }
        });


        Button storiesButton = (Button)findViewById(R.id.btn_stories);
        storiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if (!TextUtils.isEmpty(searchQuery)) {
                doStories(v);
                //}
            }
        });
    }

    public void doCharacters(View v) {
        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "characters");
        this.startActivity(intent);
    }

    public void doComics(View v) {
        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "comics");
        this.startActivity(intent);
    }

    public void doCreators(View v) {
        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "creators");
        this.startActivity(intent);
    }

    public void doEvents(View v) {

        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "events");
        this.startActivity(intent);
    }

    public void doSeries(View v) {
        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "series");
        this.startActivity(intent);
    }

    public void doStories(View v) {
        Intent intent = new Intent(this, CategoryListResults.class);
        intent.putExtra(CATEGORY_MESSAGE, "stories");
        this.startActivity(intent);
    }
}