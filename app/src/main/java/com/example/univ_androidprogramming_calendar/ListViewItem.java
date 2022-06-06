package com.example.univ_androidprogramming_calendar;

public class ListViewItem {
    private String title;
    private String content;
    private String date;
    private String start_time;
    private String end_time;
    private String location_latitude;
    private String location_longitude;


    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getContent() {
        return content;
    }
}