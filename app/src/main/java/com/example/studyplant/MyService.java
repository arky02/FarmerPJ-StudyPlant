package com.example.studyplant;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.util.Calendar;

public class MyService extends Service {

    int DAY_MILLIS = 86400000;
    long mday = 0;
    Thread thread;
    private Calendar calendar = Calendar.getInstance();
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mday = calendar.getTimeInMillis();
        thread = new Thread() {
            public void run() {
                while(true) {
                    try {
                        thread.sleep(240000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mday += 240000;

                    if(mday >= DAY_MILLIS) {
                        studyTimeData studytime = (studyTimeData)getApplicationContext();
                        studytime.resetTime();
                    }
                }
            }
        };
        thread.start();
        return START_STICKY;
    }
}
