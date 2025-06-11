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

public class loginscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loginscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Go to Sign-up screen
        TextView signUpLink = findViewById(R.id.textView);
        signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(loginscreen.this, signupscreen.class);
            startActivity(intent);
        });

        //  Login button → Go to News Screen
        Button loginButton = findViewById(R.id.btnLogin);
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(loginscreen.this, newsscreen.class);
            startActivity(intent);
            finish(); // Optional: prevent going back to login screen
        });
    }
}
