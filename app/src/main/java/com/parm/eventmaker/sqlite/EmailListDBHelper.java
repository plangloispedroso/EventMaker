package com.parm.eventmaker.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EmailListDBHelper extends SQLiteOpenHelper
{

    public static final String TABLE_EMAIL = "email";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CHECK = "checked";

    private static final String DATABASE_NAME = "emaillist.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_EMAIL + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_EMAIL + " text not null, " +
            COLUMN_CHECK + " integer" + ");";

    private static EmailListDBHelper emailDB = null;

    private EmailListDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static EmailListDBHelper getEmailListDBHelper(Context c)
    {
        if(emailDB == null)
            emailDB = new EmailListDBHelper(c.getApplicationContext());

        return emailDB;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EMAIL);
        sqLiteDatabase.execSQL(DATABASE_CREATE);

    }

    public long insertNewEmailAddress(String email, boolean check)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_CHECK, check);

        long code = getWritableDatabase().insert(TABLE_EMAIL, null, cv);System.out.println("here3");
        return code;
    }


    public Cursor getNotes()
    {
        return getReadableDatabase().query(TABLE_EMAIL, null, null, null, null, null, null);
    }


    public int deleteEmailByID(int id)
    {
        return getWritableDatabase().delete(TABLE_EMAIL, COLUMN_ID + "=?", new String[] {String.valueOf(id)});
    }

    public int deleteEmail(String email)
    {
        return getWritableDatabase().delete(TABLE_EMAIL, COLUMN_EMAIL + "=?", new String[] {String.valueOf(email)});
    }

    public int changeCheckEmail(String email, boolean check)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHECK, check);

        return getWritableDatabase().update(TABLE_EMAIL, cv, COLUMN_EMAIL + " =? ", new String[] { String.valueOf(email) });
    }
}
