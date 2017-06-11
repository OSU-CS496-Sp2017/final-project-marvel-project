package com.example.btanner.marveldatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button characterButton = (Button)findViewById(R.id.btn_characters);
        Button commicButton = (Button)findViewById(R.id.btn_comics);
        Button creatorButton = (Button)findViewById(R.id.btn_creators);
        Button eventButton = (Button)findViewById(R.id.btn_events);
        Button seriesButton = (Button)findViewById(R.id.btn_series);
        Button storiesButton = (Button)findViewById(R.id.btn_stories);

    }

    public void doCharacters(View v) {


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
