package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
        Calendar calendar = Calendar.getInstance();
//        GridView gridview = (GridView) findViewById(R.id.gridview);

        Intent intent = getIntent();
        int year = intent.getIntExtra("year", calendar.get(Calendar.YEAR));
        int month = intent.getIntExtra("month", calendar.get(Calendar.MONTH));
        calendar.set(year, month, calendar.get(Calendar.DATE));
        Log.d("MainActivity", "currenttime"+calendar.getTime());

        viewPager = findViewById(R.id.vpPager);
        FragmentStateAdapter vpAdapter = new PagerAdapter(this, calendar);
        viewPager.setAdapter(vpAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        final int pos = 50;
        viewPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                viewPager.setCurrentItem(pos);
            }
        }, 100);
    }
}