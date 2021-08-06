package com.finwin.payyanur.ckyc.save_data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = SaveData.class, exportSchema = false, version = 1)
public abstract class CustomerDatabase extends RoomDatabase {

    public static final String DB_NAME = "SaveData";
    private static CustomerDatabase instance;

    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);


    public static synchronized CustomerDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, CustomerDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }

    public abstract SaveDataDao saveDataDao();
}
