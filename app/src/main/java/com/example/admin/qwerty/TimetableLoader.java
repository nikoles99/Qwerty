package com.example.admin.qwerty;

import android.app.Fragment;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class TimetableLoader extends Fragment {
    EditText numberGroup;
    Button update;
    String group;
    boolean findWeekDay = false;
    HashMap hashMap = new HashMap();
    String nameTag, weekDay = "Понедельник";
    DataBaseUtility dataBaseUtility;
    ContentValues cv;
    SQLiteDatabase db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.time_table_loader, null);
        update = (Button) view.findViewById(R.id.button);
        numberGroup = (EditText) view.findViewById(R.id.textView);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBaseUtility = new DataBaseUtility(getActivity().getApplicationContext());
                cv = new ContentValues();
                db = dataBaseUtility.getWritableDatabase();
                db.execSQL("DELETE FROM timetable");
                MyTask mt = new MyTask();
                mt.execute();

            }
        });
        return view;
    }

    class MyTask extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... params) {
            try {
                group = numberGroup.getText().toString();
                URL url = new URL("http://www.bsuir.by/schedule/rest/schedule/" + 220601 + "");
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput((url.openStream()), null);
                while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {

                    switch (xpp.getEventType()) {
                        case XmlPullParser.START_DOCUMENT:

                            break;
                        case XmlPullParser.START_TAG:
                            if (xpp.getName().equals("weekDay")) {
                                findWeekDay = true;
                            }
                            nameTag = xpp.getName();

                            break;
                        case XmlPullParser.END_TAG:
                            if (xpp.getName().equals("weekNumber")) {
                                db.execSQL("Insert into timetable(weekday,weeknumber,type,time,subject," +
                                        "subgroup,audience,firstname,midlname,lastname) values ('" +
                                        weekDay + "','" +
                                        (String) hashMap.get("weekNumber") + "','" +
                                        (String) hashMap.get("lessonType") + "','" +
                                        (String) hashMap.get("lessonTime") + "','" +
                                        (String) hashMap.get("subject") + "','" +
                                        (String) hashMap.get("numSubgroup") + "','" +
                                        (String) hashMap.get("auditory") + "','" +
                                        (String) hashMap.get("firstName") + "','" +
                                        (String) hashMap.get("middleName") + "','" +
                                        (String) hashMap.get("lastName") + "')");


                            }
                            if (xpp.getName().equals("schedule"))
                                hashMap.clear();
                            break;

                        case XmlPullParser.TEXT:
                            hashMap.put(nameTag, xpp.getText());
                            if (findWeekDay) {
                                if (xpp.getText().equals("Понедельник"))
                                    weekDay = "Вторник";
                                else if (xpp.getText().equals("Вторник"))
                                    weekDay = "Среда";
                                else if (xpp.getText().equals("Среда"))
                                    weekDay = "Четверг";
                                else if (xpp.getText().equals("Четверг"))
                                    weekDay = "Пятница";
                                else if (xpp.getText().equals("Пятница"))
                                    weekDay = "Суббота";
                                else if (xpp.getText().equals("Суббота"))
                                    weekDay = "Воскресенье";
                                findWeekDay = false;
                            }
                            break;

                        default:
                            break;
                    }

                    xpp.next();
                }
                dataBaseUtility.close();
                publishProgress("Расписание загружено");
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (XmlPullParserException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
                publishProgress("Группа не найдена");

            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getActivity().getApplicationContext(), values[0], Toast.LENGTH_LONG).show();
        }
    }
}
