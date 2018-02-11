package com.parm.eventmaker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.parm.eventmaker.MenuActivity;

/**
 * Created by alman on 2018-02-11.
 */

public class ChosenOneActivity extends MenuActivity {

    TextView name, location;
    String stringname;
    String stringlocation;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chosenevent);

        name = (TextView) findViewById(R.id.name);
        location = (TextView) findViewById(R.id.location);

        Intent intent = getIntent();

        stringname = intent.getStringExtra("name");
        stringlocation = intent.getStringExtra("location");

        name.setText(stringname);
        location.setText(stringlocation);

    }



    public void book(View view) {
        Intent intent2 = new Intent(this, EventFormMaker.class);
        intent2.putExtra("name", stringname);
        intent2.putExtra("location", stringlocation);
        startActivity(intent2);
    }


    public void back(View view) {
        startActivity(new Intent(this, searchEvents.class));
    }
}
