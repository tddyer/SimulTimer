package com.example.simultimer;


/*

    Runnable class used to create a single timer. Timers have a name
    that they are identified by and a duration that specifies how long
    of a timer the user is requesting.

*/

import android.util.Log;

public class TimerRunnable implements Runnable {

    // reference to MainActivity for updating the timer UIs
    MainActivity mainActivity;

    // class tag
    private static final String TAG = "TimerRunnable";

    // state flags
    public static final int STOPPED = 0;
    public static final int RUNNING = 1;
    public static final int COMPLETED = 2;

    // timer variables
    private double duration;
    private String name;
    private double remaining;
    private int state;
    public int position;


    // constructor
    public TimerRunnable(double duration, String name, int position, MainActivity mainActivity) {
        this.duration = duration;
        this.name = name;
        this.position = position;
        this.remaining = duration;
        this.state = STOPPED;
        this.mainActivity = mainActivity;
    }



    // Runnable interface's run method implements the timer functionality
    @Override
    public void run() {

        Log.d(TAG, "run: Starting run method for timer " + this.name);

        this.state = RUNNING;

        try {
            while (remaining > 0 && this.state == RUNNING){

                // wait one second
                Thread.sleep(1000);

                // update time remaining and update the UI to reflect the new time remaining on the timer
                this.remaining--;
                mainActivity.runOnUiThread( () -> {mainActivity.updateTimerUI(this.position);});
            }

            // marking timer as completed
            this.state = COMPLETED;

        } catch (Exception e) {
            // if timer was interrupted, set the state to STOPPED
            this.state = STOPPED;
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
