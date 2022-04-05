package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022,1,5);
        gridview.setAdapter(new CalendarAdapter(this, calendar));

        TextView title = findViewById(R.id.textView_title);
        title.setText(String.format("%d.%d",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1));
    }
}