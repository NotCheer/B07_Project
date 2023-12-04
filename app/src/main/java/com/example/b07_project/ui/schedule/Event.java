package com.example.b07_project.ui.schedule;



import android.util.Pair;

import java.util.List;

public class Event {
    private String EventName, location, detail, time, ID;
    private List<Pair<Integer, String>> comment;
    private List<Integer> attendStudentID;


    private int limit;
    public Event(){
    }
    public Event(String eventName, String location, String detail, String time, int limit, String ID) {
        EventName = eventName;
        this.location = location;
        this.detail = detail;
        this.time = time;
        this.limit = limit;
        this.ID = ID;
    }
    public String getEventName() {
        return EventName;
    }

    public void setEventName(String name) {
        this.EventName = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void stID(String ID){this.ID = ID;}

    public String getID(){
        return ID;
    }

}
