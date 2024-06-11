package com.example.gimpro_v1.ui.training;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.gimpro_v1.R;
import com.example.gimpro_v1.databinding.FragmentTrainingBinding;
import com.example.gimpro_v1.ui.Models.Exercise;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class TrainingFragment extends Fragment implements ExerciseAdapter.OnStartClickListener {

    private FragmentTrainingBinding binding;
    private RecyclerView daysRecyclerView, exercisesRecyclerView;
    private LinearLayoutManager daysLayoutManager, exercisesLayoutManager;
    private DaysAdapter daysAdapter;
    private ExerciseAdapter exerciseAdapter;
    private List<Exercise> exerciseList;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTrainingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // Days RecyclerView
        daysRecyclerView = root.findViewById(R.id.daysRecyclerView);
        daysLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        daysRecyclerView.setLayoutManager(daysLayoutManager);

        List<String> daysOfWeek = new ArrayList<>();
        List<Integer> daysOfMonth = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

        for (int i = 1; i <= calendar.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            calendar.set(Calendar.DAY_OF_MONTH, i);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            String dayOfWeek = calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, getResources().getConfiguration().locale);

            daysOfWeek.add(dayOfWeek);
            daysOfMonth.add(dayOfMonth);
        }

        daysAdapter = new DaysAdapter(getContext(), daysOfWeek, daysOfMonth, currentDay);
        daysRecyclerView.setAdapter(daysAdapter);

        // Scroll to the current day
        daysRecyclerView.post(() -> {
            int position = currentDay - 1; // ArrayList is 0-indexed
            daysLayoutManager.scrollToPositionWithOffset(position, daysRecyclerView.getWidth() / 2);
        });

        // Exercises RecyclerView
        exercisesRecyclerView = root.findViewById(R.id.exercisesRecyclerView);
        exercisesLayoutManager = new LinearLayoutManager(getContext());
        exercisesRecyclerView.setLayoutManager(exercisesLayoutManager);

        // Populate the list with example exercises
        exerciseList = new ArrayList<>();
        exerciseList.add(new Exercise("SQUATS cu bara", "Three series 14 repetitions", R.drawable.squats_image));
        exerciseList.add(new Exercise("Presa de Picioare", "Four series 12 repetitions", R.drawable.squats_image));
        exerciseList.add(new Exercise("Fandări cu Haltere", "Four series 12 repetitions", R.drawable.squats_image));
        exerciseList.add(new Exercise("Îndreptări Românești", "Four series 12 repetitions", R.drawable.squats_image));

        exerciseAdapter = new ExerciseAdapter(getContext(), exerciseList, this);
        exercisesRecyclerView.setAdapter(exerciseAdapter);

        return root;
    }

    @Override
    public void onStartClick(int position) {
        // Handle start button click
        Exercise clickedExercise = exerciseList.get(position);

        // Perform action based on clicked exercise
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
