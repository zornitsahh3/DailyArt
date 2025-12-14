package com.example.testproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    // --- The list of paintings we will use in order to display them ---
    ListView listView;
    // --- Stores data about each painting ---
    ArrayList<Painting> paintingArrayList;
    private static MyCustomAdapter adapter;
    // --- Connects the data (paintingArrayList) to the UI (listView) ---
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // ---Makes the Activity draw behind system bars ---
        EdgeToEdge.enable(this);
        // ---Tells Android to use activity_main.xml as the layout for this Activity ---
        setContentView(R.layout.activity_main);
        // --- Receive the user's name from NameActivity ---
        String userName = getIntent().getStringExtra("username");
        if (userName != null) {
            // ---Shows a Toast message welcoming the user---
            Toast.makeText(
                    MainActivity.this,
                    "Welcome " + userName + " to the world of paintings!",
                    Toast.LENGTH_LONG
            ).show();
        }

        // --- Setup list view and paintings ---
        listView = findViewById(R.id.listview);
        paintingArrayList = new ArrayList<>();

        Painting painting1 = new Painting(
                "Mona Lisa",
                "Leonardo da Vinci",
                R.drawable.mona_lisa
        );
        paintingArrayList.add(painting1);
        Painting painting2 = new Painting(
                "The Birth of Venus",
                "Sandro Botticelli",
                R.drawable.birth_of_vinus
        );
        paintingArrayList.add(painting2);
        Painting painting3 = new Painting(
                "The Scream",
                "Evard Munch",
                R.drawable.scream
        );
        paintingArrayList.add(painting3);
        Painting painting4 = new Painting(
                "American Gothic",
                "Grant Wood",
                R.drawable.american_gothic
        );
        paintingArrayList.add(painting4);
        Painting painting5 = new Painting(
                "The Arnolfini",
                "Jan van Eyck",
                R.drawable.arnolfini_portrait
        );
        paintingArrayList.add(painting5);
        Painting painting6 = new Painting(
                "Whistler’s Mother",
                "James McNeill Whistler",
                R.drawable.whistlers_mother
        );
        paintingArrayList.add(painting6);
        Painting painting7 = new Painting(
                "Girl with a Pearl",
                "Johannes Vermeer",
                R.drawable.girl_with_pearl
        );
        paintingArrayList.add(painting7);
        // --- Set up the Adapter ---
        adapter = new MyCustomAdapter(getApplicationContext(), paintingArrayList);
        listView.setAdapter(adapter);
        // ---Ensures that the UI does not overlap system bars ---
        ViewCompat.setOnApplyWindowInsetsListener(
                findViewById(R.id.main),
                (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top,
                            systemBars.right, systemBars.bottom);
                    return insets;
                }
        );
    }
}
