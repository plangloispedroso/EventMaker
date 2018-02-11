package com.parm.eventmaker;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.parm.eventmaker.sqlite.EmailListDBHelper;

import java.util.ArrayList;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EmailListActivity extends AppCompatActivity
{
    private EmailListDBHelper emailDBH;

    private ListView emailListView;

    private  EmailCustomAdapter adapter;

    private EditText newEmailEditText;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emaillist);

        newEmailEditText = findViewById(R.id.newEmailEditText);

        emailDBH = EmailListDBHelper.getEmailListDBHelper(this);
        emailListView = findViewById(R.id.emailListView);

        updateUI();
    }

    public void addEmail(View view)
    {
        if(!newEmailEditText.getText().toString().equals(""))
        {

            emailDBH.insertNewEmailAddress(newEmailEditText.getText().toString(), false);
            updateUI();

            newEmailEditText.setText("");
        }
    }

    private void updateUI()
    {
        ArrayList<Email> emailList = new ArrayList<>();

        SQLiteDatabase db = emailDBH.getReadableDatabase();

        Cursor cursor = db.query(emailDBH.TABLE_EMAIL, new String[]{EmailListDBHelper.COLUMN_ID, EmailListDBHelper.COLUMN_EMAIL, EmailListDBHelper.COLUMN_CHECK}, null, null, null, null, null);

        while(cursor.moveToNext())
        {
            int index = cursor.getColumnIndex(emailDBH.COLUMN_EMAIL);
            int checkIndex = cursor.getColumnIndex(emailDBH.COLUMN_CHECK);
            //emailList.add(cursor.getString(index));

            String email = cursor.getString(index);
            int check = cursor.getInt(checkIndex);

            emailList.add(new Email(email, check));
        }

        Object[] arr = emailList.toArray();
        Email[] emailarr = new Email[arr.length];
        for(int i = 0; i < arr.length; i++)
        {
            emailarr[i] = (Email)arr[i];
        }

        adapter = new EmailCustomAdapter(this, emailarr);
        emailListView.setAdapter(adapter);


        cursor.close();
        db.close();

    }

    public void checkEmail(View view)
    {
        View parent = (View) view.getParent();
        TextView emailTextView = (TextView) parent.findViewById(R.id.emailTextView);
        String email = String.valueOf(emailTextView.getText());

        emailDBH.changeCheckEmail(email, ((CheckBox) parent.findViewById(R.id.emailCheckBox)).isChecked());

        updateUI();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        updateUI();
    }

}
