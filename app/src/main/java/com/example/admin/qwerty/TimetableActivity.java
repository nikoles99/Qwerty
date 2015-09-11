package com.example.admin.qwerty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Spinner;
import android.widget.Toast;


public class TimetableActivity extends ActionBarActivity {
    ViewPager viewPager;
    FrameLayout frameLayout;
    PagerAdapter pagerAdapter;
    static final int PAGE_COUNT = 28;
    Long resoult;
    Integer day;
    private int n;
    Button subGroupButton;
    static int subgruup = 1;
    Integer weeknumber;
    String week[] = new String[]{"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};
    Spinner spinner;
    LessonsActivity LessonsActivity = new LessonsActivity();
    FragmentTransaction fTrans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        Time();
        frameLayout = (FrameLayout) findViewById(R.id.fram);
        subGroupButton = (Button) findViewById(R.id.subgroup);
        subGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (subGroupButton.getText().equals("1ая Подгр")) {
                    Time();
                    n = 0;
                    frameLayout.removeView(viewPager);
                    subGroupButton.setText("2ая Подгр");
                    subgruup = 2;
                    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                    frameLayout.addView(viewPager);
                    viewPager = (ViewPager) findViewById(R.id.pager);
                    viewPager.setAdapter(pagerAdapter);


                } else if (subGroupButton.getText().equals("2ая Подгр")) {
                    frameLayout.removeView(viewPager);
                    Time();
                    n = 0;
                    subGroupButton.setText("1ая Подгр");
                    subgruup = 1;
                    pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
                    frameLayout.addView(viewPager);
                    viewPager = (ViewPager) findViewById(R.id.pager);
                    viewPager.setAdapter(pagerAdapter);

                }
            }
        });
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.pager);
        n = viewPager.getCurrentItem();
        viewPager.setAdapter(pagerAdapter);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (getSupportActionBar() == null) {
            setSupportActionBar(toolbar);
        }

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
                        new PrimaryDrawerItem().withName("Добавить группу").withIcon(FontAwesome.Icon.faw_arrow_down),
                        new PrimaryDrawerItem().withName("Добавить Препадователя").withIcon(FontAwesome.Icon.faw_arrow_circle_down),
                        new PrimaryDrawerItem().withName("Помощь").withIcon(FontAwesome.Icon.faw_bolt).withBadge("").withIdentifier(1),
                        new PrimaryDrawerItem().withName("О приложении").withIcon(FontAwesome.Icon.faw_bank)
                )


                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {
                        switch (position) {
                            case 1:
                                fTrans = getFragmentManager().beginTransaction();
                                //fTrans.add(R.id.fram, LessonsActivity);
                                fTrans.commit();
                                viewPager.setVisibility(View.INVISIBLE);
                                getSupportActionBar().setDisplayHomeAsUpEnabled(false);
                                getSupportActionBar().setDisplayShowTitleEnabled(true);
                                spinner.setVisibility(View.INVISIBLE);
                                subGroupButton.setVisibility(View.INVISIBLE);
                                break;
                            case 2:

                                break;
                        }
                    }
                }).build();
    }

    private class MyFragmentPagerAdapter extends FragmentStatePagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (viewPager.getCurrentItem() < n) {
                n--;
                --day;
                if (day == -1)
                    day = 6;
                if (day == 0) {
                    weeknumber--;
                    day = 0;
                    if (weeknumber < 1) {
                        weeknumber = 4;
                    }
                }
                if (weeknumber == 4) {
                    weeknumber = 3;
                }
                Log.e("MyFragmentPagerAdapter", day + " 1");
            } else {
                if (viewPager.getCurrentItem() != n)
                    n++;
                if (day == 6)
                    day = -1;
                if (day == -1) {
                    weeknumber++;
                    day = -1;
                    if (weeknumber > 4) {
                        weeknumber = 1;
                    }
                }
                if (weeknumber == 0) {
                    weeknumber = 4;
                }
                if (day == -1) {
                    spinner.setSelection(6);
                    day++;
                } else {
                    spinner.setSelection(day++);
                }
                Log.e("MyFragmentPagerAdapter", day + " 2");

            }
            return null;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_UNCHANGED;
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("1");
        menu.add("2");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case 0:
                Intent intent = new Intent(this, LessonsActivity.class);
                startActivity(intent);
                Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        if (fTrans == null) {
            finish();
        } else {
            fTrans = getFragmentManager().beginTransaction();
        //    fTrans.remove(LessonsActivity);
            fTrans.commit();
            viewPager.setVisibility(View.VISIBLE);
            spinner.setVisibility(View.VISIBLE);
            subGroupButton.setVisibility(View.VISIBLE);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            fTrans = null;
        }
    }

    public void Time() {
        try {
            Calendar calendar = Calendar.getInstance();
            String input = (calendar.get(Calendar.YEAR) - 1) + "0901";
            final String format = "yyyyMMdd";
            final SimpleDateFormat df = new SimpleDateFormat(format);
            Date date = df.parse(input);
            calendar.setTime(date);
            Long fistSeptemberWeek = calendar.getTimeInMillis();
            Calendar calendar2 = Calendar.getInstance();
            Long firstDesemderWeek = calendar2.getTimeInMillis();
            Calendar diff = Calendar.getInstance();
            day = diff.get(Calendar.DAY_OF_WEEK) - 2;
            diff.setTimeInMillis(firstDesemderWeek - fistSeptemberWeek);
            resoult = diff.getTimeInMillis() / (24 * 60 * 60 * 1000) / 7;
            resoult = (resoult + 1) % 4;
            weeknumber = resoult.intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
