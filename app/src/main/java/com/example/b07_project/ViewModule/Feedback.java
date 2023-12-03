package com.example.b07_project.ViewModule;


import java.util.List;

public class Feedback {
    public String comment;

    public int rate;

    public Feedback(String comment, int rate){
        this.comment = comment;
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public int getRate() {
        return rate;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
