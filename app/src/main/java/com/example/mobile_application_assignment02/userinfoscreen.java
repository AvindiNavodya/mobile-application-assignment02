package com.example.mobile_application_assignment02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class userinfoscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userinfoscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 👉 Back button
        ImageView backArrow = findViewById(R.id.imageView14);
        backArrow.setOnClickListener(v -> {
            startActivity(new Intent(userinfoscreen.this, newsscreen.class));
            finish();
        });

        // 👉 Sign-out dialog
        Button signOutBtn = findViewById(R.id.button4);
        signOutBtn.setOnClickListener(v -> showSignOutDialog());
    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(userinfoscreen.this);

        // Title
        TextView title = new TextView(this);
        title.setText("Sign-out?");
        title.setPadding(40, 40, 40, 20);
        title.setTextSize(20);
        title.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        title.setGravity(Gravity.LEFT);
        title.setTextColor(getResources().getColor(android.R.color.black));

        // Message
        TextView message = new TextView(this);
        message.setText("You will be logged out of your account.");
        message.setPadding(40, 0, 40, 40);
        message.setTextSize(16);
        message.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        message.setGravity(Gravity.TOP);
        message.setTextColor(getResources().getColor(android.R.color.black));

        // Build dialog
        builder.setCustomTitle(title);
        builder.setView(message);

        builder.setNegativeButton("DISMISS", (dialog, which) -> dialog.dismiss());

        builder.setPositiveButton("CONFIRM", (dialog, which) -> {
            Intent intent = new Intent(userinfoscreen.this, loginscreen.class);
            startActivity(intent);
            finish();
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Style the buttons
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.purple_500));
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.purple_500));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD));
    }
}
