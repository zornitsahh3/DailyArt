package com.example.f120268;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.appbar.MaterialToolbar;
import com.example.f120268.api.PaintingApiHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<Painting> paintingArrayList;
    private static MyCustomAdapter adapter;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // ------------------- TOOLBAR -------------------
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        // --- Get username from NameActivity ---
        userName = getIntent().getStringExtra("username");
        if (userName != null) {
            Toast.makeText(
                    MainActivity.this,
                    "Welcome " + userName + " to the world of paintings!",
                    Toast.LENGTH_LONG
            ).show();
        }

        // ------------------- LISTVIEW SETUP -------------------
        listView = findViewById(R.id.listview);
        // --- Initialize database ---
        AppDatabase db = AppDatabase.getInstance(this);
        PaintingDao dao = db.paintingDao();

        // --- Insert default paintings if DB is empty ---
        if (dao.getAllPaintings().isEmpty()) {
            dao.insert(new Painting("Mona Lisa", "Leonardo da Vinci", R.drawable.mona_lisa));
            dao.insert(new Painting("The Birth of Venus", "Sandro Botticelli", R.drawable.birth_of_vinus));
            dao.insert(new Painting("The Scream", "Evard Munch", R.drawable.scream));
            dao.insert(new Painting("American Gothic", "Grant Wood", R.drawable.american_gothic));
            dao.insert(new Painting("The Arnolfini", "Jan van Eyck", R.drawable.arnolfini_portrait));
            dao.insert(new Painting("Whistler's Mother", "James McNeill Whistler", R.drawable.whistlers_mother));
            dao.insert(new Painting("Girl with a Pearl", "Johannes Vermeer", R.drawable.girl_with_pearl));
        }

        // --- Load paintings from database first (for offline support) ---
        paintingArrayList = new ArrayList<>(dao.getAllPaintings());

        // --- Set up adapter with local paintings ---
        adapter = new MyCustomAdapter(getApplicationContext(), paintingArrayList, userName);
        listView.setAdapter(adapter);

        // --- Fetch additional paintings from API ---
        fetchPaintingsFromAPI(dao);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_favorites) { // Make sure your menu item id is correct
            // Open Favorites fragment
            FavFragment favFragment = new FavFragment(userName);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main, favFragment)
                    .addToBackStack(null)
                    .commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void fetchPaintingsFromAPI(PaintingDao dao) {
        // Show loading message
        Toast.makeText(this, "Loading paintings from Metropolitan Museum...", Toast.LENGTH_SHORT).show();

        // Fetch 10 random paintings from API
        PaintingApiHelper.fetchRandomPaintings(10, new PaintingApiHelper.PaintingCallback() {
            @Override
            public void onSuccess(List<Painting> paintings) {
                runOnUiThread(() -> {
                    // Add API paintings to database and list
                    for (Painting painting : paintings) {
                        // Check if painting already exists (avoid duplicates)
                        boolean exists = false;
                        for (Painting existing : paintingArrayList) {
                            if (existing.getPaintingName().equals(painting.getPaintingName()) &&
                                existing.getAuthorName().equals(painting.getAuthorName())) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists) {
                            dao.insert(painting);
                            paintingArrayList.add(painting);
                        }
                    }
                    // Update adapter
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, 
                        "Loaded " + paintings.size() + " paintings from Metropolitan Museum!", 
                        Toast.LENGTH_SHORT).show();
                });
            }

            @Override
            public void onError(String error) {
                runOnUiThread(() -> {
                    Toast.makeText(MainActivity.this, 
                        "Could not load paintings from API: " + error, 
                        Toast.LENGTH_LONG).show();
                });
            }
        });
    }

}
