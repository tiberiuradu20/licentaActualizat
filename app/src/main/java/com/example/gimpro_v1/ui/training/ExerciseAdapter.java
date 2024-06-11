package com.example.gimpro_v1.ui.training;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gimpro_v1.R;
import com.example.gimpro_v1.ui.Models.Exercise;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private Context context;
    private List<Exercise> exerciseList;
    private OnStartClickListener onStartClickListener;

    public ExerciseAdapter(Context context, List<Exercise> exerciseList, OnStartClickListener onStartClickListener) {
        this.context = context;
        this.exerciseList = exerciseList;
        this.onStartClickListener = onStartClickListener;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view, onStartClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.exerciseNameTextView.setText(exercise.getName());
        holder.exerciseDetailsTextView.setText(exercise.getDetails());
        holder.exerciseImageView.setImageResource(exercise.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    public static class ExerciseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView exerciseImageView;
        TextView exerciseNameTextView;
        TextView exerciseDetailsTextView;
        Button startButton;
        OnStartClickListener onStartClickListener;

        public ExerciseViewHolder(@NonNull View itemView, OnStartClickListener onStartClickListener) {
            super(itemView);
            exerciseImageView = itemView.findViewById(R.id.exerciseImageView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseNameTextView);
            exerciseDetailsTextView = itemView.findViewById(R.id.exerciseDetailsTextView);
            startButton = itemView.findViewById(R.id.startButton);
            this.onStartClickListener = onStartClickListener;
            startButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.startButton) {
                onStartClickListener.onStartClick(getAdapterPosition());
            }
        }
    }

    public interface OnStartClickListener {
        void onStartClick(int position);
    }
}
