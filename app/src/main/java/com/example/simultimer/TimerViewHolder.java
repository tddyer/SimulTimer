package com.example.simultimer;

/*

    ViewHolder class that stores contains layout elements for each
    timer in the RecyclerView in the MainActivity. Also handles
    timer starting/pausing when the corresponding button is selected.

*/

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    TextView title, timeRemaining;
    Button startButton, pauseButton;

    TimerViewHolder(@NonNull View view) {
        super(view);

        // create references to layout objects from timer_recycler_element.xml layout
        title = view.findViewById(R.id.timerTitle);
        timeRemaining = view.findViewById(R.id.timeRemaining);
        startButton = view.findViewById(R.id.startTimer);
        pauseButton = view.findViewById(R.id.pauseTimer);

        // set on-click listeners for start/pause buttons
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
    }


    // on-click method that is called whenever a timers start/pause button is pressed
    @Override
    public void onClick(View view) {

        // obtain references to the selected timer and its execution thread
        TimerRunnable selectedTimer = MainActivity.timerRunnables.get(getAdapterPosition());
        Thread selectedTimerThread = MainActivity.timerThreads.get(selectedTimer);

        if (selectedTimerThread != null) {
            if (view.getId() == startButton.getId()) { // start button pressed
                // start the selected timer's thread
                selectedTimerThread.start();
            } else if (view.getId() == pauseButton.getId()) { // pause button pressed

                // interrupt the timer's thread execution and make note of remaining time
                selectedTimerThread.interrupt();
                double rem = selectedTimer.getRemaining();

                // create new runnable + thread objects starting using the remaining time on the old thread
                TimerRunnable newTimer = new TimerRunnable(rem, selectedTimer.getName());
                Thread newThread = new Thread(newTimer);

                // update the timer storage data structures on MainActivity with new objects
                MainActivity.timerRunnables.set(getAdapterPosition(), newTimer);
                MainActivity.timerThreads.remove(selectedTimer);
                MainActivity.timerThreads.put(newTimer, newThread);

            }
        }
    }
}
