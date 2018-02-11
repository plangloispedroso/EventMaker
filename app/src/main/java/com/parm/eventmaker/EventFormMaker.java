package com.parm.eventmaker;

import android.content.Intent;
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


        if (getIntent().getExtras().containsKey("name")) {
            location.setText(getIntent().getExtras().getString("name"));
            address.setText(getIntent().getExtras().getString("location"));
        }
    }

    public void addEvent(View view)
    {
        EventDBHelper dbh = EventDBHelper.getEventDBHelper(this);

        dbh.insertEvent(name.getText().toString(), description.getText().toString(), category.getText().toString(), start.getText().toString(), end.getText().toString());

        super.onBackPressed();
    }

    public void openSendToContacts(View view)
    {
        Intent i = new Intent(EventFormMaker.this, SendToContactsActivity.class);
        i.putExtra("eventName", name.getText().toString());
        i.putExtra("location", location.getText().toString());
        i.putExtra("address", address.getText().toString());
        i.putExtra("category", category.getText().toString());
        i.putExtra("description", description.getText().toString());
        i.putExtra("start", start.getText().toString());
        i.putExtra("end", end.getText().toString());
        startActivity(i);
    }
}
