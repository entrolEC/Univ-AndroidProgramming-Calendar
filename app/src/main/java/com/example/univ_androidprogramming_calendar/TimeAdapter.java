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
import android.widget.Toast;

public class TimeAdapter extends BaseAdapter {
    private Context mContext;
    private int selectedPosition = -1;
    private WeekAdapter weekAdapter;

    public TimeAdapter(Context mContent, WeekAdapter weekAdapter) {
        this.mContext = mContent;
        this.weekAdapter = weekAdapter;
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
        TextView textView;
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        int screenHeight = ((Activity) mContext).getWindowManager().getDefaultDisplay().getHeight();
        int screenWidth = ((Activity) mContext).getWindowManager().getDefaultDisplay().getWidth();
        textView.setLayoutParams(new GridView.LayoutParams(screenWidth/7, screenHeight/15+1));
        textView.setBackgroundResource(R.drawable.border);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = position;
                textView.setBackgroundColor(Color.CYAN);

                weekAdapter.setSelected(position%7);
                weekAdapter.notifyDataSetChanged();
                TimeAdapter.this.notifyDataSetChanged();
                Log.d("adapter listener", String.valueOf(selectedPosition));
            }
        });

        if(selectedPosition == position) {
            textView.setBackgroundColor(Color.CYAN);
        }

        return textView;
    }
}
