package com.example.gimpro_v1.ui.progress;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gimpro_v1.R;
import com.example.gimpro_v1.databinding.FragmentProgressBinding;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;

public class ProgressFragment extends Fragment {

    private FragmentProgressBinding binding;
    float progressStatus = 0, waterCupRecommendation = 8, coefficient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProgressBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        coefficient = 100 / waterCupRecommendation;

        ProgressBar progressBar = root.findViewById(R.id.progressBar);
        Button plusWater = root.findViewById(R.id.plusWater);
        Button minusWater = root.findViewById(R.id.minusWater);

        plusWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressStatus < 100){
                    progressStatus += coefficient;
                    progressBar.setProgress((int) progressStatus);
                } else {
                    FancyToast.makeText(Objects.requireNonNull(getContext()),"Can't do this operation!",FancyToast.LENGTH_LONG, FancyToast.WARNING,false).show();
                }
            }
        });
        minusWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (progressStatus >= 0){
                    progressStatus -= coefficient;
                    progressBar.setProgress((int) progressStatus);
                } else {
                    Toast.makeText(getContext(), "Can't do this operation!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}