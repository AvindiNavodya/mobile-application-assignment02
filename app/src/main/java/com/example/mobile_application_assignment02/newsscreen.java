package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class newsscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newsscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Go to User Info
        ImageView accountIcon = findViewById(R.id.imageView7);
        accountIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, userinfoscreen.class));
        });

        // Go to Developer Info
        ImageView errorIcon = findViewById(R.id.imageView8);
        errorIcon.setOnClickListener(v -> {
            startActivity(new Intent(this, developerinfoscreen.class));
        });

        // Go to News Detail from "READ MORE.."
        TextView readMore = findViewById(R.id.textView22); // "READ MORE.." text
        readMore.setOnClickListener(v -> {
            Intent intent = new Intent(this, newsdetailscreen.class);
            startActivity(intent);
        });
    }
}
