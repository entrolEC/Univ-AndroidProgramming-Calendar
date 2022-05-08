package com.example.univ_androidprogramming_calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.time.Month;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();

        Intent intent = getIntent();
        int year = intent.getIntExtra("year", calendar.get(Calendar.YEAR));
        int month = intent.getIntExtra("month", calendar.get(Calendar.MONTH));
        calendar.set(year, month, calendar.get(Calendar.DATE));

        Bundle bundle = new Bundle();
        bundle.putInt("year", calendar.get(Calendar.YEAR));
        bundle.putInt("month", calendar.get(Calendar.MONTH));

        MonthFragment monthfrag = new MonthFragment();
        monthfrag.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.month_fragment, monthfrag);
        fragmentTransaction.commit();

//        gridview.setAdapter(new CalendarAdapter(this, calendar));

//        TextView title = findViewById(R.id.textView_title);
//        title.setText(String.format("%d년 %d월",calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1));
//
//        Button prev = findViewById(R.id.button_prev);
//        Button next = findViewById(R.id.button_next);
//
//        prev.setOnClickListener(new moveCalendar(calendar, -1));
//        next.setOnClickListener(new moveCalendar(calendar, 1));


    }

    class moveCalendar implements View.OnClickListener {
        private Calendar calendar;
        private int value;

        moveCalendar(Calendar calendar, int value) {
            this.calendar = calendar;
            this.value = value;
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            calendar.add(Calendar.MONTH, value);
            intent.putExtra("year", calendar.get(Calendar.YEAR));
            intent.putExtra("month", calendar.get(Calendar.MONTH));
            startActivity(intent);
            finish();
        }
    }
}