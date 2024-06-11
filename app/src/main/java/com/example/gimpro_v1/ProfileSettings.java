package com.example.gimpro_v1;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.shashank.sony.fancytoastlib.FancyToast;

public class ProfileSettings extends AppCompatActivity {

    private EditText editName, editEmail, editPhone, editOldPassword, editNewPassword, editConfirmPassword;
    private Switch switchEmailNotifications, switchSmsNotifications;
    private Button buttonLogout, buttonReturn, buttonChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        // Initialize views
        editName = findViewById(R.id.edit_name);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editOldPassword = findViewById(R.id.edit_old_password);
        editNewPassword = findViewById(R.id.edit_new_password);
        editConfirmPassword = findViewById(R.id.edit_confirm_password);
        switchEmailNotifications = findViewById(R.id.switch_email_notifications);
        switchSmsNotifications = findViewById(R.id.switch_sms_notifications);
        buttonLogout = findViewById(R.id.button_logout);
        buttonReturn = findViewById(R.id.button_return);
        buttonChangePass = findViewById(R.id.buttonChangePass);

        OnBackPressedDispatcher onBackPressedDispatcher = this.getOnBackPressedDispatcher();
        onBackPressedDispatcher.addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Logica personalizată la apăsarea butonului back
                FancyToast.makeText(getApplicationContext(),"No changes were made",FancyToast.LENGTH_SHORT, FancyToast.INFO,false).show();
                finish();
            }
        });
        // Set click listener for Return button
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressedDispatcher.onBackPressed();
            }
        });
    }


    private void returnToMain() {
        // Redirect to MainActivity
        Intent intent = new Intent(ProfileSettings.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}