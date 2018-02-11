package com.parm.eventmaker;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Activity used to send an sms to another person by inputting their phone number.
 *
 * Created by Philippe Langlois-Pedroso on 2018-02-11.
 */

public class SendToContactsActivity extends Activity{

    Context context;
    Button sendButton;
    EditText numberEdit;
    String phoneNo, sms;
    TextView smsText;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_to_contacts);

        context = getApplicationContext();
        sendButton = (Button) findViewById(R.id.send_contact_send_button);
        numberEdit = (EditText) findViewById(R.id.send_contact_phone_edit);
        smsText = (TextView) findViewById(R.id.send_contact_sms_edit);
        phoneNo = numberEdit.getText().toString();
        sms = smsText.getText().toString();
    }

    /**
     *
     * @param view
     */
    public void checkPermissions(View view){
        if(ContextCompat.checkSelfPermission(context,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            sendMessage();
        }else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS}, 130);
        }
    }

    /**
     * Check if the user has SENS_SMS permission set to allow. If allowed, send a message. If
     * the user has not allowed do nothing.
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 130: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    sendMessage();
                }
                return;
            }
        } // end of switch
    }

    /**
     *
     */
    public void sendMessage(){

        String number = numberEdit.getText().toString();
        String message;
        Intent intent = getIntent();
        String eventName = intent.getStringExtra("eventName");
        String location = intent.getStringExtra("location");
        String address = intent.getStringExtra("address");
        String category = intent.getStringExtra("category");
        String time = intent.getStringExtra("time");
        String day = intent.getStringExtra("day");
        String month = intent.getStringExtra("month");

        message = "Event: " +eventName +" Location: " +location +" Address: " +address +" Category: "
                +category;

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS faild, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
