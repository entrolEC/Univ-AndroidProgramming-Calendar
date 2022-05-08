package com.example.univ_androidprogramming_calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class PagerAdapter extends FragmentStateAdapter {
    Calendar calendar;

    public PagerAdapter(FragmentActivity fa, Calendar calendar) {
        super(fa);
        this.calendar = calendar;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        temp.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        temp.set(Calendar.DATE, calendar.get(Calendar.DATE));
        temp.add(Calendar.DATE, 7*position);
        WeekFragment fragobj = new WeekFragment(temp);
        return fragobj;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
