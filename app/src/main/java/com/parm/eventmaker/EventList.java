package com.parm.eventmaker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parm.eventmaker.sqlite.EventDBHelper;

import java.util.ArrayList;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EventList extends AppCompatActivity
{
    private EventDBHelper eventsDBH;
    private ListView eventsListView;
    private ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);


        eventsDBH = eventsDBH.getEventDBHelper(this);


        eventsListView = findViewById(R.id.eventListView);

        eventsListView.setOnItemClickListener(openEventOnClick);

        updateUI();
    }

    private AdapterView.OnItemClickListener openEventOnClick = new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
        {
            String event = (String)adapterView.getItemAtPosition(position);

            Intent i = new Intent(EventList.this, ItemEvent.class);
            i.putExtra("event", event);

            startActivity(i);
        }
    };

    private void updateUI()
    {
        ArrayList<String> eventList = new ArrayList<>();

        SQLiteDatabase db = eventsDBH.getReadableDatabase();

        Cursor cursor = db.query(eventsDBH.TABLE_EVENT, new String[]{eventsDBH.COLUMN_NAME}, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(eventsDBH.COLUMN_NAME);
            eventList.add(cursor.getString(index));
        }

        if(adapter == null)
        {
            adapter = new ArrayAdapter<String>(this, R.layout.event, R.id.event, eventList);
            eventsListView.setAdapter(adapter);
        }
        else
        {
            adapter.clear();
            adapter.addAll(eventList);
            adapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }


    public void newEvent(View view)
    {
        Intent intent = new Intent(this, EventFormMaker.class);

        startActivity(intent);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

}

