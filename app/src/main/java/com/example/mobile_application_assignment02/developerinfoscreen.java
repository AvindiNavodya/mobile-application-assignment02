package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class developerinfoscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_developerinfoscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // exit to News Screen when EXIT button is clicked
        Button exitButton = findViewById(R.id.button); // EXIT button ID
        exitButton.setOnClickListener(v -> {
            Intent intent = new Intent(developerinfoscreen.this, newsscreen.class);
            startActivity(intent);
            finish(); // Optional: clear this screen from back stack
        });
    }
}
