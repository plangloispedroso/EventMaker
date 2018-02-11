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

/**
 * Application to create events and send the information to your contacts. Will search for
 * locations to hold events within a given radius. The user is then shown a form of information, and
 * once the form is submitted, the user will be able to send the info via sms to individuals
 * in their contacts list.
 *
 * @author Philippe Langlois-Pedroso, Ali Dali, Rhai Hinds, Marc-Daniel Dialogo
 */
public class MainActivity extends Activity {

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void navigateCreate(View view){
        Intent intent = new Intent(context, SearchActivity.class);
        startActivity(intent);
    }


    public void search(View view) {
        startActivity(new Intent(this, searchEvents.class));
    }
}
