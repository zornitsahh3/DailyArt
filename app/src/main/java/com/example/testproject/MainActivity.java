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

    ListView listView;
    ArrayList<Painting> paintingArrayList;
    private static MyCustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // --- Receive the user's name from NameActivity ---
        String userName = getIntent().getStringExtra("username");
        if (userName != null) {
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
        adapter = new MyCustomAdapter(getApplicationContext(), paintingArrayList);
        listView.setAdapter(adapter);

        // --- Keep your Edge-to-Edge window insets handling ---
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
