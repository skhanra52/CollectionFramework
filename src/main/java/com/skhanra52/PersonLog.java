package com.skhanra52;

public class PersonLog {
    private String userId;
    private String action;

    public PersonLog(String userId, String action){
        this.userId = userId;
        this.action = action;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
