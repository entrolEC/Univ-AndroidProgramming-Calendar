package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class MonthFragment extends Fragment {
    private int position;

    public MonthFragment(int position) {
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month, container, false);

        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new CalendarAdapter(getActivity(), position));

        return view;
    }
}