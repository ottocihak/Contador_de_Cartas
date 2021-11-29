package com.example.contadordecartas;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardsDao {

    @Query("SELECT * FROM cards")
    LiveData<List<Cards>> getCards();

    @Insert
    void addCard(Cards card);

    @Insert
    void addCards(List<Cards> cards);

    @Delete
    void deleteCard(Cards card);

    @Query("DELETE FROM cards")
    void deleteCards();
}
