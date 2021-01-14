package com.example.simultimer;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.*;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnLongClickListener {


    // class tag
    private static final String TAG = "MainActivity";

    // RecyclerView variables
    public RecyclerView timersRecycler;
    public static TimersAdapter timersAdapter;

    // timer storage data structures
    public static ArrayList<TimerRunnable> timerRunnables = new ArrayList<>();
    public static HashMap<TimerRunnable, Thread> timerThreads = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creating references to layout elements
        timersRecycler = findViewById(R.id.timersRecyclerView);

        // connected RecyclerView with the list of active timers
        timersAdapter = new TimersAdapter(timerRunnables, this);
        timersRecycler.setAdapter(timersAdapter);
        timersRecycler.setLayoutManager(new LinearLayoutManager(this));

        // old data
//        timer = new TimerRunnable(10, "Timer 1");
//        timerThread = new Thread(timer);

        // creating test data
        for (int i = 5; i < 30; i+=5) {
            String name = "t" + i;
            TimerRunnable t = new TimerRunnable(i, name, timerRunnables.size(), this);
            timerRunnables.add(t);

            Thread temp = new Thread(t, name);
            timerThreads.put(t, temp);
        }

        timersAdapter.notifyDataSetChanged();

    }

    public void updateTimerUI(int position) {
        timersAdapter.notifyItemChanged(position, timerRunnables.get(position));
    }


    // alert dialogs

    public void createTimer() {

        LayoutInflater inflater = LayoutInflater.from(this);
        @SuppressLint("InflateParams")
        final View view = inflater.inflate(R.layout.add_timer_layout, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(view);
        builder.setTitle("New Timer");

        EditText title = view.findViewById(R.id.textTitle);
        EditText duration = view.findViewById(R.id.textDuration);

        builder.setPositiveButton("Create", (dialog, id) -> {

            String t = String.valueOf(title.getText());
            int d = Integer.parseInt(String.valueOf(duration.getText()));

            TimerRunnable temp = new TimerRunnable(d, t, timerRunnables.size(), this);
            timerRunnables.add(temp);

            Thread thread = new Thread(temp, t);
            timerThreads.put(temp, thread);

            timersAdapter.notifyDataSetChanged();

        });

        builder.setNegativeButton("Cancel", (dialog, id) -> {
            // do nothing
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void deleteTimer(final int pos) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setPositiveButton("OK", (dialog, id) -> {
            timerRunnables.remove(pos);
            timersAdapter.notifyDataSetChanged();
        });
        builder.setNegativeButton("CANCEL", (dialog, id) -> {
            // do nothing
        });
        builder.setMessage("Are you sure you want to delete this timer?");
        builder.setTitle("Delete Timer");

        AlertDialog dialog = builder.create();
        dialog.show();
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
        // TODO: edit a timer when it's clicked (navigate to a new EditActivity)
    }

    @Override
    public boolean onLongClick(View view) {
        int pos = timersRecycler.getChildAdapterPosition(view);
        deleteTimer(pos);
        return true;
    }
}