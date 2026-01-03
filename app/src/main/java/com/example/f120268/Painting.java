package com.example.f120268;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "paintings")
public class Painting {
    // Indicates whether the painting is marked as favorite by the user
    public boolean isFavorite = false;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String paintingName;
    private String authorName;
    // --- Represents the image-we reference the image by the id (for local drawables) ---
    private int paintingImage;
    // --- Image URL for API-loaded images ---
    private String imageUrl;
    // --- Constructor for local drawable images ---
    public Painting(String paintingName, String authorName, int paintingImage) {
        this.paintingName = paintingName;
        this.paintingImage = paintingImage;
        this.authorName = authorName;
        this.imageUrl = null;
    }
    // --- Constructor for API images ---
    public Painting(String paintingName, String authorName, String imageUrl) {
        this.paintingName = paintingName;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
        this.paintingImage = 0; // 0 means no local drawable
    }
    //Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPaintingName() {
        return paintingName;
    }
    public int getPaintingImage() {
        return paintingImage;
    }
    public String getAuthorName() {
        return authorName;
    }
    public boolean isFavorite() { return isFavorite; }
    public void setFavorite(boolean favorite) { isFavorite = favorite; }
    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }
    public void setPaintingImage(int paintingImage) {
        this.paintingImage = paintingImage;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Check if painting uses URL image or local drawable
    public boolean hasImageUrl() {
        return imageUrl != null && !imageUrl.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Painting painting = (Painting) o;
        // Compare by painting data (name, author) rather than ID
        // since ID might be 0 for unsaved paintings or different for same painting
        boolean nameMatch = paintingName != null && paintingName.equals(painting.paintingName);
        boolean authorMatch = authorName != null && authorName.equals(painting.authorName);
        return nameMatch && authorMatch;
    }

    @Override
    public int hashCode() {
        int result = (paintingName != null ? paintingName.hashCode() : 0);
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }
}
