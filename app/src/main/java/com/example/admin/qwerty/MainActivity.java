package com.example.admin.qwerty;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends ActionBarActivity{


    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    static final int PAGE_COUNT = 28;
    private String mTitle;
    Long resoult;
    Integer day;
    Integer weeknumber;
    String week[]=new String[]{"Воскресенье","Понедельник","Вторник","Среда","Четверг","Пятница","Суббота"};
    int i=-1;
    Spinner spinner;
    UpdateTimetable updateTimetable=new UpdateTimetable();
    FragmentTransaction fTrans;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    try {
        Calendar calendar = Calendar.getInstance();
        String input =(calendar.get(Calendar.YEAR)-1)+"0901";
        final String format = "yyyyMMdd";
        final SimpleDateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(input);
        calendar.setTime(date);
        Long fistSeptemberWeek=calendar.getTimeInMillis();
        Calendar calendar2 = Calendar.getInstance();
        Long firstDesemderWeek=calendar2.getTimeInMillis();
        Calendar diff = Calendar.getInstance();
        day =diff.get(Calendar.DAY_OF_WEEK)-2;
        Log.e("pars",day.toString());
        diff.setTimeInMillis(firstDesemderWeek - fistSeptemberWeek);
        resoult =diff.getTimeInMillis()/(24 * 60 * 60 * 1000)/7;
        resoult=(resoult+1)%4;
        weeknumber=resoult.intValue();


        } catch (ParseException e) {
        e.printStackTrace();
        }
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());

        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, week);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner = (Spinner) findViewById(R.id.spinner_nav);
        spinner.setAdapter(adapter);


        new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Расписание").withIcon(FontAwesome.Icon.faw_eye).withBadge("").withIdentifier(1),
                        new PrimaryDrawerItem().withName("eny").withIcon(FontAwesome.Icon.faw_adjust),
                        new SectionDrawerItem().withName(R.string.drawer_item_settings)

                )


                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:

                                fTrans = getFragmentManager().beginTransaction();
                                fTrans.add(R.id.fram, updateTimetable);
                                fTrans.commit();
                                break;
                            case 2:

                                break;

                        }
                    }
                }).build();

    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter{

            public MyFragmentPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {
                if(day==6)
                    day=-1;
                if(day==-1){
                    weeknumber++;
                    day=-1;
                    if(weeknumber>4){
                        weeknumber=1;
                    }
                }
                if(weeknumber==0){
                    weeknumber=4;
                }
                mTitle = day.toString();
                /*if(actionBar!=null)
                actionBar.setTitle(mTitle);*/
                spinner.setSelection(++day);
                return PageFragment.newInstance(week[day],weeknumber);
            }

            @Override
            public int getCount() {
                return PAGE_COUNT;
            }
        }


    public void onSectionAttached(int number) {
        switch (number) {
            case 1:

                break;
            case 2:

                break;

        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {



            menu.add("1");
            menu.add("2");
            getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        int id = item.getItemId();

        switch (item.getItemId()){
            case 0:
                Intent intent = new Intent(this, UpdateTimetable.class);
                startActivity(intent);
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
