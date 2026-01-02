package com.example.testproject;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;

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

        // ------------------- TOOLBAR -------------------
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // --- Get username from NameActivity ---
        String userName = getIntent().getStringExtra("username");
        if (userName != null) {
            Toast.makeText(
                    MainActivity.this,
                    "Welcome " + userName + " to the world of paintings!",
                    Toast.LENGTH_LONG
            ).show();
        }

        // ------------------- LISTVIEW SETUP -------------------
        listView = findViewById(R.id.listview);
        paintingArrayList = new ArrayList<>();

        paintingArrayList.add(new Painting("Mona Lisa", "Leonardo da Vinci", R.drawable.mona_lisa));
        paintingArrayList.add(new Painting("The Birth of Venus", "Sandro Botticelli", R.drawable.birth_of_vinus));
        paintingArrayList.add(new Painting("The Scream", "Evard Munch", R.drawable.scream));
        paintingArrayList.add(new Painting("American Gothic", "Grant Wood", R.drawable.american_gothic));
        paintingArrayList.add(new Painting("The Arnolfini", "Jan van Eyck", R.drawable.arnolfini_portrait));
        paintingArrayList.add(new Painting("Whistler’s Mother", "James McNeill Whistler", R.drawable.whistlers_mother));
        paintingArrayList.add(new Painting("Girl with a Pearl", "Johannes Vermeer", R.drawable.girl_with_pearl));

        // --- Set up the Adapter ---
        adapter = new MyCustomAdapter(getApplicationContext(), paintingArrayList);
        listView.setAdapter(adapter);

        // ------------------- EDGE-TO-EDGE PADDING -------------------
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

    // ------------------- MENU INFLATION -------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate your menu resource (res/menu/tabs.xml)
        getMenuInflater().inflate(R.menu.tabs, menu);
        return true;
    }
}
