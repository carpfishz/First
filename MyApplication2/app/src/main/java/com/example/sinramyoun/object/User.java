package com.example.sinramyoun.object;

public class User {
    String room;
    String w_time;
    String s_time;
    String e_time;
    String id;
    String r_time;

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getWtime() {
        return w_time;
    }

    public void setWtime(String w_time) {
        this.w_time = w_time;
    }

    public String getStime() {
        return s_time;
    }

    public void setStime(String s_time) {
        this.s_time = s_time;
    }

    public String getEtime() {
        return e_time;
    }

    public void setEtime(String e_time) {
        this.e_time = e_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRtime() {
        return r_time;
    }

    public void setRtime(String r_time) {
        this.r_time = r_time;
    }

    public User(String room, String w_time, String s_time, String e_time, String id, String r_time) {
        super();
        this.room = room;
        this.w_time = w_time;
        this.s_time = s_time;
        this.e_time = e_time;
        this.id = id;
        this.r_time = r_time;
    }

}

