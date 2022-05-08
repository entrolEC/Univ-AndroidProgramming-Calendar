package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 vpPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        Calendar tempCalendar = Calendar.getInstance();

//        Bundle bundle = new Bundle();
//        bundle.putInt("year", calendar.get(Calendar.YEAR));
//        bundle.putInt("month", calendar.get(Calendar.MONTH));
//
//        MonthFragment monthfrag = new MonthFragment();
//        monthfrag.setArguments(bundle);
//        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.add(R.id.month_fragment, monthfrag);
//        fragmentTransaction.commit();

        vpPager = findViewById(R.id.vpPager);
        vpPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                Log.i("Month", Integer.toString(calendar.get(Calendar.MONTH) - (50 - position)));
                Log.i("Now", Integer.toString(position));
//                tempCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - (50 - position));
            }
        });
        FragmentStateAdapter vpAdapter = new MonthPagerAdapter(this, tempCalendar);
        vpPager.setAdapter(vpAdapter);

//        gridview.setAdapter(new CalendarAdapter(this, calendar));

//        TextView title = findViewById(R.id.textView_title);
//        title.setText(String.format("%d년 %d월",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1));
//
//        Button prev = findViewById(R.id.button_prev);
//        Button next = findViewById(R.id.button_next);
//
//        prev.setOnClickListener(new moveCalendar(calendar, -1));
//        next.setOnClickListener(new moveCalendar(calendar, 1));


    }

    @Override
    public void onResume() {
        super.onResume();
        final int pos = 50;
        vpPager.postDelayed(new Runnable() {

            @Override
            public void run() {
                vpPager.setCurrentItem(pos);
            }
        }, 100);
    }
}