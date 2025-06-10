package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class newsscreen extends AppCompatActivity {

    LinearLayout sportsTab, academicTab, eventsTab;

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

        // Top icons
        ImageView accountIcon = findViewById(R.id.imageView7);
        accountIcon.setOnClickListener(v -> startActivity(new Intent(this, userinfoscreen.class)));

        ImageView errorIcon = findViewById(R.id.imageView8);
        errorIcon.setOnClickListener(v -> startActivity(new Intent(this, developerinfoscreen.class)));

        // Read more
        TextView readMore = findViewById(R.id.textView22);
        readMore.setOnClickListener(v -> {
            Intent intent = new Intent(this, newsdetailscreen.class);
            startActivity(intent);
        });

        // Bottom nav views
        sportsTab = findViewById(R.id.sportsTab);
        academicTab = findViewById(R.id.academicTab);
        eventsTab = findViewById(R.id.eventsTab);

        // Default highlight
        highlightTab(sportsTab);

        sportsTab.setOnClickListener(v -> {
            highlightTab(sportsTab);
            Toast.makeText(this, "SPORTS clicked", Toast.LENGTH_SHORT).show();
        });

        academicTab.setOnClickListener(v -> {
            highlightTab(academicTab);
            Toast.makeText(this, "ACADEMIC clicked", Toast.LENGTH_SHORT).show();
        });

        eventsTab.setOnClickListener(v -> {
            highlightTab(eventsTab);
            Toast.makeText(this, "EVENTS clicked", Toast.LENGTH_SHORT).show();
        });
    }

    // Method to highlight selected tab
    private void highlightTab(LinearLayout selectedTab) {
        int selectedColor = Color.parseColor("#F3DEFF");
        int defaultColor = Color.parseColor("#FFFFFF");

        sportsTab.setBackgroundColor(sportsTab == selectedTab ? selectedColor : defaultColor);
        academicTab.setBackgroundColor(academicTab == selectedTab ? selectedColor : defaultColor);
        eventsTab.setBackgroundColor(eventsTab == selectedTab ? selectedColor : defaultColor);
    }
}
