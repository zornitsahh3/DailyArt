package com.example.testproject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavoritesManager {

    private static FavoritesManager instance;

    // Store favorites per user
    private HashMap<String, ArrayList<Painting>> favoritesPerUser = new HashMap<>();

    private FavoritesManager() {
        // Private constructor for singleton
    }

    public static FavoritesManager getInstance() {
        if (instance == null) {
            instance = new FavoritesManager();
        }
        return instance;
    }

    // Add a painting to a specific user's favorites
    public void addFavorite(String username, Painting painting) {
        ArrayList<Painting> userFavorites = favoritesPerUser.getOrDefault(username, new ArrayList<>());
        if (!userFavorites.contains(painting)) {
            userFavorites.add(painting);
            favoritesPerUser.put(username, userFavorites);
        }
    }

    // Retrieve favorites for a specific user
    public List<Painting> getFavorites(String username) {
        return favoritesPerUser.getOrDefault(username, new ArrayList<>());
    }

    // Optional: remove a painting from favorites
    public void removeFavorite(String username, Painting painting) {
        ArrayList<Painting> userFavorites = favoritesPerUser.get(username);
        if (userFavorites != null) {
            userFavorites.remove(painting);
        }
    }

    // Optional: check if painting is in favorites
    public boolean isFavorite(String username, Painting painting) {
        ArrayList<Painting> userFavorites = favoritesPerUser.get(username);
        return userFavorites != null && userFavorites.contains(painting);
    }
}
