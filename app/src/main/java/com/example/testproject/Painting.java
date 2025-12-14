package com.example.testproject;

public class Painting {
    // Indicates whether the painting is marked as favorite by the user
    public boolean isFavorite = false;
    private String paintingName;
    private String authorName;
    // --- Represents the image-we reference the image by the id ---
    private int paintingImage;
    // --- Constructor ---
    // Creates a new Painting object with a name, author, and image
    public Painting(String paintingName, String authorName,int paintingImage) {
        this.paintingName = paintingName;
        this.paintingImage = paintingImage;
        this.authorName=authorName;
    }
    //Getters & Setters
    public String getPaintingName() {
        return paintingName;
    }
    public int getPaintingImage() {
        return paintingImage;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setPaintingName(String paintingName) {
        this.paintingName = paintingName;
    }
    public void setPaintingImage(int paintingImage) {
        this.paintingImage = paintingImage;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}
