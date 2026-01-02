package com.example.f120268;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites")
public class FavoritePainting {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String username;        // user who marked this painting as favorite
    private String paintingName;
    private String authorName;
    private int paintingImageResId; // resource ID for image

    public FavoritePainting(String username, String paintingName, String authorName, int paintingImageResId) {
        this.username = username;
        this.paintingName = paintingName;
        this.authorName = authorName;
        this.paintingImageResId = paintingImageResId;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsername() { return username; }
    public String getPaintingName() { return paintingName; }
    public String getAuthorName() { return authorName; }
    public int getPaintingImageResId() { return paintingImageResId; }
}
