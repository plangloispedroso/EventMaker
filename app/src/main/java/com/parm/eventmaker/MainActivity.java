package com.parm.eventmaker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    Context context = this;
    Button sendSMSButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        sendSMSButton = (Button) findViewById(R.id.navigation_sms);
//        sendSMSButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(context, SendToContactsActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    public void navigateSMS(View view){
//        Toast.makeText(this, "hi", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(context, SendToContactsActivity.class);
        startActivity(intent);
    }


    public void search(View view) {
        startActivity(new Intent(this, searchEvents.class));
    }
}
