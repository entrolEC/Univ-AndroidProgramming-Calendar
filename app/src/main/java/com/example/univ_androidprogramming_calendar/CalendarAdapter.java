package com.example.univ_androidprogramming_calendar;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private Calendar calendar;
    private int minDate = 0;

    public CalendarAdapter(Context mContext, Calendar calendar) {
        this.mContext = mContext;
        this.calendar = calendar;
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        minDate = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        Log.e("log",Integer.toString(minDate));
    }

    @Override
    public int getCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH) + minDate;
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
            if(0 <= i - minDate) {
                textView.setText(Integer.toString(i - minDate + 1));
            }

            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        } else {
            textView = (TextView) view;
        }


        if (i - minDate + 1 > 0) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + Integer.toString(i - minDate + 1);
                    Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }


        return textView;
    }
}
