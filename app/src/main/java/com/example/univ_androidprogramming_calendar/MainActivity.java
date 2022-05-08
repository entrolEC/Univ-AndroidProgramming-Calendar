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

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

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
//
//        gridview.setAdapter(new CalendarAdapter(this, calendar));
//
//        TextView title = findViewById(R.id.textView_title);
//        title.setText(String.format("%d년 %d월",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1));
//
//        Button prev = findViewById(R.id.button_prev);
//        Button next = findViewById(R.id.button_next);
//
//        prev.setOnClickListener(new MoveCalendar(calendar, -1));
//        next.setOnClickListener(new MoveCalendar(calendar, 1));

        ViewPager2 vpPager = findViewById(R.id.vpPager);
        vpPager.setCurrentItem(50, false);
//        vpPager.setCurrentItem(50);
        FragmentStateAdapter vpAdapter = new PagerAdapter(this, calendar);
        vpPager.setAdapter(vpAdapter);
    }

    class MoveCalendar implements View.OnClickListener {
        private Calendar calendar;
        private int value;

        MoveCalendar(Calendar calendar, int value) {
            this.calendar = calendar;
            this.value = value;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            calendar.add(Calendar.MONTH, value);
            intent.putExtra("year", calendar.get(Calendar.YEAR));
            intent.putExtra("month", calendar.get(Calendar.MONTH));
            startActivity(intent);
            finish();
        }
    }
}