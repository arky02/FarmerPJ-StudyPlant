package com.example.studyplant;

import android.app.Application;

public class studyTimeData extends Application {
    private int time = 0;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time += time;
    }

    public void resetTime() {
        this.time = 0;
    }

    public void backTime(int time) {
        this.time -= time;
    }
}
