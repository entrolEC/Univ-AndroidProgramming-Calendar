package com.example.univ_androidprogramming_calendar;

import android.provider.BaseColumns;

public final class ScheduleContract {
    public static final String DB_NAME = "Schedule.db";
    public static final int DATABASE_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";

    private ScheduleContract() {}

    public static class Schedules implements BaseColumns {
        public static final String TABLE_NAME = "Schedules";

        // Need Columns
        /*
        title
        content
        date
        start time
        end time
        location_latitude
        location_longitude
         */
        public static final String KEY_TITLE = "Title";
        public static final String KEY_CONTENT = "Content";
        public static final String KEY_DATE = "Date";
        public static final String KEY_START_TIME = "Start_Time";
        public static final String KEY_END_TIME = "End_Time";
        public static final String KEY_LOCATION_LATITUDE = "Location_Latitude";
        public static final String KEY_LOCATION_LONGITUDE = "Location_Longitude";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                KEY_TITLE + TEXT_TYPE + COMMA_SEP +
                KEY_CONTENT + TEXT_TYPE + COMMA_SEP +
                KEY_DATE + TEXT_TYPE + COMMA_SEP +
                KEY_START_TIME + TEXT_TYPE + COMMA_SEP +
                KEY_END_TIME + TEXT_TYPE + COMMA_SEP +
                KEY_LOCATION_LATITUDE + TEXT_TYPE + COMMA_SEP +
                KEY_LOCATION_LONGITUDE + TEXT_TYPE + " )";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
