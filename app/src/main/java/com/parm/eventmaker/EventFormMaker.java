package com.parm.eventmaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.parm.eventmaker.sqlite.EventDBHelper;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EventFormMaker extends AppCompatActivity
{

    EditText name, category, description, start, end, location, address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventform);

        name = findViewById(R.id.eventnameET);
        category = findViewById(R.id.categoryET);
        description = findViewById(R.id.descriptionET);
        start = findViewById(R.id.startET);
        end = findViewById(R.id.endET);
        location = findViewById(R.id.placeET);
        address = findViewById(R.id.addressET);


        if (getIntent().getExtras().containsKey("location_name")) {
            location.setText(getIntent().getExtras().getString("location_name"));
            address.setText(getIntent().getExtras().getString("address"));
        }
    }

    public void addEvent(View view)
    {
        EventDBHelper dbh = EventDBHelper.getEventDBHelper(this);

        dbh.insertEvent(name.getText().toString(), description.getText().toString(), category.getText().toString(), start.getText().toString(), end.getText().toString());

        super.onBackPressed();
    }
}
