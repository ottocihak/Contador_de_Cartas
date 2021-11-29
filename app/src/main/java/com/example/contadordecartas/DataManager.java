package com.example.contadordecartas;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Cards.class}, version = 1)
public abstract class DataManager extends RoomDatabase {

    private static DataManager INSTANCE;

    public static DataManager getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(
                            context.getApplicationContext(),
                            DataManager.class, "db"
                    ).build();
        }
        return INSTANCE;
    }

    public abstract CardsDao getCardsDao();
}
