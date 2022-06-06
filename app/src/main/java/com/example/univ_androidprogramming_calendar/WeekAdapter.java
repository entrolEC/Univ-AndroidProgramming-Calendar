package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

public class WeekAdapter extends BaseAdapter {
    private Context mContent;
    private Calendar calendar;
    private Calendar selectedCalendar;
    private int selected;

    public WeekAdapter(Context mContent, Calendar calendar, Calendar selectedCalendar) {
        this.mContent = mContent;
        this.calendar = calendar;
        this.selectedCalendar = selectedCalendar;
        selected = -1;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView;
        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        temp.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        temp.set(Calendar.DATE, calendar.get(Calendar.DATE));

        textView = new TextView(mContent);

        textView.setGravity(Gravity.CENTER);
        int screenHeight = ((Activity) mContent).getWindowManager().getDefaultDisplay().getHeight();

        temp.add(Calendar.DAY_OF_YEAR, position);
        Log.d("adapter", String.valueOf(temp.get(Calendar.DATE)));
        textView.setText(String.valueOf(temp.get(Calendar.DATE)));
        textView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, screenHeight/15));

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected = position;
                WeekAdapter.this.notifyDataSetChanged();
            }
        });

        if(selected == position) {
            selectedCalendar.set(Calendar.YEAR, temp.get(Calendar.YEAR));
            selectedCalendar.set(Calendar.MONTH, temp.get(Calendar.MONTH));
            selectedCalendar.set(Calendar.DATE, temp.get(Calendar.DATE));
            Log.i("check actionbarCalendar", String.format("%d/%d/%d", selectedCalendar.get(Calendar.YEAR), selectedCalendar.get(Calendar.MONTH), selectedCalendar.get(Calendar.DATE)));
            textView.setBackgroundColor(Color.CYAN);
        }

        return textView;
    }

    public void setSelected(int selected) {
        selectedCalendar.set(Calendar.HOUR, selected/7);
        Log.i("check hour", String.format("%d",selectedCalendar.get(Calendar.HOUR)));
        this.selected = selected%7;
    }
}
