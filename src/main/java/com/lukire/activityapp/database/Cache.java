package com.lukire.activityapp.database;

import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

@Async
public class Cache<K, T> extends HashMap<K, T>{

    public void put(final K key, T item, long expires) {
        super.put(key, item);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                remove(key);
            }
        }, expires - Time.now());
    }
}
