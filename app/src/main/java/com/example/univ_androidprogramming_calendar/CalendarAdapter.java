package com.example.univ_androidprogramming_calendar;

import android.content.Context;
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
    private int minDate = 1;

    public CalendarAdapter(Context mContext, Calendar calendar) {
        this.mContext = mContext;
        this.calendar = calendar;
        minDate = calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    @Override
    public int getCount() {
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)+minDate-1;
    }

    @Override
    public Object getItem(int i) {
        return i+1;
    }

    @Override
    public long getItemId(int i) {
        return i+1;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView;
        if (view == null) {

            textView = new TextView(mContext);
            if(minDate <= i+1) {
                textView.setText(Integer.toString(i+2 - minDate));
            }

            textView.setGravity(Gravity.CENTER);
            textView.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 1f));
        } else {
            textView = (TextView) view;
        }

        if (i - 1 > 0) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + Integer.toString(i - 1);
                    Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                }
            });
        }

        return textView;
    }
}
