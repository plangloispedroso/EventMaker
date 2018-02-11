package com.parm.eventmaker;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Activity used to send an sms to another person by inputting their phone number.
 *
 * Created by Philippe Langlois-Pedroso on 2018-02-11.
 */

public class SendToContactsActivity extends Activity{

    Context context;
    Button sendButton;
    EditText numberEdit, smsEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_to_contacts);

        context = getApplicationContext();
        sendButton = (Button) findViewById(R.id.send_contact_send_button);
        numberEdit = (EditText) findViewById(R.id.send_contact_phone_edit);
        smsEdit = (EditText) findViewById(R.id.send_contact_sms_edit);

        sendButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String phoneNumber = numberEdit.getText().toString();
                String sms = smsEdit.getText().toString();
                double phoneNum = 0;

                try {
                    phoneNum = Double.parseDouble(phoneNumber);
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phoneNumber, null, sms, null, null);
                    Toast.makeText(context, getResources().getString(R.string.success_sending_sms),
                            Toast.LENGTH_LONG).show();
                }catch(NumberFormatException nfe){
                    Toast.makeText(context, getResources().getString(R.string.error_invalid_phone_number),
                            Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(context, getResources().getString(R.string.error_sending_sms),
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}
