package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

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
//        GridView gridview = (GridView) findViewById(R.id.gridview);

        Intent intent = getIntent();
        int year = intent.getIntExtra("year", calendar.get(Calendar.YEAR));
        int month = intent.getIntExtra("month", calendar.get(Calendar.MONTH));
        calendar.set(year, month, calendar.get(Calendar.DATE));
        Log.d("MainActivity", "currenttime"+calendar.getTime());

        viewPager = findViewById(R.id.vpPager);
        vpAdapter = new MonthPagerAdapter(this, calendar);
        viewPager.setAdapter(vpAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (viewPager.getAdapter() instanceof MonthPagerAdapter) {
                    actionBarCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
                    actionBarCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - (50 - position));

                    actionBar = getSupportActionBar();
                    actionBar.setTitle(Integer.toString(actionBarCalendar.get(Calendar.YEAR)) + "년" + Integer.toString(actionBarCalendar.get(Calendar.MONTH) + 1) + "월");
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final int pos = 50;
        viewPager.postDelayed(new Runnable() {

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
                vpAdapter = new MonthPagerAdapter(this, calendar);
                viewPager.setAdapter(vpAdapter);
                return true;
            case R.id.action_week:
                vpAdapter = new PagerAdapter(this, calendar);
                viewPager.setAdapter(vpAdapter);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}