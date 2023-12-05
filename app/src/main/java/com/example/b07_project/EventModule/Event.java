package com.example.b07_project.EventModule;


import java.io.Serializable;
import java.util.List;

public class Event implements Serializable {
    private String eventName;
    public String eventID;
    private String detail;
    private String location;
    private String time;
    private int limit;
    private List<Integer> attendStudentIDs;

    public Event(String eventName, String detail, String location, String time, int limit, List<Integer> attendStudentIDs) {
        this.eventName = eventName;
        this.detail = detail;
        this.location = location;
        this.time = time;
        this.limit = limit;
        this.attendStudentIDs = attendStudentIDs;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<Integer> getAttendStudentIDs() {
        return attendStudentIDs;
    }

    public void addAttendStudentIDs(int attendStudentID) {
        this.attendStudentIDs.add((Integer)attendStudentID);
    }
}
