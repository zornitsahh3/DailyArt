package com.example.testproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NameActivity extends AppCompatActivity {

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

                String name = editName.getText().toString().trim();

                if (name.isEmpty()) {
                    Toast.makeText(NameActivity.this,
                            "Please enter your name", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Navigate to MainActivity and send the name
                Intent intent = new Intent(NameActivity.this, MainActivity.class);
                intent.putExtra("username", name);
                startActivity(intent);
            }
        });
    }
}
