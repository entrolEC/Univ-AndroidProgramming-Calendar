package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private DBHelper mDBHelper;
    Calendar calendar = Calendar.getInstance(), actionBarCalendar = Calendar.getInstance();
    FragmentStateAdapter vpAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        Intent intent = getIntent();
        if (intent.getStringExtra("year") != null) {
            calendar.set(Calendar.YEAR, Integer.parseInt(intent.getStringExtra("year")));
            calendar.set(Calendar.MONTH, Integer.parseInt(intent.getStringExtra("month")));
            calendar.set(Calendar.DATE, Integer.parseInt(intent.getStringExtra("date")));
            actionBarCalendar.set(Calendar.YEAR, Integer.parseInt(intent.getStringExtra("year")));
            actionBarCalendar.set(Calendar.MONTH, Integer.parseInt(intent.getStringExtra("month")));
            actionBarCalendar.set(Calendar.DATE, Integer.parseInt(intent.getStringExtra("date")));
        }

        viewPager = findViewById(R.id.vpPager);
        if (intent.getStringExtra("lastAction") == null) {
            vpAdapter = new MonthPagerAdapter(this, calendar);
        }
        else if (intent.getStringExtra("lastAction").equals("month")) {
            vpAdapter = new MonthPagerAdapter(this, calendar);
        }
        else if (intent.getStringExtra("lastAction").equals("week")) {
            vpAdapter = new WeekPagerAdapter(this, calendar, calendar);
        }

        viewPager.setAdapter(vpAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.i("Position", Integer.toString(position));
                if (viewPager.getAdapter() instanceof MonthPagerAdapter) {
                    actionBarCalendar.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
                    actionBarCalendar.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) - (50 - (position == 0 ? 50 : position)));

                    actionBar = getSupportActionBar();
                    actionBar.setTitle(Integer.toString(actionBarCalendar.get(Calendar.YEAR)) + "년" + Integer.toString(actionBarCalendar.get(Calendar.MONTH) + 1) + "월");
                } else {
                    actionBarCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                    actionBarCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
                    actionBarCalendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - (50 - (position == 0 ? 50 : position)) * 7);

                    actionBar = getSupportActionBar();
                    actionBar.setTitle(Integer.toString(actionBarCalendar.get(Calendar.YEAR)) + "년" + Integer.toString(actionBarCalendar.get(Calendar.MONTH) + 1) + "월");
                }
            }
        });

        mDBHelper = new DBHelper(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), AddScheduleActivity.class);
               intent.putExtra("selectedDate", calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DATE));
               intent.putExtra("start_time", String.valueOf(calendar.get(Calendar.HOUR)));
                Log.i("check start_time", String.valueOf(calendar.get(Calendar.HOUR)));
               startActivity(intent);
            }
        });

        PrintAllDB();
    }

    @Override
    public void onResume() {
        super.onResume();
        final int pos = 50;
        viewPager.postDelayed(new Runnable() { // https://stackoverflow.com/questions/19316729/android-viewpager-setcurrentitem-not-working-after-onresum
            @Override
            public void run() {
                viewPager.setCurrentItem(pos, false);
            }
        }, 100);
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // https://stackoverflow.com/questions/3053761/reload-activity-in-android
        finish();
        overridePendingTransition(0, 0);
        Intent intent = getIntent();

        if (viewPager.getAdapter() instanceof MonthPagerAdapter) {
            intent.putExtra("lastAction", "month");
        }
        else if (viewPager.getAdapter() instanceof WeekPagerAdapter) {
            intent.putExtra("lastAction", "week");
        }


        startActivity(getIntent());
        overridePendingTransition(0, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int pos = viewPager.getCurrentItem();
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(pos, false);
            }
        }, 100);

        switch (item.getItemId()) {
            case R.id.action_month:
                if (!(viewPager.getAdapter() instanceof MonthPagerAdapter)) {
                    vpAdapter = new MonthPagerAdapter(this, calendar);
                    viewPager.setAdapter(vpAdapter);
                }
                return true;
            case R.id.action_week:
                if (!(viewPager.getAdapter() instanceof WeekPagerAdapter)) {
                    vpAdapter = new WeekPagerAdapter(this, calendar, calendar);
                    viewPager.setAdapter(vpAdapter);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void PrintAllDB() {
        Cursor cursor = mDBHelper.getAllSchedulesBySQL();

        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append(cursor.getInt(0) + "\t");
            buffer.append(cursor.getString(1) + "\t");
            buffer.append(cursor.getString(2) + "\t");
            buffer.append(cursor.getString(3) + "\t");
            buffer.append(cursor.getString(4) + "\t");
            buffer.append(cursor.getString(5) + "\t");
            buffer.append(cursor.getString(6) + "\t");
            buffer.append(cursor.getString(7) + "\n");
        }

        Log.i("DBList", buffer.toString());
    }
}