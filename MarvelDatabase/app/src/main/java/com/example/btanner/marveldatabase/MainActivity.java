package com.example.btanner.marveldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, " ON MAIN CREATE ");

        Button charButton = (Button)findViewById(R.id.btn_characters);
        Button comicButton = (Button)findViewById(R.id.btn_comics);
        Button creatorButton = (Button)findViewById(R.id.btn_creators);
        Button eventButton = (Button)findViewById(R.id.btn_events);
        Button seriesButton = (Button)findViewById(R.id.btn_series);

        charButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doCharacters(v);
            }
        });
        comicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " Click on Character button ");

            }
        });
        creatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " Click on Character button ");

            }
        });
        eventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " Click on Character button ");

            }
        });
        seriesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, " Click on Character button ");

            }
        });
    }

    public void doCharacters(View v) {
        Log.d(TAG, " Click on Character button ");


    }

    public void doComics(View v) {

    }

    public void doCreators(View v) {

    }

    public void doEvents(View v) {

    }

    public void doSeries(View v) {

    }

    public void doStories(View v) {

    }
}
