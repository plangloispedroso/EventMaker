package com.parm.eventmaker.utilities;

import android.content.Intent;
import android.net.Uri;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EmailUtilities
{
    public Intent emailIntentMaker(String[] emailAddresses, String subject, String message)
    {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
        i.putExtra(Intent.EXTRA_SUBJECT, subject);
        i.putExtra(Intent.EXTRA_TEXT, message);

        return i;
    }

}
