package com.example.f120268;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.content.Context;

@Database(entities = {Painting.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // Migration from version 1 to 2: Add imageUrl column
    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE paintings ADD COLUMN imageUrl TEXT");
        }
    };

    public abstract PaintingDao paintingDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "paintings_db"
                    ).addMigrations(MIGRATION_1_2)
                    .allowMainThreadQueries() // only for testing, better use AsyncTask or LiveData
                    .fallbackToDestructiveMigration() // For development only
                    .build();
        }
        return instance;
    }
}
