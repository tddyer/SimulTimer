package com.example.simultimer;

/*

    ViewHolder class that stores contains layout elements for each
    timer in the RecyclerView in the MainActivity

*/

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TimerViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    TextView timeRemaining;

    TimerViewHolder(@NonNull View view) {
        super(view);
        title = view.findViewById(R.id.timerTitle);
        timeRemaining = view.findViewById(R.id.timeRemaining);
    }
}
