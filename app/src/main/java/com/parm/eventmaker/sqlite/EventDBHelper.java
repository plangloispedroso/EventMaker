package com.parm.eventmaker.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Marc-Daniel on 2018-02-11.
 */

public class EventDBHelper extends SQLiteOpenHelper
{

    public static final String TABLE_EVENT = "event";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_STARTTIME = "starttime";
    public static final String COLUMN_ENDTIME = "endtime";

    private static final String DATABASE_NAME = "events.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " + TABLE_EVENT + "(" + COLUMN_ID + " integer primary key autoincrement, " + COLUMN_NAME + " text, " +
            COLUMN_DESCRIPTION + " text, " + COLUMN_CATEGORY + " text, " + COLUMN_STARTTIME + " text, " + COLUMN_ENDTIME + " text " + ");";

    private static EventDBHelper eventDBH = null;

    private EventDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public static EventDBHelper getEventDBHelper(Context c)
    {
        if(eventDBH == null)
            eventDBH = new EventDBHelper(c.getApplicationContext());

        return eventDBH;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_EVENT);
        sqLiteDatabase.execSQL(DATABASE_CREATE);
    }

    public long insertEvent(String name, String description, String category, String starttime, String endtime)
    {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DESCRIPTION, description);
        cv.put(COLUMN_CATEGORY, category);
        cv.put(COLUMN_STARTTIME, starttime);
        cv.put(COLUMN_ENDTIME, endtime);

        long code = getWritableDatabase().insert(TABLE_EVENT, null, cv);
        return code;
    }

    public Cursor getEvents()
    {
        return getReadableDatabase().query(TABLE_EVENT, null, null, null, null, null, null);
    }

    public int deleteEventByID(int id)
    {
        return getWritableDatabase().delete(TABLE_EVENT, COLUMN_ID + "=?", new String[] {String.valueOf(id)});
    }

    public int deleteEvent(String event)
    {
        return getWritableDatabase().delete(TABLE_EVENT, COLUMN_NAME + "=?", new String[] {String.valueOf(event)});
    }
}
