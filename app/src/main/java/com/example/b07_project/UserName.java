package com.example.b07_project;

public class UserName {
    private static UserName instance = null;
    public String name;
    protected UserName() {

    }

    public static UserName getInstance() {
        if (instance ==null) {
            instance = new UserName();
        }
        return instance;
    }

    public void setName(String name) {
        this.name = name;
    }
}
