package com.example.mobile_application_assignment02;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
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

public class newsdetailscreen extends AppCompatActivity {

    TextView textView5, textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_newsdetailscreen);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Go back to news screen
        ImageView backBtn = findViewById(R.id.imageView9);
        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(newsdetailscreen.this, newsscreen.class);
            startActivity(intent);
            finish();
        });

        //  Get references to the TextViews
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);

        //  Firebase reference
        DatabaseReference newsRef = FirebaseDatabase.getInstance()
                .getReferenceFromUrl("https://fotalert-test1-default-rtdb.firebaseio.com/")
                .child("newsDetails");

        newsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String description = snapshot.child("description").getValue(String.class);
                    String summary = snapshot.child("summary").getValue(String.class);

                    textView5.setText(description != null ? description : "No description available.");
                    textView6.setText(summary != null ? summary : "No summary available.");
                } else {
                    Toast.makeText(newsdetailscreen.this, "No news details found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(newsdetailscreen.this, "Failed to load news details", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
