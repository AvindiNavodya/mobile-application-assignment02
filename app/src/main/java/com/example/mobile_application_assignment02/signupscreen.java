package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class signupscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signupscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Go back to Login screen
        TextView loginLink = findViewById(R.id.textView);
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(signupscreen.this, loginscreen.class);
            startActivity(intent);
            finish();
        });

        //  SIGN-UP button → Go to News Screen
        Button signUpButton = findViewById(R.id.btnLogin); // same ID as login button
        signUpButton.setOnClickListener(v -> {
            Intent intent = new Intent(signupscreen.this, newsscreen.class);
            startActivity(intent);
            finish(); // Optional: prevents going back to sign-up screen
        });
    }
}
