package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class TimeAdapter extends BaseAdapter {
    private Context mContext;
    private int selectedPosition = -1;
    private WeekAdapter weekAdapter;
    private DBHelper mDBHelper;
    private Calendar calendar; // 주의 첫날인 일요일 기준

    public TimeAdapter(Context mContent, WeekAdapter weekAdapter, Calendar calendar) {
        this.mContext = mContent;
        this.weekAdapter = weekAdapter;
        this.calendar = calendar;
        mDBHelper = new DBHelper(mContext);
    }

    @Override
    public int getCount() {
        return 168;
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
        LinearLayout linearLayout = new LinearLayout(mContext);
        TextView textView;
        textView = new TextView(mContext);
        linearLayout.setGravity(Gravity.CENTER);
        int screenHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        int screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        linearLayout.setLayoutParams(new GridView.LayoutParams(screenWidth/7, screenHeight/12));
        linearLayout.setBackgroundResource(R.drawable.border);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                linearLayout.setBackgroundColor(Color.CYAN);

                weekAdapter.setSelected(position);
                weekAdapter.notifyDataSetChanged();
                TimeAdapter.this.notifyDataSetChanged();
                Log.d("adapter listener", String.valueOf(selectedPosition));
                String msg = String.format("position:%d", position);
                Toast.makeText(mContext.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

        if(selectedPosition == position) {
            linearLayout.setBackgroundColor(Color.CYAN);
        }

        Calendar temp = Calendar.getInstance();
        temp.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        temp.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        temp.set(Calendar.DATE, calendar.get(Calendar.DATE));
        temp.add(Calendar.DATE, position % 7);

        Cursor cursor = mDBHelper.getScheduleWithDateAndTimeBySQL(temp.get(Calendar.YEAR) + "-" + (temp.get(Calendar.MONTH) + 1) + "-" + temp.get(Calendar.DATE), String.valueOf(position / 7));

        if (cursor.moveToNext()) {
            ScheduleItem si = new ScheduleItem(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
            textView.setText(si.getTitle());
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(mContext.getApplicationContext(), AddScheduleActivity.class);
                    intent.putExtra("id", si.getId());
                    intent.putExtra("title", si.getTitle());
                    intent.putExtra("content", si.getContent());
                    intent.putExtra("date", si.getDate());
                    intent.putExtra("start_time", si.getStart_time());
                    intent.putExtra("end_time", si.getEnd_time());
                    intent.putExtra("location_latitude", si.getLocation_latitude());
                    intent.putExtra("location_longitude", si.getLocation_longitude());

                    mContext.startActivity(intent);
                }
            });
        }

        linearLayout.addView(textView);

        return linearLayout;
    }
}
