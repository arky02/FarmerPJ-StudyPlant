package com.example.studyplant;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
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
                        SharedPreferences sf = getSharedPreferences("sFile",MODE_PRIVATE);
                        SharedPreferences.Editor editor = sf.edit();
                        editor.putInt("time_current", 0);
                        editor.commit();
                    }
                }
            }
        };
        thread.start();
        return START_STICKY;
    }
}
