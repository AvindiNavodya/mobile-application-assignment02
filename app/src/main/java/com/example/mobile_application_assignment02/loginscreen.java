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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class loginscreen extends AppCompatActivity {

    EditText etUsername, etPassword;
    Button btnLogin;
    DatabaseReference databaseRef;

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

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        databaseRef = FirebaseDatabase.getInstance().getReference("users");

        // Go to Sign-up screen
        TextView signUpLink = findViewById(R.id.textView);
        signUpLink.setOnClickListener(v -> {
            Intent intent = new Intent(loginscreen.this, signupscreen.class);
            startActivity(intent);
        });

        // Login logic
        btnLogin.setOnClickListener(v -> {
            String enteredUsername = etUsername.getText().toString().trim();
            String enteredPassword = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(enteredUsername) || TextUtils.isEmpty(enteredPassword)) {
                Toast.makeText(loginscreen.this, "Please enter both fields", Toast.LENGTH_SHORT).show();
                return;
            }

            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot snapshot) {
                    boolean loginSuccess = false;

                    for (DataSnapshot userSnap : snapshot.getChildren()) {
                        String dbUsername = userSnap.child("username").getValue(String.class);
                        String dbPassword = userSnap.child("password").getValue(String.class);

                        if (enteredUsername.equals(dbUsername) && enteredPassword.equals(dbPassword)) {
                            //  Login success
                            String email = userSnap.child("email").getValue(String.class);
                            UserSession.username = dbUsername;
                            UserSession.email = email;

                            Toast.makeText(loginscreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(loginscreen.this, newsscreen.class);
                            startActivity(intent);
                            finish();
                            loginSuccess = true;
                            break;
                        }
                    }

                    if (!loginSuccess) {
                        Toast.makeText(loginscreen.this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    Toast.makeText(loginscreen.this, "Database error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
