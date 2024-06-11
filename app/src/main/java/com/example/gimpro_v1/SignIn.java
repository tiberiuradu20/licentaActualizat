package com.example.gimpro_v1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignIn extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;
    private Button signInButton;
    private SharedPreferences sharedPreferences;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the activity_sign_in layout
        setContentView(R.layout.activity_sign_in);


        // Verifică dacă utilizatorul este deja autentificat
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Dacă utilizatorul este autentificat, redirecționează la MainActivity
            startActivity(new Intent(SignIn.this, MainActivity.class));
            finish();
        }

        mAuth = FirebaseAuth.getInstance();

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signInButton = findViewById(R.id.signInButton);
        TextView redirectSignUp = findViewById(R.id.textView2);

        redirectSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignIn.this, SignUp.class));
                finish();
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
    }

    private void signInUser() {
        // Get the email and password from EditTexts
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Check if email is empty
        if (TextUtils.isEmpty(email)) {
            emailEditText.setError("Email is required.");
            emailEditText.requestFocus();
            return;
        }

        // Check if password is empty
        if (TextUtils.isEmpty(password)) {
            passwordEditText.setError("Password is required.");
            passwordEditText.requestFocus();
            return;
        }

        // Attempt to sign in with the provided email and password
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // Print a debug message
                        System.out.println("Sign-in attempt completed");

                        // Check if the sign-in was successful
                        if (task.isSuccessful()) {
                            // Show a success message
                            FancyToast.makeText(SignIn.this, "Authentication Successful.", FancyToast.LENGTH_SHORT, FancyToast.SUCCESS, false).show();

                            // Start the MainActivity and finish the current activity
                            startActivity(new Intent(SignIn.this, MainActivity.class));
                            finish();
                        } else {
                            // Show an error message with the exception details
                            // Handle sign-in failures
                            Exception exception = task.getException();
                            if (exception != null) {
                                if (exception instanceof FirebaseAuthInvalidCredentialsException) {
                                    FancyToast.makeText(SignIn.this, "Invalid credentials.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                } else if (exception instanceof FirebaseAuthInvalidUserException) {
                                    FancyToast.makeText(SignIn.this, "No account found with this email.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                } else if (exception instanceof FirebaseNetworkException) {
                                    FancyToast.makeText(SignIn.this, "Network error. Please check your connection and try again.", FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                } else {
                                    FancyToast.makeText(SignIn.this, "Authentication Failed: " + exception.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, false).show();
                                }
                            }
                        }
                    }
                });
    }
}
