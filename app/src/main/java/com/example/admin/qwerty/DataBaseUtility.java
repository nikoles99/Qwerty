package com.example.admin.qwerty;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseUtility extends SQLiteOpenHelper {
    public DataBaseUtility(Context context) {
        super(context, "timetable", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table timetable ("
                + "id integer primary key autoincrement,"
                + "weekday text,"
                + "weeknumber text,"
                + "type,"
                + "time text,"
                + "subject text,"
                + "subgroup text,"
                + "audience text,"
                + "firstname text,"
                + "midlname text,"
                + "lastname text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
