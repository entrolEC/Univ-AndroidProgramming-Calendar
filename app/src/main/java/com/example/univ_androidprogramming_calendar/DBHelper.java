package com.example.univ_androidprogramming_calendar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    final static String TAG = "ScheduleItem";

    public DBHelper(Context context) {
        super(context, ScheduleContract.DB_NAME, null, ScheduleContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG,getClass().getName()+".onCreate()");
        db.execSQL(ScheduleContract.Schedules.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i(TAG,getClass().getName() +".onUpgrade()");
        db.execSQL(ScheduleContract.Schedules.DELETE_TABLE);
        onCreate(db);
    }

    public void insertScheduleBySQL(ScheduleItem si) {
        try {
            String sql = String.format (
                    "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s) VALUES (NULL, '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules._ID,
                    ScheduleContract.Schedules.KEY_TITLE,
                    ScheduleContract.Schedules.KEY_CONTENT,
                    ScheduleContract.Schedules.KEY_DATE,
                    ScheduleContract.Schedules.KEY_START_TIME,
                    ScheduleContract.Schedules.KEY_END_TIME,
                    ScheduleContract.Schedules.KEY_LOCATION_LATITUDE,
                    ScheduleContract.Schedules.KEY_LOCATION_LONGITUDE,
                    si.getTitle(), si.getContent(), si.getDate(), si.getStart_time(), si.getEnd_time(), si.getLocation_latitude(), si.getLocation_longitude());

            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in inserting recodes");
        }
    }

    public Cursor getAllSchedulesBySQL() {
        String sql = "Select * FROM " + ScheduleContract.Schedules.TABLE_NAME;
        return getReadableDatabase().rawQuery(sql,null);
    }

    // 날짜로 데이터 가져오기
    public Cursor getScheduleWithDateBySQL(String date) {
        String sql = String.format (
                "SELECT * FROM %s WHERE %s = '%s'",
                ScheduleContract.Schedules.TABLE_NAME,
                ScheduleContract.Schedules.KEY_DATE,
                date);
        return getReadableDatabase().rawQuery(sql,null);
    }

    // 날짜와 시간으로 데이터 가져오기
    public Cursor getScheduleWithDateAndTimeBySQL(String date, String time) {
        String sql = String.format (
                "SELECT * FROM %s WHERE %s = '%s' AND %s LIKE '%s%%'",
                ScheduleContract.Schedules.TABLE_NAME,
                ScheduleContract.Schedules.KEY_DATE, date,
                ScheduleContract.Schedules.KEY_START_TIME, time);
        Log.i("DBList", sql);
        return getReadableDatabase().rawQuery(sql,null);
    }

    public void deleteScheduleBySQL(String _id) {
        try {
            String sql = String.format (
                    "DELETE FROM %s WHERE %s = %s",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules._ID,
                    _id);
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in deleting recodes");
        }
    }

    public void updateScheduleBySQL(String _id, String title, String content, String date, String start_time, String end_time, String location_latitude, String location_longitude) {
        try {
            String sql = String.format (
                    "UPDATE  %s SET %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s', %s = '%s' WHERE %s = %s",
                    ScheduleContract.Schedules.TABLE_NAME,
                    ScheduleContract.Schedules.KEY_TITLE, title,
                    ScheduleContract.Schedules.KEY_CONTENT, content,
                    ScheduleContract.Schedules.KEY_DATE, date,
                    ScheduleContract.Schedules.KEY_START_TIME, start_time,
                    ScheduleContract.Schedules.KEY_END_TIME, end_time,
                    ScheduleContract.Schedules.KEY_LOCATION_LATITUDE, location_latitude,
                    ScheduleContract.Schedules.KEY_LOCATION_LONGITUDE, location_longitude,
                    ScheduleContract.Schedules._ID, _id) ;
            getWritableDatabase().execSQL(sql);
        } catch (SQLException e) {
            Log.e(TAG,"Error in updating recodes");
        }
    }

    public long insertScheduleByMethod(String title, String content, String date, String start_time, String end_time, String location_latitude, String location_longitude) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ScheduleContract.Schedules.KEY_TITLE, title);
        values.put(ScheduleContract.Schedules.KEY_CONTENT, content);
        values.put(ScheduleContract.Schedules.KEY_DATE, date);
        values.put(ScheduleContract.Schedules.KEY_START_TIME, start_time);
        values.put(ScheduleContract.Schedules.KEY_END_TIME, end_time);
        values.put(ScheduleContract.Schedules.KEY_LOCATION_LATITUDE, location_latitude);
        values.put(ScheduleContract.Schedules.KEY_LOCATION_LONGITUDE, location_longitude);

        return db.insert(ScheduleContract.Schedules.TABLE_NAME,null,values);
    }

    public Cursor getAllScheduleByMethod() {
        SQLiteDatabase db = getReadableDatabase();
        return db.query(ScheduleContract.Schedules.TABLE_NAME,null,null,null,null,null,null);
    }

    public long deleteScheduleByMethod(String _id) {
        SQLiteDatabase db = getWritableDatabase();

        String whereClause = ScheduleContract.Schedules._ID +" = ?";
        String[] whereArgs ={_id};
        return db.delete(ScheduleContract.Schedules.TABLE_NAME, whereClause, whereArgs);
    }

    public long updateScheduleByMethod(String _id, String title, String content, String date, String start_time, String end_time, String location_latitude, String location_longitude) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ScheduleContract.Schedules.KEY_TITLE, title);
        values.put(ScheduleContract.Schedules.KEY_CONTENT, content);
        values.put(ScheduleContract.Schedules.KEY_DATE, date);
        values.put(ScheduleContract.Schedules.KEY_START_TIME, start_time);
        values.put(ScheduleContract.Schedules.KEY_END_TIME, end_time);
        values.put(ScheduleContract.Schedules.KEY_LOCATION_LATITUDE, location_latitude);
        values.put(ScheduleContract.Schedules.KEY_LOCATION_LONGITUDE, location_longitude);

        String whereClause = ScheduleContract.Schedules._ID +" = ?";
        String[] whereArgs ={_id};

        return db.update(ScheduleContract.Schedules.TABLE_NAME, values, whereClause, whereArgs);
    }
}
