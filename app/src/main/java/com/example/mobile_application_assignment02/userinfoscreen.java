package com.example.mobile_application_assignment02;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class userinfoscreen extends AppCompatActivity {

    TextView usernameView, emailView;

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

        // Back to News Screen
        ImageView backArrow = findViewById(R.id.imageView14);
        backArrow.setOnClickListener(v -> {
            startActivity(new Intent(userinfoscreen.this, newsscreen.class));
            finish();
        });

        // Set the username and email from session
        usernameView = findViewById(R.id.textView10);
        emailView = findViewById(R.id.textView12);

        // Set only if not null
        if (UserSession.username != null) {
            usernameView.setText(" " + UserSession.username);
        }

        if (UserSession.email != null) {
            emailView.setText(" " + UserSession.email);
        }

        // Sign-out dialog
        Button signOutBtn = findViewById(R.id.button4);
        signOutBtn.setOnClickListener(v -> showSignOutDialog());

        // Edit Info dialog
        Button editInfoBtn = findViewById(R.id.button3);
        editInfoBtn.setOnClickListener(v -> showEditInfoDialog());
    }

    private void showSignOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(userinfoscreen.this);

        TextView title = new TextView(this);
        title.setText("Sign-out?");
        title.setPadding(40, 40, 40, 20);
        title.setTextSize(20);
        title.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        title.setGravity(Gravity.LEFT);
        title.setTextColor(getResources().getColor(android.R.color.black));

        TextView message = new TextView(this);
        message.setText("You will be logged out of your account.");
        message.setPadding(40, 0, 40, 40);
        message.setTextSize(16);
        message.setTypeface(Typeface.create("sans-serif-medium", Typeface.NORMAL));
        message.setGravity(Gravity.TOP);
        message.setTextColor(getResources().getColor(android.R.color.black));

        builder.setCustomTitle(title);
        builder.setView(message);
        builder.setNegativeButton("DISMISS", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("CONFIRM", (dialog, which) -> {
            // Optional: clear session
            UserSession.username = null;
            UserSession.email = null;
            Intent intent = new Intent(userinfoscreen.this, loginscreen.class);
            startActivity(intent);
            finish();
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.purple_500));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.purple_500));
    }

    private void showEditInfoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(userinfoscreen.this);

        TextView title = new TextView(this);
        title.setText("Edit");
        title.setPadding(40, 40, 40, 20);
        title.setTextSize(20);
        title.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        title.setGravity(Gravity.CENTER);
        title.setTextColor(getResources().getColor(android.R.color.black));

        EditText inputUsername = new EditText(this);
        inputUsername.setHint("Username");
        inputUsername.setPadding(40, 40, 40, 40);
        inputUsername.setBackground(getDrawable(android.R.drawable.edit_text));

        EditText inputEmail = new EditText(this);
        inputEmail.setHint("Email");
        inputEmail.setPadding(40, 40, 40, 40);
        inputEmail.setBackground(getDrawable(android.R.drawable.edit_text));

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(50, 20, 50, 10);
        layout.addView(inputUsername);
        layout.addView(inputEmail);

        builder.setCustomTitle(title);
        builder.setView(layout);

        builder.setNegativeButton("CANCEL", (dialog, which) -> dialog.dismiss());

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newUsername = inputUsername.getText().toString().trim();
            String newEmail = inputEmail.getText().toString().trim();

            if (!newUsername.isEmpty()) {
                usernameView.setText(" " + newUsername);
                UserSession.username = newUsername;
            }

            if (!newEmail.isEmpty()) {
                emailView.setText(" " + newEmail);
                UserSession.email = newEmail;
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.purple_500));
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.purple_500));
    }
}
