package com.example.gimpro_v1.ui.settings;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.gimpro_v1.ProfileSettings;
import com.example.gimpro_v1.R;
import com.example.gimpro_v1.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {
    private FragmentSettingsBinding binding;
    ImageView profileSettings, converter, workoutReminder;
    Button contactEmail, trainSmart, news, helpButton, shareFriends;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        profileSettings = root.findViewById(R.id.profileSettings);
        converter = root.findViewById(R.id.converter);
        workoutReminder = root.findViewById(R.id.workoutReminder);

        contactEmail = root.findViewById(R.id.contactEmail);
        trainSmart = root.findViewById(R.id.trainSmart);
        news = root.findViewById(R.id.news);
        helpButton = root.findViewById(R.id.helpButton);
        shareFriends = root.findViewById(R.id.shareFriends);


        profileSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ProfileSettings.class));
            }
        });

        contactEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "support@gimpro_v1.com", null));

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
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