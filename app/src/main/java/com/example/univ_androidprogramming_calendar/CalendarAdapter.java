package com.example.univ_androidprogramming_calendar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;

    public CalendarAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return 30;
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
            textView.setText(Integer.toString(i));
            textView.setGravity(Gravity.CENTER);
        } else {
            textView = (TextView) view;
        }

        return textView;
    }
}
