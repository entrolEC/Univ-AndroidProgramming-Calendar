package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class WeekAdapter extends BaseAdapter {
    private Context mContent;

    public WeekAdapter(Context mContent) {
        this.mContent = mContent;
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
        textView = new TextView(mContent);

        textView.setGravity(Gravity.CENTER);
        int screenHeight = ((Activity) mContent).getWindowManager().getDefaultDisplay().getHeight();


        textView.setText("hi");
        textView.setLayoutParams(new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, screenHeight/15));


        return textView;
    }
}
