package com.example.simultimer;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {


    // class tag
    private static final String TAG = "MainActivity";

    // RecyclerView variables
    private RecyclerView timersRecycler;
    private TimersAdapter timersAdapter;

    // local variables
    Thread timerThread;
    TimerRunnable timer;
    private final ArrayList<TimerRunnable> activeTimers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating references to layout elements
        timersRecycler = findViewById(R.id.timersRecyclerView);

        // connected RecyclerView with the list of active timers
        timersAdapter = new TimersAdapter(activeTimers, this);
        timersRecycler.setAdapter(timersAdapter);
        timersRecycler.setLayoutManager(new LinearLayoutManager(this));

        // old data
//        timer = new TimerRunnable(10, "Timer 1");
//        timerThread = new Thread(timer);

        // creating test data
        for (int i = 5; i < 30; i+=5) {
            TimerRunnable t = new TimerRunnable(i, "Timer " + i);
            activeTimers.add(t);
        }

        timersAdapter.notifyDataSetChanged();

    }



    // timer management functions

    public void createTimer() {

    }

    public void startTimer(View v, int pos) {
        timerThread.start();
    }

    public void stopTimer(View v, int pos) {
        timerThread.interrupt();
        double rem = timer.getRemaining();
        timer = new TimerRunnable(rem, timer.getName());
        timerThread = new Thread(timer);
    }



    // timer creation menu functions

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



    // onClickListener interface methods

    @Override
    public void onClick(View view) {
        int pos = timersRecycler.getChildAdapterPosition(view);
        TimerRunnable tr = activeTimers.get(pos);
    }

    @Override
    public boolean onLongClick(View view) {
        int pos = timersRecycler.getChildAdapterPosition(view);
        // deleteTimer(pos);
        return true;
    }
}