package com.example.testproject;

public class Painting {
    //Attributes
    private String paintingName;
    private String authorName;
    private int paintingImage; //represents the image-we reference the image by the id
    //Constructor
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
