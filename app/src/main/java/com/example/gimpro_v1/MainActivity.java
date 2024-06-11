package com.example.gimpro_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.gimpro_v1.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView actionBarTitle;
    private ImageView image_navbar;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // check if is login
//        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
//        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);
//
//        if (!isLoggedIn) {
//            // Dacă utilizatorul nu este autentificat, redirecționează la SignIn
//            startActivity(new Intent(MainActivity.this, SignIn.class));
//            finish();
//        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home, R.id.navigation_challenge, R.id.navigation_progress, R.id.navigation_settings).build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        // Inflate custom title layout
        LayoutInflater inflater = LayoutInflater.from(this);
        View customActionBarView = inflater.inflate(R.layout.centered_title, null);
        actionBarTitle = customActionBarView.findViewById(R.id.action_bar_title);
        image_navbar = customActionBarView.findViewById(R.id.image_navbar);

        // Set the custom view to the ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayShowCustomEnabled(true);
            getSupportActionBar().setCustomView(customActionBarView);
            Toolbar parent = (Toolbar) customActionBarView.getParent();
            parent.setContentInsetsAbsolute(0, 0);
        }

        // Set the title and its color based on the destination
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            String title = destination.getLabel() != null ? destination.getLabel().toString() : "GimPro";
            int color, image;
            switch (destination.getId()) {
                case R.id.navigation_home:
                    color = Color.RED;
                    image = R.drawable.weight_icon;
                    break;
                case R.id.navigation_challenge:
                    color = Color.GREEN;
                    image = R.drawable.challenge_icon;
                    break;
                case R.id.navigation_progress:
                    color = Color.BLUE;
                    image = R.drawable.progress_up;
                    break;
                case R.id.navigation_settings:
                    color = Color.MAGENTA;
                    image = R.drawable.baseline_settings_24;
                    break;
                default:
                    color = Color.BLACK;
                    image = R.drawable.progress_up;
                    break;
            }
            actionBarTitle.setText(title);
            image_navbar.setImageResource(image);
            actionBarTitle.setTextColor(Color.BLACK); // change with color for set different color for
        });
    }
}
