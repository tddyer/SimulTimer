package com.example.simultimer;

/*

    ViewHolder class that stores contains layout elements for each
    timer in the RecyclerView in the MainActivity

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
        title = view.findViewById(R.id.timerTitle);
        timeRemaining = view.findViewById(R.id.timeRemaining);
        startButton = view.findViewById(R.id.startTimer);
        pauseButton = view.findViewById(R.id.pauseTimer);
        startButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        TimerRunnable selectedTimer = MainActivity.timerRunnables.get(getAdapterPosition());
        Thread selectedTimerThread = MainActivity.timerThreads.get(selectedTimer);

        if (view.getId() == startButton.getId()) {
            Log.d("VIEWHOLDER", "onClick: starting " + selectedTimerThread.getName());
        } else if (view.getId() == pauseButton.getId()) {
            Log.d("VIEWHOLDER", "onClick: pausing " + selectedTimerThread.getName());
        }
    }
}
