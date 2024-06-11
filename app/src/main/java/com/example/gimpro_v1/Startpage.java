package com.example.gimpro_v1;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gimpro_v1.databinding.ActivityStartpageBinding;

public class Startpage extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityStartpageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityStartpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button start = findViewById(R.id.getStartedButton);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Startpage.this, SignIn.class));
            }
        });


    }
}