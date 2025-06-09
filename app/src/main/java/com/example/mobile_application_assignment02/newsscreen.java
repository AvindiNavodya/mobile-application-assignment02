package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

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

        // accountcircle icon → go to userinfoscreen
        ImageView accountIcon = findViewById(R.id.imageView7); // accountcircle
        accountIcon.setOnClickListener(v -> {
            Intent intent = new Intent(newsscreen.this, userinfoscreen.class);
            startActivity(intent);
        });

        //  error icon → go to developerinfoscreen
        ImageView errorIcon = findViewById(R.id.imageView8); // error icon
        errorIcon.setOnClickListener(v -> {
            Intent intent = new Intent(newsscreen.this, developerinfoscreen.class);
            startActivity(intent);
        });
    }
}
