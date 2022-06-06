package com.example.univ_androidprogramming_calendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.Calendar;

public class WeekPagerAdapter extends FragmentStateAdapter {
    Calendar calendar;
    Calendar selectedCalendar;


    public WeekPagerAdapter(FragmentActivity fa, Calendar calendar, Calendar actionbarCalendar) {
        super(fa);
        this.calendar = calendar;
        this.selectedCalendar = actionbarCalendar;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        temp.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        temp.set(Calendar.DATE, calendar.get(Calendar.DATE));
        temp.add(Calendar.DATE, 7 * (position - 50));
        WeekFragment fragobj = new WeekFragment(temp, selectedCalendar);
        return fragobj;
    }

    @Override
    public int getItemCount() {
        return 100;
    }
}
