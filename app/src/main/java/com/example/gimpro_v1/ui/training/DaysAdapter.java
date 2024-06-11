package com.example.gimpro_v1.ui.training;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.gimpro_v1.R;

import java.util.Calendar;
import java.util.List;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DayViewHolder> {
    private List<String> daysOfWeek;
    private List<Integer> daysOfMonth;
    private int currentDay;
    private Context context;

    public DaysAdapter(Context context, List<String> daysOfWeek, List<Integer> daysOfMonth, int currentDay) {
        this.context = context;
        this.daysOfWeek = daysOfWeek;
        this.daysOfMonth = daysOfMonth;
        this.currentDay = currentDay;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_day, parent, false);
        return new DayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int position) {
        String dayOfWeek = daysOfWeek.get(position);
        int dayOfMonth = daysOfMonth.get(position);

        holder.dayOfWeekTextView.setText(dayOfWeek);
        holder.dayOfMonthTextView.setText(String.valueOf(dayOfMonth));

        if (dayOfMonth == currentDay) {
            holder.dayOfMonthTextView.setBackgroundResource(R.drawable.circle_background);
            holder.dayOfMonthTextView.setTextColor(Color.WHITE);
            holder.itemView.setClickable(true);
            holder.itemView.setOnClickListener(v -> {
                // Handle click on current day
            });
        } else {
            holder.dayOfMonthTextView.setBackgroundResource(android.R.color.transparent);
            holder.dayOfMonthTextView.setTextColor(Color.BLACK);
            holder.itemView.setClickable(false);
        }
    }

    @Override
    public int getItemCount() {
        return daysOfMonth.size();
    }

    public static class DayViewHolder extends RecyclerView.ViewHolder {
        TextView dayOfWeekTextView;
        TextView dayOfMonthTextView;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayOfWeekTextView = itemView.findViewById(R.id.dayOfWeekTextView);
            dayOfMonthTextView = itemView.findViewById(R.id.dayOfMonthTextView);
        }
    }
}
