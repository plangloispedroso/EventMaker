package com.parm.eventmaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public class SearchActivity extends MenuActivity {

    ArrayList<String> names = new ArrayList<>();
    ListView listView;
    private static final String TAG = "searchActivity";
    private Context context;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        context = this;
        Intent mainIntent = getIntent();

        names = mainIntent.getStringArrayListExtra("nameList");

        for(int i = 0; i<names.size(); i++) {
            Log.i(TAG, names.get(i));
        }


        listView = (ListView) findViewById(R.id.list_view);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout
                .simple_list_item_1, android.R.id.text1);
        listView.setAdapter(adapter);
        adapter.addAll(names);

    }

    public void onDataChange(DataSnapshot dataSnapshot) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // Setup the intent to start the QuoteActivity class
//                Intent intent = new Intent(context, .class);
//                intent.putExtra("parent", "quote" + Integer.toString(position + 1));
//                intent.putExtra("cat", categoryNumber);

               // startActivity(intent);
            }
        });

    }

}
