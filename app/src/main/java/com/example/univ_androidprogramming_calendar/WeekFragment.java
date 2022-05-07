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

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeekFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeekFragment newInstance(String param1, String param2) {
        WeekFragment fragment = new WeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Calendar calendar = Calendar.getInstance();

        int year = getArguments().getInt("year");
        int month = getArguments().getInt("month");
        int day = getArguments().getInt("day");
        calendar.set(year, month, day);
        Log.d("weekfragment", "onCreateView: "+calendar.getTime());
        View view = inflater.inflate(R.layout.fragment_week, container, false);
        GridView dateGrid = (GridView) view.findViewById(R.id.date_gridview);
        dateGrid.setAdapter(new WeekAdapter(getActivity()));

        GridView timeGrid = (GridView) view.findViewById(R.id.time_gridview);
        timeGrid.setAdapter(new TimeAdapter(getActivity()));

        TableLayout timeTable = view.findViewById(R.id.time_table);
        int screenHeight = ((Activity) getActivity()).getWindowManager().getDefaultDisplay().getHeight();
        for(int i = 1; i < 24; i++) {
            TableRow tableRow = new TableRow(getActivity());
            TextView tv = new TextView(getActivity());
            tv.setText(Integer.toString(i));
            tv.setGravity(Gravity.CENTER);
            tableRow.addView(tv);
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) tv.getLayoutParams();
            params.topMargin = screenHeight/24;
            timeTable.addView(tableRow);
        }


        //calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        // get start of this week in milliseconds

        return view;
    }
}