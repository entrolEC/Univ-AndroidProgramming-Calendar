package com.example.univ_androidprogramming_calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.ActionBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends BaseAdapter {
    private Context mContext;
    private Calendar calendar, tempCalendar = Calendar.getInstance();
    private int minDate = 0;
    private ListView lastSelected;

    private DBHelper mDBHelper;

    public CalendarAdapter(Context mContext, int position, Calendar calendar) {
        this.mContext = mContext;
        this.calendar = calendar;

        this.calendar.set(Calendar.YEAR, tempCalendar.get(Calendar.YEAR));
        this.calendar.set(Calendar.MONTH, tempCalendar.get(Calendar.MONTH) - (50 - position));
        Log.i("Position", Integer.toString(position));

        this.calendar.set(Calendar.DAY_OF_MONTH, 1);
        this.minDate = calendar.get(Calendar.DAY_OF_WEEK) - 1;

        mDBHelper = new DBHelper(mContext);
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
        ListView listView = new ListView(mContext);
        ListViewAdapter adapter = new ListViewAdapter();

        if (view == null) {
            if(0 <= i - minDate && i - minDate + 1 <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                ScheduleItem si = new ScheduleItem(Integer.toString(i - minDate + 1), "", "", "", "", "", "");
                adapter.addItem(si);

                Cursor cursor = mDBHelper.getScheduleBySQL(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + Integer.toString(i - minDate + 1));
                List<ScheduleItem> ListItems = new ArrayList<>();
                while (cursor.moveToNext()) {
                    si = new ScheduleItem(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7));
                    adapter.addItem(si);
                    ListItems.add(si);
                }

                listView.setAdapter(adapter);
                listView.setDivider(Drawable.createFromPath("#00000000"));

                // https://webnautes.tistory.com/1094
                List<String> ListTitles = new ArrayList<>();
                for (int j = 0; j < ListItems.size(); j++) {
                    ListTitles.add(ListItems.get(j).getTitle());
                }
                final CharSequence[] items =  ListTitles.toArray(new String[ ListTitles.size()]);

                ListView finalListView = listView;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView parent, View v, int position, long id) {
                        if (items.length == 1) {
                            ScheduleItem selectedItem = ListItems.get(0);

                            Intent intent = new Intent(mContext.getApplicationContext(), AddScheduleActivity.class);
                            intent.putExtra("title", selectedItem.getTitle());
                            intent.putExtra("content", selectedItem.getContent());
                            intent.putExtra("date", selectedItem.getDate());
                            intent.putExtra("start_time", selectedItem.getStart_time());
                            intent.putExtra("end_time", selectedItem.getEnd_time());
                            intent.putExtra("location_latitude", selectedItem.getLocation_latitude());
                            intent.putExtra("location_longitude", selectedItem.getLocation_longitude());

                            mContext.startActivity(intent);
                        }
                        else if (items.length > 1) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setTitle(calendar.get(Calendar.YEAR) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + Integer.toString(i - minDate + 1) + "일");
                            builder.setItems(items, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ScheduleItem selectedItem = ListItems.get(which);

                                    Intent intent = new Intent(mContext.getApplicationContext(), AddScheduleActivity.class);
                                    intent.putExtra("title", selectedItem.getTitle());
                                    intent.putExtra("content", selectedItem.getContent());
                                    intent.putExtra("date", selectedItem.getDate());
                                    intent.putExtra("start_time", selectedItem.getStart_time());
                                    intent.putExtra("end_time", selectedItem.getEnd_time());
                                    intent.putExtra("location_latitude", selectedItem.getLocation_latitude());
                                    intent.putExtra("location_longitude", selectedItem.getLocation_longitude());

                                    mContext.startActivity(intent);
                                }
                            });

                            builder.show();
                        }

                        if (lastSelected != null) {
                            lastSelected.setBackgroundResource(R.drawable.month_border);
                        }
                        lastSelected = finalListView;
                        finalListView.setBackgroundResource(R.drawable.month_selected_border);
                        calendar.set(Calendar.DATE, i - minDate + 1);

                    }
                });
            }
        } else {
            listView = (ListView) view;
        }

        // https://inma.tistory.com/72
        DisplayMetrics displayMetrics= new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        float density =  displayMetrics.density;

        int width = ((Activity) mContext).findViewById(R.id.vpPager).getWidth();
        int height = ((Activity) mContext).findViewById(R.id.vpPager).getHeight() - (int) (20 * density);

        listView.setLayoutParams(new GridView.LayoutParams(width / 7, height / 6));

        // https://stackoverflow.com/questions/12523005/how-set-background-drawable-programmatically-in-android
        listView.setBackgroundResource(R.drawable.month_border);

        return listView;
    }
}
