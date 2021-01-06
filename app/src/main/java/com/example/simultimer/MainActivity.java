package com.example.simultimer;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {


    Thread timerThread;
    TimerRunnable timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timer = new TimerRunnable(10, "Timer 1");
        timerThread = new Thread(timer);
    }



    // timer management functions

    public void createTimer() {

    }

    public void startTimer(View v) {
        timerThread.start();
    }

    public void stopTimer(View v) {
        timerThread.interrupt();
        double rem = timer.getRemaining();
        timer = new TimerRunnable(rem, timer.getName());
        timerThread = new Thread(timer);
    }



    // create timer menu functions

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menuCreateTimer) {
            createTimer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}