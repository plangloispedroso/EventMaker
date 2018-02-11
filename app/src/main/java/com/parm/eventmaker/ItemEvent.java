package com.parm.eventmaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class ItemEvent extends AppCompatActivity{

    TextView fullEventTextView;

    /**
     * Creates and sets up the ItemNote activity.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.itemevent);

        fullEventTextView = findViewById(R.id.eventItemTV);

        if (getIntent().getExtras().containsKey("event")) {
            String event = getIntent().getStringExtra("event");
            fullEventTextView.setText(event);
        }
    }
}
