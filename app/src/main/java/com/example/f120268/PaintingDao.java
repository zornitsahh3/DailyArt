package com.example.f120268;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PaintingDao {

    @Insert
    void insert(Painting painting);

    @Query("SELECT * FROM paintings")
    List<Painting> getAllPaintings();

    @Query("DELETE FROM paintings")
    void deleteAll();
}
