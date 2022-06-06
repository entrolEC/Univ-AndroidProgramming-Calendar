package com.example.univ_androidprogramming_calendar;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class MonthPagerAdapter extends FragmentStateAdapter {
    Calendar calendar;

    public MonthPagerAdapter(FragmentActivity fragmentActivity, Calendar calendar) {
        super(fragmentActivity);
        this.calendar = calendar;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        MonthFragment monthFragment = new MonthFragment(position, calendar);

        return monthFragment;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
