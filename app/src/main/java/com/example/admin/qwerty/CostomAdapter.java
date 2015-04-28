package com.example.admin.qwerty;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Admin on 03.04.2015.
 */

public class CostomAdapter extends BaseAdapter {
    private Context context;
    private  ArrayList<Timetable> data;
    LayoutInflater layoutInflater;
    public CostomAdapter(Context c, ArrayList<Timetable> d) {
        this.context=c;
        this.data=d;
        this.layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
     class ViewHolder {
        TextView time,subject,audience,type,teacher,imag;
    }
    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e("pars","getView");
        View vi = convertView;
        ViewHolder holder = null;
        Timetable p = (Timetable)getItem(position);
        if (vi == null) {
            vi = layoutInflater.inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.time = (TextView) vi.findViewById(R.id.time);
            holder.subject = (TextView) vi.findViewById(R.id.subject);
            holder.audience = (TextView) vi.findViewById(R.id.audience);
            holder.type = (TextView) vi.findViewById(R.id.type);
            holder.teacher = (TextView) vi.findViewById(R.id.teacher);
            holder.imag = (TextView) vi.findViewById(R.id.imag);
            vi.setTag(holder);
        }
        else{
            holder = (ViewHolder) vi.getTag();
        }

        if(p.getSunday()!=null){
            holder.subject.setText(p.getSunday());
            holder.time.setText("");
            holder.teacher.setText("");
            holder.type.setText("");
            holder.audience.setText("");
            holder.imag.setText("");
        }
        else {
            if(p.getLastname().equals("null")|| p.getAudience().equals("null")){
                holder.audience.setText("");
                holder.teacher.setText("");
            }
            else{
                holder.audience.setText(p.getAudience());
                holder.teacher.setText(p.getLastname());
            }


            if(p.getType().equals("ЛР")){
                holder.imag.setBackgroundColor(Color.RED);
            }
            else  if(p.getType().equals("ЛК")){
                holder.imag.setBackgroundColor(Color.GREEN);
            }
            else  if(p.getType().equals("ПЗ")){
                holder.imag.setBackgroundColor(Color.YELLOW);
            }
            holder.time.setText(p.getTime());
            holder.subject.setText(p.getSubject());
            holder.type.setText(p.getType());

        }
        return vi;
    }
}
