package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class signupscreen extends AppCompatActivity {

    EditText etUsername, etPassword, etConfirmPassword, etEmail;
    Button btnSignUp;

    DatabaseReference databaseRef;

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

        //  Firebase
        databaseRef = FirebaseDatabase.getInstance().getReference("users");

        // Input fields
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etPassword1);
        etEmail = findViewById(R.id.etUsername1);
        btnSignUp = findViewById(R.id.btnLogin);

        //  Go to Login screen
        TextView loginLink = findViewById(R.id.textView);
        loginLink.setOnClickListener(v -> {
            Intent intent = new Intent(signupscreen.this, loginscreen.class);
            startActivity(intent);
            finish();
        });

        //  SIGN-UP button logic
        btnSignUp.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            String email = etEmail.getText().toString().trim();

            if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) ||
                    TextUtils.isEmpty(confirmPassword) || TextUtils.isEmpty(email)) {
                Toast.makeText(signupscreen.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(signupscreen.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save to Firebase
            String userId = databaseRef.push().getKey(); // auto ID
            HashMap<String, String> userMap = new HashMap<>();
            userMap.put("username", username);
            userMap.put("password", password);
            userMap.put("email", email);

            if (userId != null) {
                databaseRef.child(userId).setValue(userMap).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Store temporarily for user info screen (optional)
                        UserSession.username = username;
                        UserSession.email = email;

                        Toast.makeText(signupscreen.this, "Sign-Up Successful!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(signupscreen.this, newsscreen.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(signupscreen.this, "Failed to Sign-Up", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
