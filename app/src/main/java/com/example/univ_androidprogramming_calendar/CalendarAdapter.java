package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private Calendar calendar;
    private int minDate = 0;
    private TextView lastSelected;

    public CalendarAdapter(Context mContext, int position) {
        this.mContext = mContext;
        this.calendar = Calendar.getInstance();

        this.calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        this.calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - (50 - position));
        Log.i("Position", Integer.toString(position));

        this.calendar.set(Calendar.DAY_OF_MONTH, 1);

        this.minDate = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    @Override
    public int getCount() {
        int cntdate = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + minDate;
        return 42 - cntdate + cntdate;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;

        if (view == null) {
            textView = new TextView(mContext);

            if(0 <= i - minDate && i - minDate + 1 <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                textView.setText(Integer.toString(i - minDate + 1));
            }
        } else {
            textView = (TextView) view;
        }

        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        // https://inma.tistory.com/72
        DisplayMetrics displayMetrics= new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        float density =  displayMetrics.density;

        int width = ((Activity) mContext).findViewById(R.id.vpPager).getWidth();
        int height = ((Activity) mContext).findViewById(R.id.vpPager).getHeight() - (int) (20 * density);

        textView.setLayoutParams(new GridView.LayoutParams(width / 7, height / 6));

        // https://stackoverflow.com/questions/12523005/how-set-background-drawable-programmatically-in-android
        textView.setBackgroundResource(R.drawable.month_border);

        if (0 <= i - minDate && i - minDate + 1 <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (lastSelected != null) {
                        lastSelected.setBackgroundResource(R.drawable.month_border);
                    }
                    lastSelected = textView;
                    textView.setBackgroundColor(Color.CYAN);

                    String msg = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + Integer.toString(i - minDate + 1);
                    Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

        return textView;
    }
}
