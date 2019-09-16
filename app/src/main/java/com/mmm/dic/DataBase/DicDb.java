package com.mmm.dic.DataBase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.mmm.dic.Classes.Converters;


@Database(entities = {TermEntity.class,TestEntity.class}, version = 1, exportSchema = false)


public abstract class DicDb extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "dicDb";
    private static DicDb sInstance;

    public static DicDb getInstance(Context context) {
        if (sInstance == null) {
            synchronized (LOCK) {
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        DicDb.class, DicDb.DATABASE_NAME)
                        // Queries should be done in a separate thread to avoid locking the UI
                        // We will allow this ONLY TEMPORALLY to see that our DB is working
                        .allowMainThreadQueries()
                        .build();
            }
        }
        return sInstance;
    }


    public abstract DicDao taskDao();

}
