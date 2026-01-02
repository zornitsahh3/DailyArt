package com.example.testproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FavoritePaintingDao {

    @Insert
    void insert(FavoritePainting favorite);

    @Delete
    void delete(FavoritePainting favorite);

    @Query("SELECT * FROM favorites WHERE username = :username")
    List<FavoritePainting> getFavoritesForUser(String username);

    @Query("SELECT * FROM favorites WHERE username = :username AND paintingName = :paintingName")
    FavoritePainting findFavorite(String username, String paintingName);
}
