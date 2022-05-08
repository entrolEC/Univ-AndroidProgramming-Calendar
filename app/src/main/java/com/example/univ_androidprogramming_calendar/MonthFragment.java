package com.example.univ_androidprogramming_calendar;

import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class MonthFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Calendar calendar = Calendar.getInstance();

        View view = inflater.inflate(R.layout.month, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        gridView.setAdapter(new CalendarAdapter(getActivity(), calendar));

//        Display display = getActivity().getWindowManager().getDefaultDisplay();
//        Point size = new Point();
//        display.getRealSize(size);
//        int height = size.y;
//
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
//        float density = outMetrics.density;
//
//        int dp = (int) ((height / density));
//
//        gridView.setVerticalSpacing(dp / 6);

        return view;
    }
}