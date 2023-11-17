package com.example.b07_project.AnnouncementModule;

import java.text.DateFormat;

public class Announcement {
	private String title;
    private String content;
    private DateFormat date;

    //Getters and Setter
    public Announcement(String title, String content, DateFormat date) {
    	this.title=title;
    	this.content=content;
    	this.date=date;
    }

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

	public DateFormat getDate() {
		return date;
	}

	public void setDate(DateFormat date) {
		this.date = date;
	}
}
