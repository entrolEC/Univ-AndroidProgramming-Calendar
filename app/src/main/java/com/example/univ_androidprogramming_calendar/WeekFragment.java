package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class WeekFragment extends Fragment {
    private Calendar calendar;
    private Calendar selectedCalendar;


    public WeekFragment() {
    }

    public WeekFragment(Calendar calendar, Calendar selectedCalendar) {
        this.calendar = calendar;
        this.selectedCalendar = selectedCalendar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek()); // 현재 주의 첫째 날
        //https://stackoverflow.com/questions/2937086/how-to-get-the-first-day-of-the-current-week-and-month
        Log.d("weekfragment", "onCreateView: "+calendar.getTime());
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        GridView dateGrid = (GridView) view.findViewById(R.id.date_gridview);
        WeekAdapter weekAdapter = new WeekAdapter(getActivity(), calendar, selectedCalendar);
        TimeAdapter timeAdapter = new TimeAdapter(getActivity(), weekAdapter);
        dateGrid.setAdapter(weekAdapter);

        GridView timeGrid = (GridView) view.findViewById(R.id.time_gridview);
        timeGrid.setAdapter(timeAdapter);

        TableLayout timeTable = view.findViewById(R.id.time_table);
        int screenHeight = ((Activity) getActivity()).getWindowManager().getDefaultDisplay().getHeight();
        for(int i = 0; i <= 24; i++) {
            TableRow tableRow = new TableRow(getActivity());
            TextView tv = new TextView(getActivity());
            tv.setText(Integer.toString(i));
            tv.setGravity(Gravity.CENTER);
            tableRow.addView(tv);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tv.getLayoutParams();
            params.height = screenHeight/12;

//            if(i==0) {
//                params.topMargin = -screenHeight/24;
//            }
            timeTable.addView(tableRow);
        }

        return view;
    }
}