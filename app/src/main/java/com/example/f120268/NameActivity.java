package com.example.f120268;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
// --- Activity responsible for collecting the user's name ---
public class NameActivity extends AppCompatActivity {
    // UI elements
    EditText editName;
    Button btnEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);

        editName = findViewById(R.id.editName);
        btnEnter = findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read and sanitize user input
                String name = editName.getText().toString().trim();
                // Validate input: name must not be empty
                if (name.isEmpty()) {
                    Toast.makeText(NameActivity.this,
                            "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // --- Navigate to MainActivity and send the name ---
                Intent intent = new Intent(NameActivity.this, MainActivity.class);
                intent.putExtra("username", name);
                startActivity(intent);
            }
        });
    }
}
