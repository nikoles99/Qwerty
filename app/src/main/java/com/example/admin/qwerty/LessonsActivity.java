package com.example.admin.qwerty;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LessonsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table_loader);

        Button loadTimetable = (Button) findViewById(R.id.loadTimetable);

        final EditText group = (EditText) findViewById(R.id.group);

        loadTimetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeTimetable();
                loadTimetable(group.getText().toString());
            }
        });
    }

    private void loadTimetable(String group) {
        TimetableLoader timetableLoader = new TimetableLoader(group);
        timetableLoader.execute();
    }

    private void removeTimetable() {
        DataBaseUtility dataBaseUtility = new DataBaseUtility(getApplicationContext());
        SQLiteDatabase db = dataBaseUtility.getWritableDatabase();
        db.execSQL("DELETE FROM timetable");
    }
}
