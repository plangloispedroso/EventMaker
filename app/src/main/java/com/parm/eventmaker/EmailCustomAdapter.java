package com.parm.eventmaker;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EmailCustomAdapter extends ArrayAdapter {

    Email[] emails = null;
    Context context;

    public EmailCustomAdapter(Context context, Email[] emails)
    {
        super(context, R.layout.emaillist_row, emails);
        this.emails = emails;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.emaillist_row, parent, false);
        TextView emailTV = (TextView) convertView.findViewById(R.id.emailTextView);
        CheckBox cb = (CheckBox) convertView.findViewById(R.id.emailCheckBox);
        emailTV.setText(emails[position].getEmail());
        if(emails[position].getCheck() == 1)
            cb.setChecked(true);
        else
            cb.setChecked(false);
        return convertView;
    }
}
