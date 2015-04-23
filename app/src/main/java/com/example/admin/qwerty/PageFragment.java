package com.example.admin.qwerty;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class PageFragment extends android.support.v4.app.ListFragment {
    String weekday;
    int day;
    BDConnection bdConnection;
    SQLiteDatabase db;
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    ArrayList<Timetable> stringArrayAdapter;
    Timetable timetable;

    static android.support.v4.app.Fragment newInstance(String page, int day) {
        Log.e("pars", "newInstance");
        android.support.v4.app.Fragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PAGE_NUMBER, page);
        arguments.putInt("day", day);
        pageFragment.setArguments(arguments);

        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("pars", "onCreate");
        stringArrayAdapter=new ArrayList<Timetable>();



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        Log.e("pars", "onCreateView");
        View view = inflater.inflate(R.layout.fragment_main, null);
       /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, stringArrayAdapter);*/
        CostomAdapter costomAdapter=new CostomAdapter(container.getContext(),stringArrayAdapter);
        setListAdapter(costomAdapter);
        TextView tvPage = (TextView) view.findViewById(R.id.texr);
        weekday = getArguments().getString(ARGUMENT_PAGE_NUMBER);
        day = getArguments().getInt("day");
        tvPage.setText(weekday);
        setSubject();
        return view;
    }


    private void setSubject(){
        Context context=getActivity().getApplicationContext();
        bdConnection=new BDConnection(context);
        db = bdConnection.getWritableDatabase();
        Cursor c = null;
        c=db.rawQuery("Select * from timetable where weeknumber="+day+" and weekday='"+weekday+"'",null);
        stringArrayAdapter.clear();
        if (c.moveToFirst()) {
            int time = c.getColumnIndex("time");
            int subject = c.getColumnIndex("subject");
            int lastname = c.getColumnIndex("lastname");
            int type = c.getColumnIndex("type");
            int audience = c.getColumnIndex("audience");
            do {
                timetable=new Timetable(c.getString(time),c.getString(subject),c.getString(lastname),c.getString(type),c.getString(audience));
                stringArrayAdapter.add(timetable);
            } while (c.moveToNext());


        } else {
            timetable = new Timetable("Выходной");
            stringArrayAdapter.add(timetable);
        }

            c.close();
    }
}


