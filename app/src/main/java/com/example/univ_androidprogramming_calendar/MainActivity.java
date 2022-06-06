package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
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
    Calendar calendar = Calendar.getInstance(), actionBarCalendar = Calendar.getInstance();
    FragmentStateAdapter vpAdapter;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);

        viewPager = findViewById(R.id.vpPager);
        vpAdapter = new MonthPagerAdapter(this);
        viewPager.setAdapter(vpAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.i("Position", Integer.toString(position));
                if (viewPager.getAdapter() instanceof MonthPagerAdapter) {
                    actionBarCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                    actionBarCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - (50 - (position == 0 ? 50 : position)));

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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), AddScheduleActivity.class);
               startActivity(intent);
            }
        });

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
                    vpAdapter = new MonthPagerAdapter(this);
                    viewPager.setAdapter(vpAdapter);
                }
                return true;
            case R.id.action_week:
                if (!(viewPager.getAdapter() instanceof WeekPagerAdapter)) {
                    vpAdapter = new WeekPagerAdapter(this, calendar);
                    viewPager.setAdapter(vpAdapter);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}