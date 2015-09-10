package com.example.admin.qwerty;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PageFragment extends android.support.v4.app.ListFragment {
    String weekday;
    int day, subgroup;
    DataBaseUtility dataBaseUtility;
    SQLiteDatabase db;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    ArrayList<Lesson> stringArrayAdapter;
    Lesson lesson;

    static android.support.v4.app.Fragment newInstance(String page, int day, int sgroup) {
        Log.e("pars", "newInstance");
        android.support.v4.app.Fragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PAGE_NUMBER, page);
        arguments.putInt("day", day);
        arguments.putInt("subgroup", sgroup);
        pageFragment.setArguments(arguments);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("pars", "onCreate");
        stringArrayAdapter = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("pars", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, null);
        SubjectAdapter subjectAdapter = new SubjectAdapter(container.getContext(), stringArrayAdapter);
        setListAdapter(subjectAdapter);
        TextView tvPage = (TextView) view.findViewById(R.id.lessonType);
        weekday = getArguments().getString(ARGUMENT_PAGE_NUMBER);
        day = getArguments().getInt("day");
        subgroup = getArguments().getInt("subgroup");
        tvPage.setText(weekday);
        setSubject();
        return view;
    }


    private void setSubject() {
        Context context = getActivity().getApplicationContext();
        dataBaseUtility = new DataBaseUtility(context);
        db = dataBaseUtility.getWritableDatabase();
        Cursor c = null;
        c = db.rawQuery("Select * from timetable where weeknumber=" + day + "" +
                " and weekday='" + weekday + "' and(subgroup=0 or subgroup=" + subgroup + ")", null);
        stringArrayAdapter.clear();
        if (c.moveToFirst()) {
            int time = c.getColumnIndex("time");
            int subject = c.getColumnIndex("subject");
            int lastname = c.getColumnIndex("lastname");
            int type = c.getColumnIndex("type");
            int audience = c.getColumnIndex("audience");
            do {
                lesson = new Lesson(c.getString(time), c.getString(subject), c.getString(lastname), c.getString(type), c.getString(audience));
                stringArrayAdapter.add(lesson);
            } while (c.moveToNext());


        } else {
            lesson = new Lesson("Выходной");
            stringArrayAdapter.add(lesson);
        }
        c.close();
    }
}


