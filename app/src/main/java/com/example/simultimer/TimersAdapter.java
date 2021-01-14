package com.example.simultimer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TimersAdapter extends RecyclerView.Adapter<TimerViewHolder> {

    // local variables
    ArrayList<TimerRunnable> timers;
    MainActivity mainActivity;

    public TimersAdapter(ArrayList<TimerRunnable> timers, MainActivity mainActivity) {
        this.timers = timers;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public TimerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.timer_recycler_element, parent, false);

        // calls appropriate functions from main activity when a click/long click is detected
        itemView.setOnClickListener(mainActivity);
        itemView.setOnLongClickListener(mainActivity);

        return new TimerViewHolder(itemView, mainActivity);
    }

    @Override
    public void onBindViewHolder(@NonNull TimerViewHolder holder, int position) {
        TimerRunnable timer = timers.get(position);
        holder.title.setText(timer.getName());
        holder.timeRemaining.setText(Double.toString(timer.getRemaining()));
    }

    @Override
    public int getItemCount() {
        return timers.size();
    }
}
