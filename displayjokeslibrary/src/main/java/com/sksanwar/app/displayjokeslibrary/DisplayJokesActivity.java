package com.sksanwar.app.displayjokeslibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DisplayJokesActivity extends AppCompatActivity {

    public static final String JOKE_EXTRA = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_jokes);

        TextView showJokesTextView = (TextView) findViewById(R.id.textview_jokes);

        if (getIntent() != null && getIntent().hasExtra(JOKE_EXTRA)){
            String joke = getIntent().getStringExtra(JOKE_EXTRA);

            if (joke != null || !joke.equals("")){
                showJokesTextView.setText(joke);
            }else {
                showJokesTextView.setText("No Jokes here");
            }
        }
    }
}
