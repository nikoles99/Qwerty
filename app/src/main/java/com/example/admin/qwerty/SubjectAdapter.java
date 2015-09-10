package com.example.admin.qwerty;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SubjectAdapter extends BaseAdapter {
    private ArrayList<Lesson> lessons;
    private LayoutInflater layoutInflater;

    public SubjectAdapter(Context context, ArrayList<Lesson> lessons) {
        this.lessons = lessons;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    class ViewHolder {
        TextView time, subject, audience, type, teacher, imag;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("pars", "getView");
        View vi = convertView;
        ViewHolder holder = null;
        Lesson p = (Lesson) getItem(position);
        if (vi == null) {
            vi = layoutInflater.inflate(R.layout.lesson_item, parent, false);
            holder = new ViewHolder();
            holder.time = (TextView) vi.findViewById(R.id.time);
            holder.subject = (TextView) vi.findViewById(R.id.subject);
            holder.audience = (TextView) vi.findViewById(R.id.audience);
            holder.type = (TextView) vi.findViewById(R.id.type);
            holder.teacher = (TextView) vi.findViewById(R.id.teacher);
            holder.imag = (TextView) vi.findViewById(R.id.imag);
            vi.setTag(holder);
        } else {
            holder = (ViewHolder) vi.getTag();
        }

        if (p.getSunday() != null) {
            holder.subject.setText(p.getSunday());
            holder.time.setText("");
            holder.teacher.setText("");
            holder.type.setText("");
            holder.audience.setText("");
            holder.imag.setText("");
        } else {
            if (p.getLastname().equals("null") || p.getAudience().equals("null")) {
                holder.audience.setText("");
                holder.teacher.setText("");
            } else {
                holder.audience.setText(p.getAudience());
                holder.teacher.setText(p.getLastname());
            }


            if (p.getType().equals("ЛР")) {
                holder.imag.setBackgroundColor(Color.RED);
            } else if (p.getType().equals("ЛК")) {
                holder.imag.setBackgroundColor(Color.GREEN);
            } else if (p.getType().equals("ПЗ")) {
                holder.imag.setBackgroundColor(Color.YELLOW);
            }
            holder.time.setText(p.getTime());
            holder.subject.setText(p.getName());
            holder.type.setText(p.getType());

        }
        return vi;
    }
}
