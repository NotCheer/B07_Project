package com.example.b07_project.AnnouncementModule;

import java.util.Date;

public class Announcement {
	private String title;
    private String content;
    private Date date;

    //Getters and Setter
    public Announcement(String title, String content, Date date) {
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
