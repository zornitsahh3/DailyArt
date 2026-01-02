package com.example.f120268;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Painting.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract PaintingDao paintingDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "paintings_db"
                    ).allowMainThreadQueries() // only for testing, better use AsyncTask or LiveData
                    .build();
        }
        return instance;
    }
}
