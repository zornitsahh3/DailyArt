package com.example.testproject;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.testproject.Painting;

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
