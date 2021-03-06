package com.example.univ_androidprogramming_calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

// https://recipes4dev.tistory.com/43
public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<>() ;

    public ListViewAdapter() { }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView1) ;
        ListViewItem listViewItem = listViewItemList.get(position);

        titleTextView.setText(listViewItem.getTitle());

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position) ;
    }

    public void addItem(ScheduleItem si) {
        ListViewItem item = new ListViewItem();

        item.setTitle(si.getTitle());

        listViewItemList.add(item);
    }
}
