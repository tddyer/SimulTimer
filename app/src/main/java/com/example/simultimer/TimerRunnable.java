package com.example.simultimer;


/*

    Runnable class used to create a single timer instance

*/

import android.util.Log;

public class TimerRunnable implements Runnable {

    // class tag
    private static final String TAG = "TimerRunnable";

    // state flags
    public static final int STOPPED = 0;
    public static final int RUNNING = 1;

    // timer variables
    private final double duration;
    private final String name;
    private double remaining;
    private int state;


    // constructor
    public TimerRunnable(double duration, String name) {
        this.duration = duration;
        this.name = name;
        this.remaining = duration;
        this.state = STOPPED;
    }


    // Runnable interface's run method implements the timer functionality
    @Override
    public void run() {

        Log.d(TAG, "run: Starting run method for timer " + this.name);

        try {
            while (remaining > 0){
                Log.d(TAG, "Time remaining: " + this.remaining);
                Thread.sleep(1000);
                this.remaining--;
            }
        } catch (Exception e) {
            this.state = STOPPED;
            Log.d(TAG, "run: timer " + this.name + " was stopped.\n   Time remaining upon interruption: " + this.remaining);
        }
    }

    // getters + setters
    public double getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public double getRemaining() {
        return remaining;
    }

    public void setRemaining(double remaining) {
        this.remaining = remaining;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
