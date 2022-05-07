package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class TimeAdapter extends BaseAdapter {
    private Context mContent;

    public TimeAdapter(Context mContent) {
        this.mContent = mContent;
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
        textView = new TextView(mContent);
        textView.setText("hi");
        textView.setGravity(Gravity.CENTER);
        int screenHeight = ((Activity) mContent).getWindowManager().getDefaultDisplay().getHeight();
        int screenWidth = ((Activity) mContent).getWindowManager().getDefaultDisplay().getWidth();
        textView.setLayoutParams(new GridView.LayoutParams(screenWidth/7, screenHeight/15+1));
        textView.setBackgroundResource(R.drawable.border);
        return textView;
    }
}
