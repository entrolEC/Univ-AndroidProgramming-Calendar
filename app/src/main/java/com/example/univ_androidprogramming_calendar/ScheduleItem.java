package com.example.univ_androidprogramming_calendar;

public class ScheduleItem {
    private String title;
    private String content;
    private String date;
    private String start_time;
    private String end_time;
    private String location_latitude;
    private String location_longitude;

    public ScheduleItem(String title, String content, String date, String start_time, String end_time, String location_latitude, String location_longitude) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.start_time = start_time;
        this.end_time = end_time;
        this.location_latitude = location_latitude;
        this.location_longitude = location_longitude;
    }

    // Add Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(String location_latitude) {
        this.location_latitude = location_latitude;
    }

    public String getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(String location_longitude) {
        this.location_longitude = location_longitude;
    }
}
