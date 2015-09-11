package com.example.admin.qwerty;

import android.os.AsyncTask;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.HashMap;

class TimetableLoader extends AsyncTask<String, String, Void> {
    private String group;
    private boolean findWeekDay = false;

    private String nameTag;
    private String weekDay = "Понедельник";

    public TimetableLoader(String group) {
        this.group = group;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            HashMap<String, Group> groupsId = GroupUtility.getGroupsId();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
}
